package com.example.demo.model;

import lombok.Data;

/**
 * ログインフォームを表すデータクラスです。
 * Lombokの@Dataアノテーションを使用して、自動的にゲッター、セッター、toStringなどが生成されます。
 */
@Data
public class LoginForm {
	// ログインIDを保持するフィールド
	private String loginId;
	// パスワードを保持するフィールド
	private String password;
}
