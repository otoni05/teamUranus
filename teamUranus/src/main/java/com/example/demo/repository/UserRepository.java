package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserForm;

@Repository
public interface UserRepository extends JpaRepository<UserForm, String> {
    UserForm findByUserId(Long userId); 
}