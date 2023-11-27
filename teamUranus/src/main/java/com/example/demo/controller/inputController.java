package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserData;
import com.example.demo.service.UserService;


@Controller
public class inputController {

 // UserServiceのフィールド
 private final UserService userService;

 // UserServiceを引数に取るコンストラクタ
 public inputController(UserService userService) {
     this.userService = userService;
 }

 @GetMapping("/input")
 public String showInputForm(Model model) {
	 
     // モデルに"userData"属性として新しいUserDataオブジェクトを追加
     model.addAttribute("userData", new UserData());
     return "input";
 }

 @PostMapping("/input")
 public String processInputForm(UserData userData, Model model) {
	 
     // UserDataのバリデーションを行うUserServiceのメソッドを呼び出し
     if (userService.validateUserData(userData)) {
    	 
         // バリデーションが成功した場合、モデルに"userData"属性として"confirmation"を返す
         model.addAttribute("userData", userData);
         return "confirmation";
     } else {
    	 
         // バリデーションが失敗した場合、モデルに"validationError"属性としてtrueを追加し、"input"を返す
         model.addAttribute("validationError", true);
         return "input";
     }
 }
}
