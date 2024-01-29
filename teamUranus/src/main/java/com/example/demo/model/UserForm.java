package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報を表すデータクラスです。
 * Lombokの@Dataアノテーションを使用して、自動的にゲッター、セッター、toStringなどが生成されます。
 */
@Data
@Entity
@Table(name = "users")
public class UserForm {

	/**
     * ユーザーIDを保持するフィールド
     */
	@Id
	@Column(name = "user_id")
	private String userId;
	
	/**
     * メールアドレスを保持するフィールド
     */
	@Column(name = "email")
	private String email;
	
	/**
     * パスワードを保持するフィールド
     */
	@Column(name = "password")
	private String password;
	
	/**
     * ユーザー作成日時を保持するフィールド
     */
	@Column(name = "create_at")
	private Timestamp createDate;
}
