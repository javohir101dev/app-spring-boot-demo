package demo.uz.service;

import demo.uz.domain.Card;
import demo.uz.domain.User;
import demo.uz.enums.Currency;
import demo.uz.model.CardDto;
import demo.uz.model.OperationDto;
import demo.uz.model.UserCrudDto;

import java.util.List;

public interface UserService {


    User save(UserCrudDto userCrudDto);

    User update(Long id, UserCrudDto userCrudDto);

    User get(Long id);

    List<User> getAll();

    Boolean delete(Long id);

    List<CardDto> getUserCards(Long id, List<String> types);

    List<OperationDto> getOperations(Long id, Integer page, Integer size);

}

