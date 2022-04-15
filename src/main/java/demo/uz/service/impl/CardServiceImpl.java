package demo.uz.service.impl;

import demo.uz.common.MapstructMapper;
import demo.uz.domain.Card;
import demo.uz.domain.Operation;
import demo.uz.model.OperationDto;
import demo.uz.repository.CardRepo;
import demo.uz.repository.OperationRepo;
import demo.uz.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;
    private final OperationRepo operationRepo;
    private final MapstructMapper mapper;

    @Override
    public Card get(Long id) {
        Optional<Card> optionalCard = cardRepo.findById(id);
        return optionalCard.orElse(null);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    @Override
    public List<OperationDto> getOperations(Long id, Integer page, Integer size) {
        Optional<Card> optionalCard = cardRepo.findById(id);
        if (!optionalCard.isPresent()){
            throw new RuntimeException(String.format("Card id with %s is not found", id));
        }


        List<Operation> operations = operationRepo.findAllByCard(id, page * size, size);

        List<OperationDto> operationDtoList = new ArrayList<>();

        for (Operation operation : operations) {
            operationDtoList.add(mapper.toOperationDto(operation));
        }

        return operationDtoList;
    }
}
