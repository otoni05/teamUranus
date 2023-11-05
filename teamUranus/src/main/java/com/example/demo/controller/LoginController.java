package com.example.demo.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginForm;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

	@PostMapping("/login")
    public String processLoginForm(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        // ログイン処理を行う

        return "redirect:/dashboard"; // ログイン成功後のページにリダイレクト
    }

}
