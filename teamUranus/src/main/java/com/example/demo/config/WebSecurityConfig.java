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
public class WebSecurityConfig {

	/**
	 * Spring Securityのフィルターチェーンを構成します。
	 *
	 * @param http HttpSecurityオブジェクト
	 * @return SecurityFilterChainオブジェクト
	 * @throws Exception 例外が発生した場合
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// ログイン時に起動するURLの指定
		http.formLogin(
				login -> login
						.loginPage("/login")
						.loginProcessingUrl("/authenticate")
						.defaultSuccessUrl("/topMenu"));

		// URL認証設定
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login", "/newUser").permitAll()
				.requestMatchers("/static/**", "/img/**", "/css/**").permitAll() 
				// ダイレクトアクセス処理
				.requestMatchers("/topMenu", "/registration").authenticated()
//				.requestMatchers("/topMenu", "/registration").hasRole("USER")
		);

		return http.build();
	}

	/**
	 * パスワードのハッシュ化に使用するBCryptPasswordEncoderを提供します。
	 *
	 * @return BCryptPasswordEncoderオブジェクト
	 */
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
