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
//@PreAuthorize("hasAnyAuthority('USER_READ')")
public class CoursesController {

	@Autowired
	private CourseRepository repository;

	@GetMapping("/courses")
	public List<Course> findAllCourses() {
		return repository.findAll();
	}

	@GetMapping("/courses/{id}")
	public Course findCourseById( @PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}
	@PostMapping("/courses")
	public Course createNewCourse(Course course) {
		return repository.save(course);
	}
	@PutMapping("/courses/{id}")
	public Course updateCourse(@PathVariable Long id,Course newCourse) {
		Course course= findCourseById(id);
		course.setName(newCourse.getName());
		course.setPrice(newCourse.getPrice());;
		return repository.save(course);
	}
	@DeleteMapping("/courses/{id}")
	public boolean deleteCourse( @PathVariable Long id) {
		repository.delete(findCourseById(id));
		return true;
	}
}
