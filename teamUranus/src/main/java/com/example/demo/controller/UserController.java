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

	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    model.addAttribute("loginForm", new LoginForm());
	    return "login";
	}
	
	
	@PostMapping("/login")
	public String processLoginForm(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult) {
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

	    // ログイン失敗
	    bindingResult.rejectValue("loginId", "error.user", "ログイン情報が正しくありません");
	    return "login";
	}
	
    @GetMapping("/newUser")
    public String view(Model model) {
        model.addAttribute("newUser", new UserForm());
        return "newUser";
    }
    
    @PostMapping("/newUser")
    public String register(@Validated @ModelAttribute("newUser") UserForm newUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newUser";
        }
        
     // パスワードのバリデーションを行う
        if (!userService.isPasswordValid(newUser.getPassword())) {
            result.rejectValue("password", "error.user", "PassWordは指定された条件を満たしていません");
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


