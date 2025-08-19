package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.User;
import com.sparksupport.sparktraining.services.UserService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/logDemo")
    public String logginDemo() {
        return userService.loggingDemo();
    }

    /**
     *
     * @param id
     * @return "User" if Available / "Exception" if -ve id / "user not found in DB" if user not exist
     */
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id should be positive integer");
        }
        return userService.getUserById(id).getBody();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("Error : " + ex.getMessage());
    }
}