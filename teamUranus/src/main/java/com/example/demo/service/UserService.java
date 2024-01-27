package com.example.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.example.demo.model.UserForm;
import com.example.demo.repository.UserRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Service
@Transactional
public class UserService<passwordEncoder> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    // ユーザー情報の保存メソッド
    public void save(UserForm userForm) {

        // バリデーション実行
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        if (violations.isEmpty()) {
            // バリデーションエラーがなければ保存
            userRepository.save(userForm);
        }
    }

    // ユーザー情報のバリデーションと保存メソッド
    public void validateAndSaveUser(UserForm userForm, BindingResult result) {
        // 各フィールドのバリデーションメソッド呼び出し
        validateName(userForm.getName(), result);
        validateFurigana(userForm.getFurigana(), result);
        validateHobbies(userForm.getHobbies(), result);

        if (!result.hasErrors()) {
            // バリデーションエラーがなければ保存
            Instant currentUtcInstant = Instant.now();
            Instant truncatedInstant = currentUtcInstant.truncatedTo(ChronoUnit.SECONDS);
            Timestamp currentUtcTimestamp = Timestamp.from(truncatedInstant);
            userForm.setCreateDate(currentUtcTimestamp);
            save(userForm);
        }
    }

    // 名前のバリデーションメソッド
    public String validateName(String name, BindingResult result) {
        if (name == null || name.trim().isEmpty()) {
            result.rejectValue("name", "error.user", "名前は必須項目です");
        } else if (name.length() > 10) {
            result.rejectValue("name", "error.user", "名前は10文字以内で入力してください");
        }
        return "";
    }

    // フリガナのバリデーションメソッド
    public String validateHurigana(String hurigana, BindingResult result) {
        if (hurigana == null || hurigana.trim().isEmpty()) {
            result.rejectValue("hurigana", "error.user", "フリガナは必須項目です");
        } else if (hurigana.length() > 20) {
            result.rejectValue("hurigana", "error.user", "フリガナは20文字以内で入力してください");
        }
        return "";
    }

    // 趣味のバリデーションメソッド
    public String validateHobbies(String[] hobbies, BindingResult result) {
        if (hobbies == null || hobbies.length == 0) {
            result.rejectValue("hobbies", "error.user", "趣味は必須項目です。1つ以上選んでください。");
        }
        return "";
    }



   

	public String validateName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String validateFurigana(String furigana) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String validateHobbies(String hobbies) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void validateHobbies(String hobbies, BindingResult result) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void validateFurigana(String furigana, BindingResult result) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}


