package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Introduction;
import com.example.demo.Repository.IntroductionRepository;


@Service
public class IntroductionService {
	
	@Autowired
	private IntroductionRepository introductionRepository;
	
	public List<Introduction> findAll(){
		return introductionRepository.findAll();
	}
	// IDに一致するものを取得
	public Optional<Introduction> findByUserId(String userId) {
		return introductionRepository.findByUserId(userId);
	}
	// 削除対象のIDに一致するデータを削除
	public void deleteIntroduction(Integer introductionId) {
	
		introductionRepository.deleteById(introductionId);
	}
}
