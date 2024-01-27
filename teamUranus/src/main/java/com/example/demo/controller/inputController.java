package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserForm;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;


@Controller
public class inputController {

    private static final String UserForm = null;

	@Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    // ルートパスへのアクセス時に/inputへリダイレクト
    @GetMapping("/")
    public String redirectToInput() {
        return "redirect:/input";
    }

    // ログインフォームを表示
    @GetMapping("/input")
    public String showUserForm(Model model) {
        // モデルに"UserForm"属性として新しいUserFormオブジェクトを追加
        model.addAttribute("UserForm", new UserForm());
        return "input";
    }
    
    // ログインフォームの処理
    @PostMapping("/input")
    public String processInputForm(@ModelAttribute("UserForm") 
      @Valid UserForm userForm, BindingResult result, Model model) {
    	 userService.validateName(userForm.getName(), result);
         userService.validateFurigana(userForm.getFurigana(), result);
         userService.validateHobbies(userForm.getHobbies(), result);

        // UserFormのバリデーションを行う
        if (result.hasErrors()) {
            model.addAttribute("validationError", true);
            return "input";
        }

        // 名前、フリガナ、趣味の項目が正しいかどうかを確認
        String nameValidationError = userService.validateName(userForm.getName());
        String huriganaValidationError = userService.validateFurigana(userForm.getFurigana());
        String hobbiesValidationError = userService.validateHobbies(userForm.getHobbies());

        if (!nameValidationError.isEmpty() || !huriganaValidationError.isEmpty() || !hobbiesValidationError.isEmpty()) {
            // 名前、フリガナ、趣味のいずれかが正しくない場合
            model.addAttribute("validationError", true);
            model.addAttribute("nameError", nameValidationError);
            model.addAttribute("huriganaError", huriganaValidationError);
            model.addAttribute("hobbiesError", hobbiesValidationError);
            return "input";
        }

        // すべての項目が正しい場合は/confirmationにリダイレクト
        return "redirect:/confirmation";
    }

    // 登録画面を表示
    @GetMapping("/confirmation")
    public String viewRegistration() {
        // モデルに"confirmation"属性として新しいUserFormオブジェクトを追加
        return "confirmation";
    }
}
    
