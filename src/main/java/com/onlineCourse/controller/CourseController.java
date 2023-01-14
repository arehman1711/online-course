package com.onlineCourse.controller;


import com.onlineCourse.entities.Course;
import com.onlineCourse.repository.UserRepository;
import com.onlineCourse.service.interfaces.CourseService;
import com.onlineCourse.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.onlineCourse.entities.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@Slf4j
public class CourseController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public String courses(Model model) {
		List<Course> courseList = courseService.getCourseList();
		log.info("courseList : " + courseList);
		model.addAttribute("title", "Courses");
		model.addAttribute("courseList", courseList);
		return "courses";
	}

	// hanlder for registering user
	@RequestMapping(value = "/do_xyz", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model) {
		if (!agreement) {
			log.info("You have not agreed the terms and conditions");
			return "courses";
		}
		log.info("USER " + user);
		boolean isDuplicateUser = userService.isValidUser(user);

		if(isDuplicateUser){
			return "sig";
		}
		User result = userRepository.save(user);
	    model.addAttribute("user ", result);
		return "courses";
	}


}
