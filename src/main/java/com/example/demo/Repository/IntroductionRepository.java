package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Introduction;

@Repository
public interface IntroductionRepository extends JpaRepository<Introduction, Integer> {
	// ユーザーIDと一致するデータを取得
	Optional<Introduction> findByUserId(String userId);

	
}
