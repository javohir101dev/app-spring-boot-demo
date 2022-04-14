package demo.uz.service;

import demo.uz.domain.Card;
import demo.uz.model.OperationDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {


    Card get(Long id);

    List<Card> getAllCards();


    List<OperationDto> getOperations(Long id, Integer page, Integer size);
}
