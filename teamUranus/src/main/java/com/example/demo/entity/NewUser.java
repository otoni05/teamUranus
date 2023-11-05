package com.example.demo.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class NewUser {
//	 	@Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private Long id;

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

	    @NotBlank(message = "MailAddressは必須項目です")
	    @Email(message = "正しいMailAddress形式で入力してください")
	    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "MailAddressは半角英数字のみで入力してください")
	    private String mailAddress;
}
