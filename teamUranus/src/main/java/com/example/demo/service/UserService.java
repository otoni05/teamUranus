package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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

	public void save(@Validated UserForm userForm) {

		// パスワードをハッシュ化してセット
		String hashedPassword = passwordEncoder.encode(userForm.getPassword());

		userForm.setPassword(hashedPassword);

		 Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
		    if (!violations.isEmpty()) {
		        for (ConstraintViolation<UserForm> violation : violations) {
		            System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
		        }
		    }

		    if (violations.isEmpty()) {
		        userRepository.save(userForm);
		    }
	}
	
	public String validateUserId(String userId) {
		StringBuilder errorMessage = new StringBuilder();
		
		if (userId == null || userId.trim().isEmpty()) {
            errorMessage.append("User IDは必須項目です");
        } else if (userId.length() < 3 || userId.length() > 10) {
            errorMessage.append("User IDは10文字以内で入力してください");
        } else if (!userId.matches("^[ -~｡-ﾟ]*$")) {
            errorMessage.append("User IDは半角英数字のみで入力してください");
        } else if (!userId.matches("^[^#\\$%&'\\(\\)=~]*$")) {
            errorMessage.append("User IDには特殊記号を使用しないでください");
        }
		
		return errorMessage.toString();	
	}
	
	public String validateEmail(String email) {
		
		StringBuilder errorMessage = new StringBuilder();

        if (email == null || email.isEmpty()) {
            errorMessage.append("MailAddressは必須項目です");
        } else if (!email.matches("^[ -~｡-ﾟ]*$")) {
            errorMessage.append("MailAddressは半角英数字のみで入力してください");
        }else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errorMessage.append("正しいMailAddress形式で入力してください");
        }

        return errorMessage.toString();
	
	}
	
	public String validatePassword(String password) {
		 StringBuilder errorMessage = new StringBuilder();
	
		 if(password == null || password.trim().isEmpty()) {
			 errorMessage.append("PassWordは必須項目です");
			 
		 }else if (password.length() < 3 || password.length() > 10) {
		        errorMessage.append("PassWordは10文字以内、3文字以上で入力してください。");
		    } else {
		        // 特定の記号を排除することをチェック
		        boolean noSpecialCharacters = password.matches("[^#\\$%&'\\(\\)=~]*");
		        
		        // 特殊記号が含まれる場合のエラーメッセージを優先
		        if (!noSpecialCharacters) {
		            errorMessage.append("PassWordは特殊記号を使用しないでください。");
		        } else {
		            // 大文字、小文字、数字をそれぞれ1文字以上含むことをチェック
		            boolean hasUppercase = password.matches(".*[A-Z].*");
		            boolean hasLowercase = password.matches(".*[a-z].*");
		            boolean hasDigit = password.matches(".*\\d.*");
		            boolean noHalfSizeNumber = password.matches("[a-zA-Z0-9]+$");
		            
		            // 特殊記号以外の場合はエラーメッセージを追加しない
		            if (!hasUppercase || !hasLowercase || !hasDigit || !noHalfSizeNumber) {
		            	if (!noHalfSizeNumber) {
		                    errorMessage.append("PassWordは半角英数字のみで入力してください");
		                    
		                }else if (!hasUppercase || !hasLowercase || !hasDigit) {
		                    errorMessage.append("PassWordは大文字小文字数字を組み合わせて入力してください。");
		                }
		            }
		        }
		    }
		    return errorMessage.toString();
		}
		 

	public UserForm findByUserId(String userId) {
		//return userRepository.findByUserId(userId);
		System.out.println("Searching for user with ID: " + userId);
	    UserForm user = userRepository.findByUserId(userId);
	    if (user != null) {
	        System.out.println("Found user: " + user.getUserId());
	    } else {
	        System.out.println("User not found for ID: " + userId);
	    }
	    return user;
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
