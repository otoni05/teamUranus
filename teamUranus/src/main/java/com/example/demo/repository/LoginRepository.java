package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginForm;


public interface LoginRepository extends JpaRepository<LoginForm, Long> {
	 LoginForm findByUserId(String userId);
}