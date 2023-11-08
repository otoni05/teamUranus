package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public void save(User user) {
    	// パスワードをハッシュ化してセット
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        userRepository.save(user);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean login(String userId, String plainPassword) {
        User user = userRepository.findByUserId(userId);

        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(plainPassword, user.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
