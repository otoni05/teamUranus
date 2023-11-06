package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean login(String userId, String password) {
        User user = userRepository.findByUserId(userId);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
