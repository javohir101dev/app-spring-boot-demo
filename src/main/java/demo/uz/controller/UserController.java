package demo.uz.controller;

import demo.uz.domain.User;
import demo.uz.model.UserCrudDto;
import demo.uz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/save")
    public User save(@RequestBody UserCrudDto userCrudDto){
        return userService.save(userCrudDto);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable(value = "id") Long id){
        return userService.get(id);
    }

    @GetMapping("/get/all")
    public List<User> get(){
        return userService.getAll();
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable(value = "id") Long id,  @RequestBody UserCrudDto userCrudDto){
        return userService.update(id, userCrudDto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable(value = "id") Long id){
        return userService.delete(id);
    }

}
