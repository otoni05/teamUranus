package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserForm {

	@Id
	@Column(name = "user_id")
	@NotBlank(message = "User IDは必須項目です")
	@Pattern(regexp = "^[ -~｡-ﾟ]*$", message = "User IDは半角英数字のみで入力してください")
	@Pattern(regexp = "^[^#\\$%&'\\(\\)=~]*$", message = "User IDには特殊記号を使用しないでください")
	@Size(min= 3, max = 10, message = "User IDは10文字以内で入力してください")
	private String userId;
	
	@Column(name = "email")
	@NotBlank(message = "MailAddressは必須項目です")
	@Email(message = "正しいMailAddress形式で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
	, message = "MailAddressは半角英数字のみで入力してください")
	private String mailAddress;

	@Column(name = "password")
	@NotBlank(message = "PassWordは必須項目です")
	@Pattern(regexp = "^[ -~｡-ﾟ]*$", message = "PassWordは半角英数字のみで入力してください")
	private String password;
	
}
