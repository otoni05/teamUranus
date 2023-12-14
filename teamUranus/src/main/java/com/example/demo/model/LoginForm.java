package com.example.demo.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {
	
    private String loginId;
	
    @Size(max = 255)
	private String password;
}
