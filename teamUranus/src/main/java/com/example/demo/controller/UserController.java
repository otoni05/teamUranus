package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.LoginForm;
import com.example.demo.model.UserForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String processLoginForm(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
			 BindingResult result, Model model) {
		
		String loginIdErrorMessage = userService.validateUserId(loginForm.getLoginId());
		if (!loginIdErrorMessage.isEmpty()) {
		    result.rejectValue("loginId", "error.loginForm", loginIdErrorMessage);
		    System.out.println("Login ID Validation Error: " + loginIdErrorMessage);
		}

		// パスワードのバリデーションを行う
		String passwordErrorMessage = userService.validatePassword(loginForm.getPassword());
		if (!passwordErrorMessage.isEmpty()) {
		    // パスワードのバリデーションエラーをBindingResultに追加
		    result.rejectValue("password", "error.loginForm", passwordErrorMessage);
		    System.out.println("Password Validation Error: " + passwordErrorMessage);
		}
		
		// どれかエラーがある場合、登録画面に戻る
		if (result.hasFieldErrors("loginId") || result.hasFieldErrors("password")) {
			return "redirect:/login";
		}
		
		// ログイン処理
		UserForm existingUser = userService.findByUserId(loginForm.getLoginId());
		
		if (existingUser != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (passwordEncoder.matches(loginForm.getPassword(), existingUser.getPassword())) {
				// ログイン成功
				
				return "redirect:/topMenu"; // ログイン成功時には/topMenuにリダイレクト
			}
		}
		return "redirect:/login";
	}

	@GetMapping("/newUser")
	public String view(Model model) {
		model.addAttribute("newUser", new UserForm());
		return "newUser";
	}
	

	@PostMapping("/newUser")
	public String register(@Validated @ModelAttribute("newUser") UserForm newUser, BindingResult result, Model model) {
		// カスタムバリデーションの実行
		String userIdErrorMessage = userService.validateUserId(newUser.getUserId());
		if (!userIdErrorMessage.isEmpty()) {
			result.rejectValue("userId", "error.user", userIdErrorMessage);
		}

		String emailErrorMessage = userService.validateEmail(newUser.getEmail());
		if (!emailErrorMessage.isEmpty()) {
			result.rejectValue("email", "error.user", emailErrorMessage);
		}

		String passwordErrorMessage = userService.validatePassword(newUser.getPassword());
		if (!passwordErrorMessage.isEmpty()) {
			result.rejectValue("password", "error.user", passwordErrorMessage);
		}

		// どれかエラーがある場合、登録画面に戻る
		if (result.hasFieldErrors("userId") || result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
			return "newUser";
		}

		// 新規ユーザーをデータベースに保存
		userService.save(newUser);

		model.addAttribute("newUser", newUser); // ここでUserオブジェクトを追加する

		return "redirect:/login";
	}

	@GetMapping("/topMenu")
	public String View() {
		return "topMenu";
	}
}
