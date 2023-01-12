package com.onlinecourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.onlinecourse.entities.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-online Course Management");
		log.info("Enter in Home!");
		return "home";
		
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register-online Course Management");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	//hanlder for registering user
	@PostMapping("/do_register")
	public String registerUser() {
		return "signup";
	}
}
