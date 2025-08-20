package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.User;
import com.sparksupport.sparktraining.exceptions.ResourceNotFoundException;
import com.sparksupport.sparktraining.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> addUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Custom Exception Handled
     *
     * @param userId
     * @return User or UserNotFoundException
     */
    public ResponseEntity<User> getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found In Database, Id = " + userId));
        return ResponseEntity.ok(user);
    }

    public List<Map<String, String>> getLatestBorrowedBook() {
        return userRepository.findLatestBorrowedBook().stream()
                .map(object -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Member Name", String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    map.put("Issue Date", String.valueOf(object[2]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public String loggingDemo() {
        log.info("Getting Latest Borrowed Book {}", getLatestBorrowedBook());
        log.warn("warning from slf4j log {}", getUsers());
        log.error("error from slf4j log");
        log.debug("debug from slf4j log {}", getUsers());
        log.trace("trace from slf4j log");
        return "Logs are printed on the console...";
    }

    // Test purpose

    public String getUserNameById(Integer userId) {
        return userRepository.findNameById(userId);
    }
}