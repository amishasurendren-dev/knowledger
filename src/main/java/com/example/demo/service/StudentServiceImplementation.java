package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.UserRepository;

@Service
public class StudentServiceImplementation implements StudentService{

	
		@Autowired
		UserRepository ur;

		@Autowired
		LessonRepository lessonRepo;

		@Override

		public Lesson getLesson(int lessonId) {
		return lessonRepo.findById(lessonId).get();

		}

}
