package com.onlineCourse.controller;


import com.onlineCourse.entities.ContactUs;
import com.onlineCourse.repository.ContactUsRepository;
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
	private ContactUsRepository contactUsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseController courseController;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home");
		log.info("Enter in Home!");
		return "home";

	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Sign Up");
		model.addAttribute("user", new User());
		return "sign-up";
	}
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		model.addAttribute("user", new User());
		return "login";
	}


	// hanlder for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(HttpSession session, @ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model) {
		if (!agreement) {
			log.info("You have not agreed the terms and conditions.");
			model.addAttribute("error", "You have not agreed the terms and conditions.");
			return "sign-up";
		}
		log.info("USER " + user);
		boolean isDuplicateUser = userService.isUserAlreadyExists(user.getEmail());

		if(isDuplicateUser){
			model.addAttribute("error", "User already exists in database.");
			log.info("User already exists in database.");
			return "sign-up";
		}
		User dbUser = userRepository.save(user);
		session.setAttribute("user", dbUser);
	    model.addAttribute("user", dbUser);
		session.setAttribute("name", dbUser.getName());
		model.addAttribute("info", "Welcome "+ dbUser.getName() + "!");
		model.addAttribute("success", "User registered successfully.");
		log.info("User "+ dbUser.getName() + " successfully Registered.");
		return "home";
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
			model.addAttribute("info", "Welcome "+ user.getName() + "!");
			log.info("Welcome "+ user.getName() + "!");
			return courseController.courses(session, model);
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

	@GetMapping(value = "/contactus")
	public String contactus(Model model) {
		model.addAttribute("contactUs", new ContactUs());
		model.addAttribute("title", "Contact US");
		log.info("loading Contact us..!");
		return "contact-us";
	}

	@RequestMapping(value = "/submit-contactus", method = RequestMethod.POST)
	public String submitContactus(@ModelAttribute("contactUs") ContactUs contactUs, Model model) {
		contactUsRepository.save(contactUs);
		model.addAttribute("success", "Message sent successfully.");
		log.info("Message sent successfully.");
		return "home";
	}

}
