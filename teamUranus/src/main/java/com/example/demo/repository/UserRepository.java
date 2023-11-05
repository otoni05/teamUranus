package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NewUser;


public interface UserRepository extends JpaRepository<NewUser, Long> {
    NewUser findByUserId(String userId);
}