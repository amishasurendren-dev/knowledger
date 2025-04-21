package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>{

}