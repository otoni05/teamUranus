package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserForm {

	@Id
	@Column(name = "name")
	private String name;
	
	@Column(name = "furigana")
	private String furigana;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "hobbies")
	private String hobbies;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "create_at")
	private Timestamp createDate;


}






