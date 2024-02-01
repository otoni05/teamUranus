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

/**
 * ユーザー関連の操作を管理するためのControllerクラスです。
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新しいユーザーを作成するためのフォームを表示します。
     *
     * @param model ビューで使用されるモデル
     * @return 新しいユーザーフォームのビュー名
     */
    @GetMapping("/newUser")
    public String viewNewUser(Model model) {
        // 新しいユーザーフォームをモデルに追加
        model.addAttribute("newUser", new UserForm());
        // 新しいユーザーフォームのビューを表示
        return "newUser";
    }

    /**
     * 新しいユーザーの登録を処理します。
     *
     * @param userForm 登録フォームを介して提出されたユーザーフォームデータ
     * @param result   バリデーションエラーのためのバインディング結果
     * @return エラーがある場合は新しいユーザーフォーム、それ以外は正常に登録された場合のビュー名
     */
    @PostMapping("/newUser")
    public String register(@ModelAttribute("newUser") UserForm userForm, BindingResult result) {
        // ユーザーのバリデーションと保存を行うサービスメソッドを呼び出し
        userService.validateAndSaveUser(userForm, result);
        // エラーがある場合は新しいユーザーフォームのビュー、それ以外はログインページにリダイレクト
        return result.hasErrors() ? "newUser" : "redirect:/login";
    }

    /**
     * トップメニューのビューを表示します。
     *
     * @return トップメニューのビュー名
     */
    @GetMapping("/topMenu")
    public String viewTopMenu() {
        // トップメニューのビューを表示
    	System.out.println("topMenu");
        return "topMenu";
    }

    /**
     * 登録ページのビューを表示します。
     *
     * @return 登録ページのビュー名
     */
    @GetMapping("/registration")
    public String viewRegistration() {
        // 登録ページのビューを表示
    	System.out.println("registration");
        return "registration";
    }
}
