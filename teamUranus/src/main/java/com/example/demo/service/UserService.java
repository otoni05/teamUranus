package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserData;

@Service
public class UserService {
    
    public boolean validateUserData(UserData userData) {
        // バリデーションを実装 　名前、ふりがなが空でないか確認
        return !userData.getName().isEmpty() && !userData.getFurigana().isEmpty();
    }
}
