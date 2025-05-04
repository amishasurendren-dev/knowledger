package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.service.TrainerService;

@Controller
public class TrainerController {

@Autowired
TrainerService tService;

@PostMapping("/addCourse")
public String addCourse(
        @RequestParam("courseName") String courseName,
        @RequestParam("coursePrice") int coursePrice,
        Model model) {

    Course existingCourse = tService.getCourseByName(courseName);

    if (existingCourse != null) {
        model.addAttribute("errorMessage", "Course already exists!");
        return "trainerHome";  // Return to the same page
    }

    Course course = new Course();
    course.setCourseName(courseName);
    course.setCoursePrice(coursePrice);

    Course c = tService.addCourse(course);

    if (c != null) {
        model.addAttribute("successMessage", "Course is added successfully!");
    } else {
        model.addAttribute("errorMessage", "Failed to create course. Please try again.");
    }
    return "trainerHome";  // Return to the same page
}



@GetMapping("/lesson")
public String showCourseDropdown(Model model) {
    List<Course> courseList = tService.courseList();
    
    // Check if the courseList is null or empty
    if (courseList == null || courseList.isEmpty()) {
        System.out.println("No courses found.");
    } else {
        System.out.println("Courses fetched: " + courseList.size());
    }

    model.addAttribute("courseList", courseList);
    return "addLesson";
}


@PostMapping("/lesson")
public String lesson(@RequestParam("courseId") int courseId,
                     @RequestParam("lessonName") String lessonName,
                     @RequestParam("topics") String topics,
                     @RequestParam("link") String link) {

    Course course = tService.getCourse(courseId);
    if (course == null) {
        throw new RuntimeException("Course not found for ID: " + courseId);
    }

    Lesson lesson = new Lesson(lessonName, topics, link, course);
    tService.addLesson(lesson);

    if (course.getLessons() == null) {
        course.setLessons(new ArrayList<>());
    }
    course.getLessons().add(lesson);
    tService.saveCourse(course);

    return "/trainerHome";
}


@GetMapping("/showCourses")
public String showCourses(Model theModel)
    {
	List<Course> courseList = tService.courseList();
	theModel.addAttribute("courseList",courseList);
	return "courses";
    }

@PostMapping("/deleteCourse/{id}")
public String deleteCourse(@PathVariable("id") int id) {
    tService.deleteCourseById(id);
    return "redirect:/showCourses"; // Redirect to the courses list after deletion
}

@GetMapping("/trainerHome")
public String trainerHomePage() {
    return "trainerHome";
}


@GetMapping("/createCourse")
public String createCourse() {
return "createCourse";

}


}