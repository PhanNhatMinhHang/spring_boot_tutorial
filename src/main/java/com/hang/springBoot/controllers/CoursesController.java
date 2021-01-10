package com.hang.springBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.Course;
import com.hang.springBoot.repositories.CourseRepository;

@RestController
@RequestMapping("/")
public class CoursesController {

	@Autowired
	private CourseRepository repository;

	@GetMapping("/users/{userId}/courses")
	public List<Course> findAllCourses(@PathVariable Long userId) {
		return repository.findByUserId(userId);
	}

	@GetMapping("/users/{userId}/courses/{id}")
	public Course findCourseById(@PathVariable Long userId, @PathVariable Long id) {
		return repository.findByUserIdAndId(userId, id).orElseThrow(() -> new ResourceNotFoundException());
	}
	@PostMapping("/users/{userId}/courses")
	public Course createNewCourse(@PathVariable Long userId, Course course) {
		course.setUserId(userId);
		return repository.save(course);
	}
	@PutMapping("/users/{userId}/courses/{id}")
	public Course updateCourse(@PathVariable Long userId, @PathVariable Long id,Course newCourse) {
		Course course= findCourseById(userId, id);
		course.setName(newCourse.getName());
		course.setPrice(newCourse.getPrice());
		return repository.save(course);
	}
	@DeleteMapping("/users/{userId}/courses/{id}")
	public boolean deleteCourse(@PathVariable Long userId, @PathVariable Long id) {
		repository.delete(findCourseById(userId, id));
		return true;
	}
}
