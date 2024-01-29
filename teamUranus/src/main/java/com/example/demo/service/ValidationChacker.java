package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserForm;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * バリデーション関連の機能を提供するサービスクラスです。
 */
@Component
public class ValidationChacker {

	@Autowired
	private Validator validator;

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
	 * ユーザーIDのバリデーションを行います。
	 *
	 * @param userId バリデーション対象のユーザーID
	 * @return エラーメッセージ
	 */
	public String validateUserId(String userId) {
		StringBuilder errorMessage = new StringBuilder();

		// ユーザーIDがnullまたは空文字の場合、必須項目エラーを追加
		if (userId == null || userId.trim().isEmpty()) {
			errorMessage.append("User IDは必須項目です");
		} else if (userId.length() < 3 || userId.length() > 10) {
			// ユーザーIDの長さが指定範囲外の場合、長さエラーを追加
			errorMessage.append("User IDは10文字以内で入力してください");
		} else if (!userId.matches("^[ -~｡-ﾟ]*$")) {
			// ユーザーIDが半角英数字でない場合、半角英数字エラーを追加
			errorMessage.append("User IDは半角英数字のみで入力してください");
		} else if (!userId.matches("^[^#\\$%&'\\(\\)=~]*$")) {
			// ユーザーIDが特殊記号を含む場合、特殊記号エラーを追加
			errorMessage.append("User IDには特殊記号を使用しないでください");
		}

		return errorMessage.toString();
	}

	/**
	 * メールアドレスのバリデーションを行います。
	 *
	 * @param email バリデーション対象のメールアドレス
	 * @return エラーメッセージ
	 */
	public String validateEmail(String email) {

		StringBuilder errorMessage = new StringBuilder();

		// メールアドレスがnullまたは空文字の場合、必須項目エラーを追加
		if (email == null || email.isEmpty()) {
			errorMessage.append("MailAddressは必須項目です");
		} else if (!email.matches("^[ -~｡-ﾟ]*$")) {
			// メールアドレスが半角英数字でない場合、半角英数字エラーを追加
			errorMessage.append("MailAddressは半角英数字のみで入力してください");
		} else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			// メールアドレスが正しい形式でない場合、形式エラーを追加
			errorMessage.append("正しいMailAddress形式で入力してください");
		}
		return errorMessage.toString();
	}

	/**
	 * パスワードのバリデーションを行います。
	 *
	 * @param password バリデーション対象のパスワード
	 * @return エラーメッセージ
	 */
	public String validatePassword(String password) {
		StringBuilder errorMessage = new StringBuilder();

		// パスワードがnullまたは空白の場合、必須項目エラーを追加
		if (password == null || password.trim().isEmpty()) {
			errorMessage.append("PassWordは必須項目です");
		} else if (password.length() < 3 || password.length() > 10) {
			// パスワードの長さが指定範囲外の場合、長さエラーを追加
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
						// 半角英数字以外が含まれる場合、エラーメッセージを追加
						errorMessage.append("PassWordは半角英数字のみで入力してください");

					} else if (!hasUppercase || !hasLowercase || !hasDigit) {
						// 大文字、小文字、数字がそれぞれ1文字以上含まれない場合、エラーメッセージを追加
						errorMessage.append("PassWordは大文字小文字数字を組み合わせて入力してください。");
					}
				}
			}
		}
		// エラーメッセージを文字列に変換して返す
		return errorMessage.toString();
	}
}
