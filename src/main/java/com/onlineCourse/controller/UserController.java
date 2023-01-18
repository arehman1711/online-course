package com.onlineCourse.controller;

import com.onlineCourse.entities.User;
import com.onlineCourse.repository.UserRepository;
import com.onlineCourse.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseController courseController;

    @GetMapping(value = "/profile")
    public String profile(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "profile";
    }

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public String updateUser(HttpSession session, @ModelAttribute("user") User user, Model model) {
        User sessionUser = (User) session.getAttribute("user");
        log.info("USER : " + user);
        log.info("sessionUser : " + user);
        if(sessionUser.getEmail().equals(user.getEmail())){
            user.setId(sessionUser.getId());
            userRepository.save(user);
            session.setAttribute("user", user);
            session.setAttribute("name", user.getName());
            model.addAttribute("success", "Profile updated successfully.");
            log.info("Profile updated successfully.");
            return "home";
        }
        model.addAttribute("error", "Email update not allowed.");
        log.info("Email update not allowed.");
        return "profile";
    }

    @GetMapping(value = "/delete-profile")
    public String deleteUser(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("user");
        log.info("sessionUser : " + sessionUser);
        userRepository.deleteById(sessionUser.getId());
        session.invalidate();
        model.addAttribute("success", "Profile deleted successfully.");
        log.info("Profile deleted successfully.");
        return "home";
    }
}
