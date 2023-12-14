package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserForm;


public interface UserRepository extends JpaRepository<UserForm, String> {
    UserForm findByUserId(String userId); 
}