package com.onlineCourse.controller;


import com.onlineCourse.entities.Course;
import com.onlineCourse.repository.CourseRepository;
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
	private CourseRepository courseRepository;
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

	@PostMapping("/search")
	public String search( @RequestParam(value = "searchText") String searchText, Model model) {
		log.info("Search Criteria : " + searchText);
		List<Course> courseList = courseService.search(searchText);
		log.info("courseList : " + courseList);
		model.addAttribute("title", "Courses");
		model.addAttribute("courseList", courseList);
		return "courses";
	}
	@GetMapping("/coursedetail")	public String enroll(@ModelAttribute("user") Course course,  Model model) {
		//log.info("Course : " + course);
		//log.info("User : " + user);

		//model.addAttribute("user ",user);
		List<Course> courseList = courseService.getCourseList();
		log.info("courseList : " + courseList);
		model.addAttribute("title", "Courses");
		model.addAttribute("courseList", courseList);
		return "coursedetail" ;

	}
	@GetMapping("/mycourse")
	public String enrolluser(@ModelAttribute("user") User user,  Model model) {

		log.info(" USER : " +  user);
		return courses(model);
	}

	//@RequestMapping(value = "/enroll", method = RequestMethod.POST)
/*	public String enrolluser(@ModelAttribute("user") User user,  Model model) {

		log.info(" USER : " +  user);

		return courses(model);
	}*/

	//mycourses - User

	//courseDetail - Course




}
