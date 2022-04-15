package demo.uz.service.impl;

import demo.uz.common.MapstructMapper;
import demo.uz.domain.Card;
import demo.uz.domain.Operation;
import demo.uz.domain.User;
import demo.uz.enums.Currency;
import demo.uz.helper.Utils;
import demo.uz.model.CardDto;
import demo.uz.model.OperationDto;
import demo.uz.model.UserCrudDto;
import demo.uz.repository.CardRepo;
import demo.uz.repository.OperationRepo;
import demo.uz.repository.UserRepo;
import demo.uz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final CardRepo cardRepo;
    private final OperationRepo operationRepo;
    private final MapstructMapper mapper;

    @Override
    public User save(UserCrudDto userCrudDto) {
        User byUsername = userRepo.findByUsername(userCrudDto.getUsername());
        if (byUsername != null) {
            throw new RuntimeException(String.format("This username %s is already exists", userCrudDto.getUsername()));
        }
        User user = mapper.toUser(userCrudDto);
        return userRepo.save(user);
    }

    @Override
    public User get(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User update(Long id, UserCrudDto userCrudDto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException(String.format("This user id  %s is not found", id));
        }
        if (userCrudDto.getUsername() != null && !userCrudDto.getUsername().equals("")) {
            User byUsernameAndIdNot = userRepo.findByUsernameAndIdNot(userCrudDto.getUsername(), id);
            if (byUsernameAndIdNot != null) {
                throw new RuntimeException(String.format("This username %s is already exists", userCrudDto.getUsername()));
            }
        }
        User changedUser = User.toUser(optionalUser.get(), userCrudDto);
        return userRepo.save(changedUser);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            throw new RuntimeException(String.format("User with %s is not found", id));
        }
         userRepo.delete(optionalUser.get());
        return true;
    }

    @Override
    public List<CardDto> getUserCards(Long id, List<String> types) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            throw new RuntimeException(String.format("User id with %s is not found", id));
        }

        List<String> currencies = new ArrayList<>();

        if (Utils.isEmpty(types)){
            for (Currency value : Currency.values()) {
                currencies.add(value.toString());
            }
        }else {
            for (String type : types) {
//                todo check try catch with currency
                currencies.add(Currency.valueOf(type).toString());
            }
        }

        List<Card> cards = cardRepo.findByUserIdAAndCurrencies(id, currencies);

        List<CardDto> cardDtoList = mapper.toCardDtoList(cards);
        return cardDtoList;
    }

    @Override
    public List<OperationDto> getOperations(Long id, Integer page, Integer size) {
//        todo sender amount and commission rate correcting percentage
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            throw new RuntimeException(String.format("User id with %s is not found", id));
        }

        List<Long> cardIds = cardRepo.getAllCardIdsByUserId(id);
        if (Utils.isEmpty(cardIds)){
            return null;
        }

        List<Operation> operations = operationRepo.findAllByCards(cardIds, page * size, size);

        return mapper.toOperationDto(operations);
    }
}
