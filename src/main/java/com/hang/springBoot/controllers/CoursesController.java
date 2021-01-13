package com.hang.springBoot.controllers;

import java.util.ArrayList;
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
import com.hang.springBoot.models.UserCourse;
import com.hang.springBoot.repositories.CourseRepository;
import com.hang.springBoot.repositories.UserCourseRepository;
import com.hang.springBoot.repositories.UserRepository;

@RestController
@RequestMapping("/")
public class CoursesController {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private UserCourseRepository ucRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/{userId}/courses")
	public List<Course> findAllCourses(@PathVariable Long userId) {
		List<UserCourse> userCourses = ucRepository.findByUserId(userId);
		List<Course> courses = new ArrayList<>();
		for (UserCourse uc : userCourses) {
			courses.add(uc.getCourse());
		}
		return courses;
	}

	@GetMapping("/users/{userId}/courses/{id}")
	public Course findCourseById(@PathVariable Long userId, @PathVariable Long id) {
		UserCourse uc = ucRepository.findByUserIdAndCourseId(userId, id).orElseThrow(() -> new ResourceNotFoundException());
		return uc.getCourse();
	}
	@PostMapping("/users/{userId}/courses")
	public Course createNewCourse(@PathVariable Long userId, Course course) {
		Course newCourse = repository.save(course);
		UserCourse uc = new UserCourse();
		uc.setCourse(newCourse);
		uc.setUser(CommonFinder.findUserById(userRepository, userId));
		ucRepository.save(uc);
		return newCourse;
	}
	@PutMapping("/users/{userId}/courses/{id}")
	public Course updateCourse(@PathVariable Long userId, @PathVariable Long id,Course newCourse) {
		Course course= findCourseById(userId, id);
		course.setName(newCourse.getName());
		course.setPrice(newCourse.getPrice());;
		return repository.save(course);
	}
	@DeleteMapping("/users/{userId}/courses/{id}")
	public boolean deleteCourse(@PathVariable Long userId, @PathVariable Long id) {
		Course course = findCourseById(userId, id);
		ucRepository.delete(ucRepository.findByUserIdAndCourseId(userId, id).orElseThrow(() -> new ResourceNotFoundException()));
		repository.delete(course);
		return true;
	}
}
