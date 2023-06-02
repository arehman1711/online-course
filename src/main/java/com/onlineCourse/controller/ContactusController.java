package com.onlineCourse.controller;


import com.onlineCourse.entities.ContactUs;
import com.onlineCourse.repository.ContactUsRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
@Controller
@Slf4j
public class ContactusController {

	@Autowired
	private ContactUsRepository contactUsRepository;
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
