package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "introductions")
public class Introduction{
	// 管理番号
	@Id
	@Column(name = "introduction_id")
	private Integer introductionId;
	
	// ユーザーID
	@Column(name = "user_id")
	private String userId;
	
	// 名前
	@Column(name = "name")
	private String name;
	
	// フリガナ
	@Column(name = "kana")
	private String kana;
	
	// 性別
	@Column(name = "gender")
	private String gender;
	
	// 趣味
	@Column(name = "hobby")
	private String hobby;
	
	// 一言
	@Column(name = "word")
	private String word;
	
	// 登録日時
	@Column(name = "created_at")
	private Date createdAt;
	
	// 更新日時
	@Column(name = "updated_at")
	private Date updatedAt;
	
	public Integer getIntroductionId() {
		return introductionId;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getKana() {
		return kana;
	}
	public String getGender() {
		return gender;
	}
	public String getHobby() {
		return hobby;
	}
	public String getWord() {
		return word;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
}