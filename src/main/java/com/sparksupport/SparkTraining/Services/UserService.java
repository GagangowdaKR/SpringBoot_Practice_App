package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.User;
import com.sparksupport.SparkTraining.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
