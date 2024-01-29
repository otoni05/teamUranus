package com.example.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.LoginForm;
import com.example.demo.model.UserForm;
import com.example.demo.repository.UserRepository;

import jakarta.validation.ConstraintViolation;

/**
 * ユーザーサービスの実装クラスです。
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ValidationChacker validationChacker;

	/**
	 * ユーザー情報を保存します。
	 *
	 * @param userForm 保存するユーザーフォーム
	 */
	public void save(@Validated UserForm userForm) {

		// パスワードをハッシュ化してセット
		String hashedPassword = passwordEncoder.encode(userForm.getPassword());

		userForm.setPassword(hashedPassword);
		// バリデーションを実行し、違反がなければデータベースに保存
		Set<ConstraintViolation<UserForm>> violations = validationChacker.validate(userForm);
		if (violations.isEmpty()) {
			userRepository.save(userForm);
		}
	}

	/**
	 * ユーザーフォームのバリデーションを実行し、ユーザーを保存します。
	 *
	 * @param userForm ユーザーフォーム
	 * @param result   バリデーション結果
	 */
	public void validateAndSaveUser(UserForm userForm, BindingResult result) {
		// ユーザーIDのバリデーションを実行し、エラーメッセージがあればリジェクト
		String userIdErrorMessage = validationChacker.validateUserId(userForm.getUserId());
		if (!userIdErrorMessage.isEmpty()) {
			result.rejectValue("userId", "error.user", userIdErrorMessage);
		}
		// メールアドレスのバリデーションを実行し、エラーメッセージがあればリジェクト
		String emailErrorMessage = validationChacker.validateEmail(userForm.getEmail());
		if (!emailErrorMessage.isEmpty()) {
			result.rejectValue("email", "error.user", emailErrorMessage);
		}
		// パスワードのバリデーションを実行し、エラーメッセージがあればリジェクト
		String passwordErrorMessage = validationChacker.validatePassword(userForm.getPassword());
		if (!passwordErrorMessage.isEmpty()) {
			result.rejectValue("password", "error.user", passwordErrorMessage);
		}

		if (!result.hasErrors()) {
			// 現在のUTCの瞬間を取得
			Instant currentUtcInstant = Instant.now();

			// 瞬間を秒までの精度に切り捨て
			Instant truncatedInstant = currentUtcInstant.truncatedTo(ChronoUnit.SECONDS);

			// InstantからTimestampへ変換
			Timestamp currentUtcTimestamp = Timestamp.from(truncatedInstant);

			// 新しいユーザーの作成日時を設定
			userForm.setCreateDate(currentUtcTimestamp);

			// 新規ユーザーをデータベースに保存
			save(userForm);
		}
	}

	/**
	 * ログインフォームのバリデーションを実行します。
	 *
	 * @param loginForm ログインフォーム
	 * @param result    バリデーション結果
	 */
	public void validateLoginUser(LoginForm loginForm, BindingResult result) {
		// ログインIDのバリデーションを実行し、エラーメッセージがあればリジェクト
		String loginIdErrorMessage = validationChacker.validateUserId(loginForm.getLoginId());
		if (!loginIdErrorMessage.isEmpty()) {
			result.rejectValue("loginId", "error.loginForm", loginIdErrorMessage);
		}
		
		// パスワードのバリデーションを実行し、エラーメッセージがあればリジェクト
		String passwordErrorMessage = validationChacker.validatePassword(loginForm.getPassword());
		if (!passwordErrorMessage.isEmpty()) {
			result.rejectValue("password", "error.loginForm", passwordErrorMessage);
		}
	}

	/**
	 * 生パスワードとエンコードされたパスワードが一致するかどうかを検証します。
	 *
	 * @param rawPassword      生のパスワード
	 * @param encodedPassword  エンコードされたパスワード
	 * @return 一致する場合はtrue、それ以外はfalse
	 */
	public boolean isPasswordValid(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
