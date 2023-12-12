package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserForm;
import com.example.demo.repository.UserRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;



@Service
@Transactional 
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private Validator validator;
    
    public void save(UserForm userForm) {
    	
    	// パスワードをハッシュ化してセット
        String hashedPassword = passwordEncoder.encode(userForm.getPassword());
        
        userForm.setPassword(hashedPassword);
        
    	Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
    	
    	if (!violations.isEmpty()) {
    	    for (ConstraintViolation<UserForm> violation : violations) {
    	        System.out.println(violation.getMessage()); // エラーメッセージをログに出力する例
    	    }
    	}else {
    		 userRepository.save(userForm);
    	}
    }
    
    public boolean isPasswordValid(String password) {
    	
        // 大文字、小文字、数字をそれぞれ1文字以上含むことをチェック
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        
        // 特定の記号を排除することをチェック
        boolean noSpecialCharacters = password.matches("[^#\\$%&'\\(\\)=~]*");

        // すべての条件を満たすかどうかを返す
        return hasUppercase && hasLowercase && hasDigit && noSpecialCharacters;
    }
    
    
    public UserForm findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    
    public boolean login(String userId, String plainPassword) {
        UserForm user = userRepository.findByUserId(userId);

        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(plainPassword, user.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
