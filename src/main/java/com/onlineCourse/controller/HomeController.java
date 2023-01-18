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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			log.info("You have not agreed the terms and conditions.");
			model.addAttribute("error", "You have not agreed the terms and conditions.");
			return "signup";
		}
		log.info("USER " + user);
		boolean isDuplicateUser = userService.isUserAlreadyExists(user.getEmail());

		if(isDuplicateUser){
			model.addAttribute("error", "User already exists in database.");
			log.info("User already exists in database.");
			return "signup";
		}
		User dbUser = userRepository.save(user);
		session.setAttribute("user", dbUser);
	    model.addAttribute("user", dbUser);
		session.setAttribute("name", dbUser.getName());
		model.addAttribute("success", "Welcome "+ dbUser.getName() + "!");
		log.info("Welcome "+ dbUser.getName() + "!");
		return "courses";
	}
	@RequestMapping(value = "/do_login", method = RequestMethod.POST)
	public String loginUser(HttpSession session, @ModelAttribute("user") User user, Model model) {

		log.info("USER : " + user);
		boolean isValidUser = userService.isValidUser(user);

		if(isValidUser){
			log.info("Session User :  " + user);
			model.addAttribute("user ", user);
			session.setAttribute("user", user);
			session.setAttribute("name", user.getName());
			model.addAttribute("success", "Welcome "+ user.getName() + "!");
			log.info("Welcome "+ user.getName() + "!");
			return courseController.courses(model);
		}
		log.info("Invalid email/password.");
		model.addAttribute("error", "Invalid email/password.");
		return "login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("success", "Logged out successfully.");
		log.info("Logged out successfully.");
		return "home";
	}

}
