package com.example.demo.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	Integer courseId;
	String courseName;
	int coursePrice;
	@OneToMany
	List<Lesson> lessons;
	@ManyToMany
	List<Users> users;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(int courseId, String courseName, int coursePrice, List<Lesson> lessons, List<Users> users) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.lessons = lessons;
		this.users = users;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	// In Courses class
	@Override
	public String toString() {
	    return "Course [ courseName=" + courseName + ", coursePrice=" + coursePrice
	            + ", lessons=" + (lessons == null ? "null" : lessons.size()) + ", users=" + (users == null ? "null" : users.size()) + "]";
	}

	
	
}

