


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Webセキュリティの設定を行うクラスです。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     *
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainオブジェクト
     * @throws Exception 例外が発生した場合
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        // ダイレクトアクセス処理
        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().authenticated());

        return http.build();
    }

    /**
     *
     * @return BCryptPasswordEncoderオブジェクト
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
