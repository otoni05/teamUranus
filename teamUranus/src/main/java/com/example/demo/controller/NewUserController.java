package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.NewUser;
import com.example.demo.service.UserService;

@Controller
public class NewUserController {
	
	@Autowired
	private UserService userService;
	 
	@GetMapping("/newUser")
	public String view(Model model) {
	    model.addAttribute("newUser", new NewUser()); 
	    return "newUser";
	}
	
	@PostMapping("/newUser")
    public String register(Model model, @Valid NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        
        // 新規ユーザーをデータベースに保存
        userService.save(newUser);
        
        return "redirect:/login";
    }
}
