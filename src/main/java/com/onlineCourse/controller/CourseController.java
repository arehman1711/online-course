package com.onlineCourse.controller;


import com.onlineCourse.entities.ContactUs;
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

import javax.servlet.http.HttpSession;
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
		return "courses/courses";
	}

	@PostMapping("/search")
	public String search( @RequestParam(value = "searchText") String searchText, Model model) {
		log.info("Search Criteria : " + searchText);
		List<Course> courseList = courseService.search(searchText);
		log.info("courseList : " + courseList);
		model.addAttribute("title", "Courses");
		model.addAttribute("courseList", courseList);
		return "courses/courses";
	}
	@GetMapping("/coursedetail")
	public String courseDetail(@ModelAttribute("user") Course course,  Model model) {
		//log.info("Course : " + course);
		//log.info("User : " + user);

		//model.addAttribute("user ",user);
		List<Course> courseList = courseService.getCourseList();
		log.info("courseList : " + courseList);
		model.addAttribute("title", "Courses");
		model.addAttribute("courseList", courseList);
		return "courses/course-detail" ;

	}
	@GetMapping("/mycourse")
	public String mycourse(@ModelAttribute("user") User user,  Model model) {
		log.info(" USER : " +  user);
		return courses(model);
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	public String enrolluser(HttpSession session, @ModelAttribute("enrolledCourse") Course course, @RequestParam(value = "id", defaultValue = "0") Integer courseId, Model model) {
		log.info("Course for Enrollment : " +  course);
		User sessionUser = (User) session.getAttribute("user");
		List<Course> courseList = new ArrayList<>();
		courseList.add(course);
		sessionUser.setCourseList(courseList);
		User user = userService.enroll(sessionUser);
		//log.info("User Data after Enrollment : " +  course);
		model.addAttribute("success", sessionUser.getName() + " successfully enrolled for " + course.getCourseName());
		log.info("success" +  sessionUser.getName() + " successfully enrolled for " + course.getCourseName());
		return courses(model);
	}

	@GetMapping(value = "/init-add-course")
	public String initAddCourse(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("title", "Add Course");
		log.info("loading init-add-course..!");
		return "courses/add-course";
	}

	@RequestMapping(value = "/submit-add-course", method = RequestMethod.POST)
	public String submitAddCourse(@ModelAttribute("course") Course course, Model model) {
		Course dbCourse = courseRepository.save(course);
		model.addAttribute("success", dbCourse.getCourseName() + " added successfully.");
		log.info("success - " + course.getCourseName() + " added successfully. Course =  : " + dbCourse);
		return initAddCourse(model);
	}

	@RequestMapping(value = "/init-manage-course", method = RequestMethod.POST)
	public String initManageCourse(@ModelAttribute("course") Course course, Model model) {
		model.addAttribute("course", course);
		model.addAttribute("title", "Add Course");
		log.info("loading init-manage-course..! course=" + course);
		return "courses/manage-course";
	}

	//mycourses - User

	//courseDetail - Course

}
