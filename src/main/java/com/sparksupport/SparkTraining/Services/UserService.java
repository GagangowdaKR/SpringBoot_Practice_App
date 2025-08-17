package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.User;
import com.sparksupport.SparkTraining.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public List<?> getLatestBorrowedBook() {
        return userRepository.findLatestBorrowedBook().stream()
                .map(anyType -> {
                    Object[] object = (Object[]) anyType;
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Member Name", String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    map.put("Issue Date", String.valueOf(object[2]));
                    return map;
                })
                .collect(Collectors.toList());
    }
}
