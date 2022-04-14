package demo.uz.service.impl;


import demo.uz.domain.Card;
import demo.uz.domain.Operation;
import demo.uz.domain.Transaction;
import demo.uz.enums.TransactionStatus;
import demo.uz.enums.TransactionType;
import demo.uz.repository.CardRepo;
import demo.uz.repository.TransactionRepo;
import demo.uz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final CardRepo cardRepo;

    @Override
    public void debit(Operation operation, Card senderCard, Long amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCardNumber(senderCard.getNumber());
        transaction.setCurrency(senderCard.getCurrency());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setOperation(operation);
        transactionRepo.save(transaction);

        senderCard.setBalance(senderCard.getBalance() - amount);
        cardRepo.save(senderCard);
    }

    @Override
    public void credit(Operation operation, Card receiverCard, long amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCardNumber(receiverCard.getNumber());
        transaction.setCurrency(receiverCard.getCurrency());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.CREDIT);
        transaction.setOperation(operation);
        transactionRepo.save(transaction);

        receiverCard.setBalance(receiverCard.getBalance() + amount);
        cardRepo.save(receiverCard);
    }
}
