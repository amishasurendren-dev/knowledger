package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/nav")
public class NavigationController {
	
	@Autowired
	UserService uService;
	
	@Autowired
	CommentService cService;
	
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@PostMapping("/addUser")
	public String addUser(
    @RequestParam("name")String name,
    @RequestParam("email")String email,
    @RequestParam("password")String password,
    @RequestParam("role")String role)
	
	{
		boolean emailExists = uService.checkEmail(email);
		if(emailExists == false)
		{
			Users user = new Users();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);
			uService.addUser(user);
		    
			System.out.println("User registered successfully");			
			return "redirect:/nav/login";
	    }
		else
		{
            System.out.println("User already exists");			
			return "registerFail";
		}
	}
	
	
	@PostMapping("/validate")
    public String validate(
    @RequestParam("email")String email,
	@RequestParam("password")String password,
     HttpSession session) {

	if(uService.checkEmail(email)) 
	 {
	      boolean val=uService.validate(email, password);

	      //if user is valid
          if(val==true) 
          {
    	  Users user = uService.getUser(email); // Assuming you have a method to get the User object
    	  session.setAttribute("loggedInUser", user); // Saving the User object in session
                if(uService.getUserRole(email).equals("Student")) 
                   {
                    return "studentHome";
                   }
                else 
	               {
                    return "trainerHome";
                   } 
           }

	       else 
	       {
	       System.out.println("incorrect credentials, try again!");
           return "loginFail";
	       }
      }

	 else 
	 {
        return "loginFail";

	 }
	}
	
	
	@GetMapping("/createCourse")
	public String createCourse() {
	return "createCourse";

	}

	@GetMapping("/addLesson")
	public String addLesson() {
	return "addLesson";

	}
	
    @GetMapping("/about")
    public String about() {
        return "about"; 
    }
    
	
    @GetMapping("/services")
    public String services() {
        return "services"; 
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; 
    }


}
