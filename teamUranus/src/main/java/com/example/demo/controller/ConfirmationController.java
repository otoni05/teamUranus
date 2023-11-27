package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserData;

@Controller
public class ConfirmationController {

    @GetMapping("/confirmation")
    public String showConfirmationPage(Model model) {
    	
        // ここでユーザデータを取得し、確認画面に渡す
        model.addAttribute("userData", new UserData());
        return "confirmation";
    }

    @PostMapping("/confirmation")
    public String processConfirmationPage(UserData userData) {
        // 確認画面からのデータ処理を行う
        // 完了したら登録完了画面にリダイレクトする
        return "redirect:/registration";
    }
}
