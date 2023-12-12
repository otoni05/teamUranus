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
	public String processLoginForm(@Validated @ModelAttribute("loginForm") LoginForm loginForm
			,BindingResult bindingResult
			,Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
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

		// パスワードのバリデーションを行う
		String passwordErrorMessage = userService.validatePassword(loginForm.getPassword());
	    if (!passwordErrorMessage.isEmpty()) {
	        // パスワードのバリデーションエラーをBindingResultに追加
	        bindingResult.rejectValue("password", "error.user", passwordErrorMessage);
	    }
		return "login";
	}

	@GetMapping("/newUser")
	public String view(Model model) {
		model.addAttribute("newUser", new UserForm());
		return "newUser";
	}

	@PostMapping("/newUser")
	public String register(@Validated @ModelAttribute("newUser") UserForm newUser, BindingResult result, Model model) {
		// カスタムバリデーションの実行
	    String passwordErrorMessage = userService.validatePassword(newUser.getPassword());
	    if (!passwordErrorMessage.isEmpty()) {
	    	result.rejectValue("password", "error.user", passwordErrorMessage);
	    }
	    
	    // Emailが空かどうかをチェック
	    if(newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
	        result.rejectValue("email", "error.user", "MailAddressは必須項目です");
	    }
	    
	    // IDやメールアドレスのバリデーションエラーをチェック
	    boolean hasUserIdError = result.hasFieldErrors("userId");
	    boolean hasEmailError = result.hasFieldErrors("email");
	    System.out.println("Has field errors - User ID: " + hasUserIdError + ", Email: " + hasEmailError);
	    
	    // どれかエラーがある場合、登録画面に戻る
	    if (hasUserIdError || hasEmailError || result.hasFieldErrors("password")) {
	        return "newUser";
	    }
	    // 新規ユーザーをデータベースに保存
	    System.err.println("aa");
	    userService.save(newUser);
	    System.err.println("BB");
	    model.addAttribute("newUser", newUser); // ここでUserオブジェクトを追加する

	    return "redirect:/login";
	}

	@GetMapping("/topMenu")
	public String View() {
		return "topMenu";
	}
}
