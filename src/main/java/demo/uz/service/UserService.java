package demo.uz.service;

import demo.uz.domain.User;
import demo.uz.model.UserCrudDto;

import java.util.List;

public interface UserService {


    User save(UserCrudDto userCrudDto);

    User update(Long id, UserCrudDto userCrudDto);

    User get(Long id);

    List<User> getAll();

    Boolean delete(Long id);
}

