package demo.uz.service.impl;

import demo.uz.domain.User;
import demo.uz.model.UserCrudDto;
import demo.uz.repository.UserRepo;
import demo.uz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User save(UserCrudDto userCrudDto) {
        User byUsername = userRepo.findByUsername(userCrudDto.getUsername());
        if (byUsername != null) {
            throw new RuntimeException(String.format("This username %s is already exists", userCrudDto.getUsername()));
        }
        User user = User.toUser(userCrudDto);
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
}
