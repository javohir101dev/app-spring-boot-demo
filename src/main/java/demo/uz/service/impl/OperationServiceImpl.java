package demo.uz.service.impl;

import demo.uz.domain.*;
import demo.uz.enums.OperationStatus;
import demo.uz.model.OperationCrudDto;
import demo.uz.model.resp.ApiResponse;
import demo.uz.repository.*;
import demo.uz.service.OperationService;
import demo.uz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepo operationRepo;
    private final CardRepo cardRepo;
    private final OperationCodeRepo operationCodeRepo;
    private final LimitRepo limitRepo;
    private final CommissionRepo commissionRepo;
    private final TransactionService transactionService;

    @Override
    public Operation get(Long id) {
        Optional<Operation> optionalOperation = operationRepo.findById(id);
        return optionalOperation.orElse(null);
    }

    @Override
    public Page<Operation> getList(Pageable pageable) {
        return operationRepo.findAll(pageable);
    }

    @Override
    public ApiResponse save(OperationCrudDto dto) {
        Optional<Card> optionalSenderCard = cardRepo.findById(dto.getSenderId());
        if (!optionalSenderCard.isPresent()) {
            return new ApiResponse("Sender Card with id: " + dto.getSenderId() + " is not found", false, HttpStatus.BAD_REQUEST);
        }

        Optional<Card> optionalReceiverCard = cardRepo.findById(dto.getReceiverId());
        if (!optionalReceiverCard.isPresent()) {
            return new ApiResponse("Receiver Card with id: " + dto.getReceiverId() + " is  not found", false, HttpStatus.BAD_REQUEST);
        }

        Card senderCard = optionalSenderCard.get();
        Card receiverCard = optionalReceiverCard.get();

        Operation operation = new Operation();
        operation.setSender(senderCard);
        operation.setReceiver(receiverCard);
        operation.setSenderCurrency(senderCard.getCurrency());
        operation.setReceiverCurrency(receiverCard.getCurrency());

        String strOperationCode = senderCard.getCurrency() + "2" + receiverCard.getCurrency();
        Optional<OperationCode> optionalOperationCode = operationCodeRepo.byName(strOperationCode);
        if (!optionalOperationCode.isPresent()) {
            return new ApiResponse("Operation code not found", false, HttpStatus.BAD_REQUEST);
        }


        operation.setOperationCode(strOperationCode);

        Long senderAmount = dto.getAmount() * 100L;

//        todo adding expirt checkto cards

        Limit limit = limitRepo.findByOperationCode(strOperationCode);
//      todo checking limit earliar
        if (senderAmount < limit.getMinLimit()) {
            return new ApiResponse("Less than the minimum limit " + limit.getMinLimit()/100 + " cannot be sent!", false, HttpStatus.BAD_REQUEST);
        } else if (senderAmount > limit.getMaxLimit()) {
            return new ApiResponse("More than the maximum limit " + limit.getMaxLimit()/100 + " cannot be sent!", false, HttpStatus.BAD_REQUEST);
        }

        Commission commission = commissionRepo.byOperationCode(strOperationCode);
        Long feeAmount = dto.getAmount() * commission.getRate() / 100;

        Long fullAmount = senderAmount;
        if (commission.isUpper()) {
            fullAmount = senderAmount + feeAmount;
        }

        if (senderCard.getBalance() < fullAmount) {
            return new ApiResponse("Balance is not enough!", false, HttpStatus.BAD_REQUEST);
        }

        operation.setOperationStatus(OperationStatus.PENDING);
        operation = operationRepo.save(operation);

        // debit
        transactionService.debit(operation, senderCard, fullAmount);

        OperationCode operationCode = optionalOperationCode.get();
        Long exchangeCurrency = operationCode.getExchangeCurrency();
        Boolean isUpper = operationCode.getIsUpper();

        long receiverAmount;
        if (exchangeCurrency == null || exchangeCurrency == 0 || isUpper == null) {
            receiverAmount = senderAmount;
        } else {
            if (isUpper) {
                feeAmount = feeAmount * exchangeCurrency;
                receiverAmount = senderAmount * exchangeCurrency;
            } else {
                feeAmount = feeAmount / exchangeCurrency;
                receiverAmount = senderAmount / exchangeCurrency;
            }
        }

        if (!commission.isUpper()) {
            receiverAmount = receiverAmount - feeAmount;
        }

        // credit
        transactionService.credit(operation, receiverCard, receiverAmount);

        operation.setSenderAmount(fullAmount);
        operation.setReceiverAmount(receiverAmount);
        operation.setOperationStatus(OperationStatus.SUCCESS);
        operation.setCommission(feeAmount);
        operation.setCommissionRate(commission.getRate());

        return new ApiResponse("Operation created", true, HttpStatus.OK, operationRepo.save(operation));

    }
}
