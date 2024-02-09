package com.example.demo.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.model.UserForm;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * バリデーション関連の機能を提供するサービスクラスです。
 */
@Component
public class ValidationChacker {

    private final Validator validator;

    public ValidationChacker(Validator validator) {
        this.validator = validator;
    }

	/**
	 * ユーザーフォームのバリデーションを行います。
	 *
	 * @param userForm バリデーション対象のユーザーフォーム
	 * @return バリデーション結果のセット
	 */
	public Set<ConstraintViolation<UserForm>> validate(UserForm userForm) {
		return validator.validate(userForm);
	}

	/**
	 * 自己紹介情報のバリデーションを行います。
	 *
	 * @param userForm バリデーション対象の自己紹介情報
	 * @return エラーメッセージ
	 */
	public String validateIntroduction(UserForm userForm) {
		StringBuilder errorMessage = new StringBuilder();

		// 名前のバリデーション
		String nameValidationError = validateName(userForm.getName());
		if (!nameValidationError.isEmpty()) {
			errorMessage.append(nameValidationError).append("\n");
		}

		// フリガナのバリデーション
		String kanaValidationError = validateKana(userForm.getKana());
		if (!kanaValidationError.isEmpty()) {
			errorMessage.append(kanaValidationError).append("\n");
		}

		// 趣味のバリデーション
		String hobbyValidationError = validateHobby(userForm.getHobby());
		if (!hobbyValidationError.isEmpty()) {
			errorMessage.append(hobbyValidationError).append("\n");
		}

		return errorMessage.toString().trim();
	}

	/**
	 * 名前のバリデーションを行います。
	 *
	 * @param name バリデーション対象の名前
	 * @return エラーメッセージ
	 */
	private String validateName(String name) {
		StringBuilder errorMessage = new StringBuilder();

		if (name == null || name.trim().isEmpty()) {
			errorMessage.append("名前は必須項目です。");
		} else if (name.length() > 10) {
			errorMessage.append("名前は10文字以内で入力してください。");
		}

		return errorMessage.toString();
	}

	/**
	 * フリガナのバリデーションを行います。
	 *
	 * @param kana バリデーション対象のフリガナ
	 * @return エラーメッセージ
	 */
	private String validateKana(String kana) {
		
		System.out.println(kana);
		StringBuilder errorMessage = new StringBuilder();

		if (kana == null || kana.trim().isEmpty()) {
			errorMessage.append("フリガナは必須項目です。");
		} else if (kana.length() > 20) {
			errorMessage.append("フリガナは20文字以内で入力してください。");
		}

		return errorMessage.toString();
	}

	/**
	 * 趣味のバリデーションを行います。
	 *
	 * @param hobby バリデーション対象の趣味
	 * @return エラーメッセージ
	 */
	private String validateHobby(String hobby) {
		StringBuilder errorMessage = new StringBuilder();

		if (hobby == null || hobby.trim().isEmpty()) {
			errorMessage.append("趣味は必須項目です。1つ以上選んでください。");
		}

		return errorMessage.toString();
	}
}
