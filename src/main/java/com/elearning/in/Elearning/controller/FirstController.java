package com.elearning.in.Elearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.in.Elearning.model.Available_Courses;
import com.elearning.in.Elearning.model.PaymentHistory;
import com.elearning.in.Elearning.model.Time_Slot;
import com.elearning.in.Elearning.model.User;
import com.elearning.in.Elearning.service.UserService;

@RestController
public class FirstController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestBody User user) {
		System.out.println(user);
		/*
		 * String name = user.getFirstname(); System.out.println(name);
		 */

		if ((user.getFirstname() != "") && (user.getlastname() != "")) {
			int value = userService.saveUser(user);
			String k = "user created succesfully !";
			if (value > 0) {
				System.out.println(value);
				System.out.println(k);
				return k;
			} else {
				return "user already exists";
			}
		} else {
			return "user not created";
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/timetable/{courseName}")
	@ResponseBody
	public List<Time_Slot> displayTimetable(@PathVariable("courseName") String courseName) {
		List<Time_Slot> li = userService.timeTable(courseName);
		System.out.println(li);
		return li;
	}

	@RequestMapping(value = "/loginSuccess")
	public void loginSuccess(Principal principal) {
		System.out.println("login sucess");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/payment_history")
	@ResponseBody
	public List<PaymentHistory> displaytable() {
		List<PaymentHistory> li = userService.payment_history();
		System.out.println(li);
		return li;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/available_courses")
	@ResponseBody
	public List<Available_Courses> displaytable1() {
		List<Available_Courses> li = userService.available_courses();
		System.out.println(li);
		return li;
	}

}
