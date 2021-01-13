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
import com.hang.springBoot.models.Lesson;
import com.hang.springBoot.repositories.CourseRepository;
import com.hang.springBoot.repositories.LessonRepository;

@RestController
@RequestMapping("/")
public class LessonsController {

	@Autowired
	private LessonRepository repository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/users/{userId}/courses/{courseId}/lessons")
	public List<Lesson> findAllLessions(@PathVariable Long userId, @PathVariable Long courseId){
		checkCourse(userId, courseId);
		return repository.findByCourseId(courseId);
	}
	@GetMapping("/users/{userId}/courses/{courseId}/lessons/{id}")
	public Lesson findLessionById(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long id) {
		checkCourse(userId, courseId);
		return repository.findByCourseIdAndId(courseId, id).orElseThrow(()-> new ResourceNotFoundException("Can't find lesson with id "+id));
	}
	@PostMapping("/users/{userId}/courses/{courseId}/lessons")
	public Lesson createLession(@PathVariable Long userId, @PathVariable Long courseId, Lesson lesson) {
		checkCourse(userId, courseId);
		lesson.setCourseId(courseId);
		return repository.save(lesson);
	}
	@PutMapping("/users/{userId}/courses/{courseId}/lessons/{id}")
	public Lesson updateLession(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long id,Lesson newLession) {
		Lesson lession = findLessionById(userId, courseId, id);
		lession.setName(newLession.getName());
		lession.setProfessor(newLession.getProfessor());
		return repository.save(lession);
	}
	@DeleteMapping("/users/{userId}/courses/{courseId}/lessons/{id}")
	public boolean deleteLession(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long id) {
		repository.delete(findLessionById(userId, courseId, id));
		return true;
	}
	
	private void checkCourse(Long userId, Long courseId) {
		courseRepository.findByUserIdAndId(userId, courseId).orElseThrow(() -> new ResourceNotFoundException());
	}
}
