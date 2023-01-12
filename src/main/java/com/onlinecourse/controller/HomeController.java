package com.onlinecourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-online Course Management");
		log.info("Enter in Home!");
		return "home";
		
	}
	@RequestMapping("/signup")
	public String signup(Model model) 
	{
		model.addAttribute("title","Register-online Course Management");
		model.addAttribute("user");
		return "signup";
	}
}
