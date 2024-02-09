package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 */
@Data
@Entity
@Table(name = "introductions")
public class UserForm {
	

    /**
     * 自己紹介IDを保持するフィールド
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "introduction_id")
    private Long introductionId;

    /**
     * ユーザーIDを保持するフィールド
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 氏名を保持するフィールド
     */
    @Column(name = "name", length = 10, nullable = false)
    private String name;

    /**
     * フリガナを保持するフィールド
     */
    @Column(name = "kana", length = 20, nullable = false)
    private String kana;

    /**
     * 性別を保持するフィールド
     */
    @Column(name = "gender")
    private String gender;

    /**
     * 趣味を保持するフィールド
     */
    @Column(name = "hobby", length = 60, nullable = false)
    private String hobby;

    /**
     * 一言を保持するフィールド
     */
    @Column(name = "word", length = 300)
    private String word;

    /**
     * チーム名を保持するフィールド
     */
    @Column(name = "team_name", length = 30, nullable = false)
    private String teamName;

    /**
     * 登録日を保持するフィールド
     */
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    /**
     * 更新日を保持するフィールド
     */
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
