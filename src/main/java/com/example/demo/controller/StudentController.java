package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Users;
import com.example.demo.service.CommentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TrainerService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	StudentService service;
	
	@Autowired
	UserService us;
	
	@Autowired
	TrainerService tService;
	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId")int lessonId,
							Model model,HttpSession session) {
		
		
		Lesson lesson = service.getLesson(lessonId);
		// Extract the YouTube video id from the URL
	    String youtubeUrl = lesson.getLink();
	    
	    String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
	    lesson.setLink(videoId);
		
		
		model.addAttribute("lesson",lesson);
		
		return "myLesson";
	}


	@GetMapping("/purchase")
	public String showCourses(Model model,HttpSession session) {
		Users user = (Users) session.getAttribute("loggedInUser");

		List<Course> courseList=tService.courseList();
		model.addAttribute("courseList",courseList);
		model.addAttribute("loggedInUser",user);
		return "purchase";
	}
	
	
	@GetMapping("/fetchCourses")
	public String fetchCourses(Model model, HttpSession session) {
		
		Users loggedUser=(Users) session.getAttribute("loggedInUser");
		
		String email=loggedUser.getEmail();
				
		Users user=us.getUser(email);
			
		List<Course> courseList=user.getCourses();
		model.addAttribute("courseList",courseList);
		
		
		return "myCourses";
	}
	
	
}
