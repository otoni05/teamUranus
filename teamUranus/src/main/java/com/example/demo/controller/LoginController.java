package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.LoginForm;
import com.example.demo.model.UserForm;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		// ログインフォームをモデルに追加

		model.addAttribute("loginForm", new LoginForm());
		// ログインフォームのビューを表示
		return "login";
	}

	@PostMapping("/login")
	public String processLoginForm(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result,
			Model model) {
		
		// ユーザーログインのバリデーションを行うサービスメソッドを呼び出し
		userService.validateLoginUser(loginForm, result);

		if (result.hasErrors()) {
			// エラーがある場合はログインフォームのビューを表示
			model.addAttribute("login", loginForm);
			return "login";
		}
		
		// 入力されたログインIDに対応するユーザーを取得
		UserForm existingUser = userRepository.findByUserId(loginForm.getLoginId());
		// ユーザーが存在し、パスワードが正しい場合はトップメニューのビューを表示
		if (existingUser != null && userService.isPasswordValid(loginForm.getPassword(), existingUser.getPassword())) {
			// トップメニューのビューを表示
			return "registration";
		}
		
		// ログインに失敗した場合はログインフォームのビューを表示
		model.addAttribute("login", loginForm);
		// ログインフォームのビューを表示
		return "login";
	}
}
