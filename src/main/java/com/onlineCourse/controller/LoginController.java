package com.onlineCourse.controller;
import com.onlineCourse.entities.User;
import com.onlineCourse.repository.ContactUsRepository;

import com.onlineCourse.service.interfaces.EmailService;
import com.onlineCourse.service.interfaces.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Data
@Controller
@Slf4j
public class LoginController {

	@Autowired
	private ContactUsRepository contactUsRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	private UserService userService;

	@Autowired
	private CourseController courseController;

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

	@RequestMapping(value = "/do_login", method = RequestMethod.POST)
	public String loginUser(HttpSession session, @ModelAttribute("user") User user, Model model) {

		log.info("USER : " + user);
		boolean isValidUser = userService.isValidUser(user);

		if(isValidUser){
			log.info("Session User :  " + user);
			model.addAttribute("user ", user);
			session.setAttribute("user", user);
			session.setAttribute("name", user.getName());
			emailService.sendEmail(user.getEmail(),
					"Login Successful",
					"Dear "+user.getName()+","+"\n\n"
							+ "Congratulations! You have successfully logged in to Learning Kart.\n\n"
							+ "Thank you for choosing Learning Kart for your learning needs.\n\n"
							+ "Best regards,\n"
							+ "The Learning Kart Team ");

			//model.addAttribute("info", "Welcome "+ user.getName() + "!");
			log.info("Welcome "+ user.getName() + "!");
			return "index";
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
		return "index";
	}

}
