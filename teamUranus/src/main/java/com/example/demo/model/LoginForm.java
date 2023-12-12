package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotBlank(message = "User IDは必須項目です")
	@Pattern(regexp = "^[ -~｡-ﾟ]*$", message = "User IDは半角英数字のみで入力してください")
	@Size(min= 3, max = 10, message = "User IDは10文字以内で入力してください")
	@Pattern(regexp = "^[^#\\$%&'\\(\\)=~]*$", message = "User IDには特殊記号を使用しないでください")
    private String loginId;
	
	@NotBlank(message = "PassWordは必須項目です") 
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "PassWordは半角英数字のみで入力してください")
	private String password;
}
