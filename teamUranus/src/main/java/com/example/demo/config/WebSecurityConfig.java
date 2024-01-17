package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/**
		 * ログイン時に起動するURLの指定 
		 */
		http.formLogin(
				login -> login
						.loginPage("/login")
						.loginProcessingUrl("/authenticate")
						.defaultSuccessUrl("/topMenu"));
		
		/**
		 * URL認証設定
		 */

		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/login", "/newUser","/registration" ,"/static/**", "/img/**", "/css/**").permitAll()
				.requestMatchers("/topMenu").authenticated() //ダイレクトアクセス処理
//				.requestMatchers("/registration").authenticated() //ダイレクトアクセス処理
				);
		return http.build();

	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
