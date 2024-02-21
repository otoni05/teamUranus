package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.IntroductionRepository;
import com.example.demo.Service.IntroductionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IntroductionController {

	@Autowired
	HttpSession session;
	
	@Autowired
	IntroductionRepository introductionRepository;
	
	@Autowired
	IntroductionService introductionService;
	
	@GetMapping(value = "/listDisp")
	public ModelAndView listDisp(ModelAndView mv) {
		
		mv.addObject("introductions", introductionService.findAll());
		mv.setViewName("listDisp");
		return mv;
	}
	
	
	

	
}
