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
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

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
	public String processLoginForm(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result,
			Model model) {
		userService.validateLoginUser(loginForm, result);

		if (result.hasErrors()) {
			model.addAttribute("login", loginForm);
			return "login";
		}

		UserForm existingUser = userRepository.findByUserId(loginForm.getLoginId());

		if (existingUser != null && userService.isPasswordValid(loginForm.getPassword(), existingUser.getPassword())) {
			return "/topMenu";
		}

		model.addAttribute("login", loginForm);
		return "redirect:/login";
	}

	@GetMapping("/newUser")
	public String view(Model model) {
		model.addAttribute("newUser", new UserForm());
		return "newUser";

	}

	@PostMapping("/newUser")
	public String register(@ModelAttribute("newUser") UserForm userForm, BindingResult result) {
		userService.validateAndSaveUser(userForm, result);
		return result.hasErrors() ? "newUser" : "redirect:/login";
	}

	@GetMapping("/topMenu")
	public String viewTopMenu() {
		return "topMenu";
	}
}
