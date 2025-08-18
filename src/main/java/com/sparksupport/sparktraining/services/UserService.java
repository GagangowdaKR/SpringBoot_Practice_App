package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.User;
import com.sparksupport.sparktraining.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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

    public User getUserById(Integer userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
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
}
