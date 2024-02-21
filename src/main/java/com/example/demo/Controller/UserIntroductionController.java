package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.IntroductionRepository;
import com.example.demo.Service.IntroductionService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserIntroductionController {
	@Autowired
	HttpSession session;

	@Autowired
	IntroductionRepository userIntroductionRepository;
	
	@Autowired
	IntroductionService introductionService;
// 詳細画面に移ったとき
	@GetMapping(value = "detailDisp/{id}")
	public ModelAndView detailDisp(ModelAndView mv, @PathVariable("id") String userId) {
		// パラメータとして持ってきたidと一致するデータを取得
		mv.addObject("userIntroductions", introductionService.findByUserId(userId).get());
		
		mv.setViewName("detailDisp");
		return mv;
	}
// 削除ボタンで遷移
	@GetMapping(value = "detailDisp/{id}/delete")
	public String delete(ModelAndView mv,@PathVariable("id") Integer introductionId) {
		// 持ってきたidと一致するデータを削除
		introductionService.deleteIntroduction(introductionId);
		// 一覧画面に戻る
		return "redirect:/listDisp";
				}
	
}
