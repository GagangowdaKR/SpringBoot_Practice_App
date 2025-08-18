package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.User;
import com.sparksupport.sparktraining.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@Validated @RequestBody User user){
        return userService.addUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@Validated @RequestBody List<User> users){
        return userService.addUsers(users);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/q20")
    public List<Map<String, String>> getLatestBorrowedBook() {
        return userService.getLatestBorrowedBook();
    }
}
