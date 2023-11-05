package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NewUser;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(NewUser user) {
        userRepository.save(user);
    }

    public NewUser findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}