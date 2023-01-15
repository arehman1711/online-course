package com.onlineCourse.controller;


import com.onlineCourse.repository.UserRepository;
import com.onlineCourse.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.onlineCourse.entities.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

@Data
@Controller
@Slf4j
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseController courseController;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-online Course Management");
		log.info("Enter in Home!");
		return "home";

	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register-online Course Management");
		model.addAttribute("user", new User());
		return "signup";
	}
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "login-online Course Management");
		model.addAttribute("user", new User());
		return "login";
	}


	// hanlder for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(HttpSession session, @ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model) {
		if (!agreement) {
			log.info("You have not agreed the terms and conditions");
			return "signup";
		}
		log.info("USER " + user);
		boolean isDuplicateUser = userService.isValidUser(user);

		if(isDuplicateUser){
			return "signup";
		}
		User dbuser = userRepository.save(user);
		session.setAttribute("user", dbuser);
	    model.addAttribute("user ", dbuser);
		return "courses";
	}
	@RequestMapping(value = "/do_login", method = RequestMethod.POST)
	public String loginUser(HttpSession session, @ModelAttribute("user") User user, Model model) {

		log.info("USER " + user);
		boolean isValidUser = userService.isValidUser(user);

		if(isValidUser){
			model.addAttribute("user ", user);
			session.setAttribute("user", user);
			session.setAttribute("name", user.getName());
			return courseController.courses(model);
		}
		return "login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "home";
	}


}
