package com.example.demo.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Course;
import com.example.demo.entity.Users;
import com.example.demo.service.TrainerService;
import com.example.demo.service.UserService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class OrderCreation {
	
	@Autowired
	UserService us;
	
	@Autowired
	TrainerService tService;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam("amount") int amount,
	                          @RequestParam("email") String email,
	                          @RequestParam("courseId") int courseId) {
	    attachCourse(email, courseId);
	    return "Course added to your account!🎉";
	}
	
	
	public String attachCourse(String email, int courseId) {
		
		
		Course course=tService.getCourse(courseId);
		Users user=us.getUser(email);
		
		user.getCourses().add(course);
		course.getUsers().add(user);
	
		us.updateUser(user);
		return tService.saveCourse(course);
	}
}
