package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserForm;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class InputController {

    @Autowired
    private UserService userService;

    // 入力フォームを表示
    @GetMapping("/input")
    
    public String showUserForm(Model model, HttpSession session) {
    	
        // セッションからユーザーフォームを取得
        UserForm userForm = (UserForm) session.getAttribute("userForm");

        if (userForm == null) {
            // セッションにユーザーフォームがない場合は新しく作成
            userForm = new UserForm();
        }

        model.addAttribute("userForm", userForm);
        return "input";
    }

    // 入力フォームの処理
    @PostMapping("/input")
    public String processInputForm(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
                                   Model model, HttpSession session) {
    	
    	
        // バリデーションを実行
        userService.validateName(userForm.getName(), bindingResult);
        userService.validateKana(userForm.getKana(), bindingResult);
        userService.validateHobby(userForm.getHobby(), bindingResult);

        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            return "input";
        }

        // 入力データをセッションに保存
        session.setAttribute("userForm", userForm);

        // 入力データを確認画面に渡す
        model.addAttribute("userForm", userForm);
        return "confirmation";
    }
}
