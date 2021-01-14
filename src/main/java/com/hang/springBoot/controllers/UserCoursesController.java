package com.hang.springBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.UserCourse;
import com.hang.springBoot.repositories.CourseRepository;
import com.hang.springBoot.repositories.UserCourseRepository;
import com.hang.springBoot.repositories.UserRepository;

@RestController
@RequestMapping("/")
public class UserCoursesController {
	@Autowired
	private UserCourseRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/users/{userId}/user_courses")
	public List<UserCourse> findAllUserCourse(@PathVariable Long userId) {
		return repository.findByUserId(userId);
	}

	@GetMapping("/users/{userId}/user_courses/{id}")
	public UserCourse findByUserCourseId(@PathVariable Long userId, @PathVariable Long id) {
		return repository.findByUserIdAndId(userId, id).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PostMapping("/users/{userId}/user_courses")
	public UserCourse addCourseToUser(@PathVariable Long userId, UserCourse userCourse) {
		userCourse.setUser(CommonFinder.findUserById(userRepository, userId));
		userCourse.setCourse(CommonFinder.findCourseById(courseRepository, userCourse.getNewCourseId()));
		return repository.save(userCourse);
	}

	@DeleteMapping("/users/{userId}/user_courses/{id}")
	public boolean removeCourseFromUser(@PathVariable Long userId, @PathVariable Long id) {
		repository.delete(findByUserCourseId(userId, id));
		return true;
	}

	@GetMapping("/courses/{courseId}/user_courses")
	public List<UserCourse> findAllUsersInCourse(@PathVariable Long courseId) {
		return repository.findByCourseId(courseId);
	}

	@GetMapping("/courses/{courseId}/user_courses/{id}")
	public UserCourse findUserInCourse(@PathVariable Long courseId, @PathVariable Long id) {
		return repository.findByCourseIdAndId(courseId, id).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PostMapping("/courses/{courseId}/user_courses")
	public UserCourse addUserToCourse(@PathVariable Long courseId, UserCourse userCourse) {
		userCourse.setUser(CommonFinder.findUserById(userRepository, userCourse.getNewUserId()));
		userCourse.setCourse(CommonFinder.findCourseById(courseRepository, courseId));
		return repository.save(userCourse);
	}

	@DeleteMapping("/courses/{courseId}/user_courses/{id}")
	public boolean removeUserFromCourse(@PathVariable Long courseId, @PathVariable Long id) {
		repository.delete(findUserInCourse(courseId, id));
		return true;
	}
}
