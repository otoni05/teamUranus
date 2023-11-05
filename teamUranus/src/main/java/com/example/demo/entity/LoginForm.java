package com.example.demo.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity	
public class LoginForm {
		
	  	@Id
	  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // 一意の識別子として適切なフィールドを追加する
		
	 	@NotBlank(message = "User IDは必須項目です")
	    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "User IDは半角英数字のみで入力してください")
	    @Pattern(regexp = "^[^#\\$%&'\\(\\)=~]*$", message = "User IDには特殊記号を使用しないでください")
	    @Size(min= 3,max = 10, message = "User IDは10文字以内で入力してください")
	    private String userId;

	    @NotBlank(message = "PassWordは必須項目です")
	    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,10}$"
	    , message = "PassWordは大文字小文字数字を組み合わせて入力してください")
	    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "PassWordは半角英数字のみで入力してください")
	    @Pattern(regexp = "^[^#\\$%&'\\(\\)=~]*$", message = "PassWordには特殊記号を使用しないでください")
	    @Size(min= 3,max = 10, message = "PassWordは10文字以内で入力してください")
	    private String password;
}
