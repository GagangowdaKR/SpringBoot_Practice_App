package com.sparksupport.SparkTraining.Controllers.api;

import com.sparksupport.SparkTraining.Entity.User;
import com.sparksupport.SparkTraining.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

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
}
