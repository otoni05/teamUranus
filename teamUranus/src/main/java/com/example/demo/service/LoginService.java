package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginForm;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean login(String userId, String password) {
        // ユーザ名とパスワードを使ってデータベースからユーザを取得
        LoginForm user = loginRepository.findByUserId(userId);

        // ユーザが存在し、パスワードが一致する場合はログイン成功
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        // それ以外の場合はログイン失敗
        return false;
    }
}
