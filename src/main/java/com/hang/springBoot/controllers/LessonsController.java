//package com.hang.springBoot.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hang.springBoot.models.Lesson;
//import com.hang.springBoot.repositories.CourseRepository;
//import com.hang.springBoot.repositories.LessonRepository;
//
//@RestController
//@RequestMapping("/")
//public class LessonsController {
//
//	@Autowired
//	private LessonRepository repository;
//	@Autowired
//	private CourseRepository courseRepository;
//	
//	@GetMapping("/courses/{courseId}/lessons")
//	public List<Lesson> findAllLessions( @PathVariable Long courseId){
//		return repository.findByCourseId(courseId);
//	}
//	@GetMapping("/courses/{courseId}/lessons/{id}")
//	public Lesson findLessionById( @PathVariable Long courseId, @PathVariable Long id) {
//		return repository.findByCourseIdAndId(courseId, id).orElseThrow(()-> new ResourceNotFoundException("Can't find lesson with id "+id));
//	}
//	@PostMapping("/courses/{courseId}/lessons")
//	public Lesson createLession(@PathVariable Long courseId, Lesson lesson) {
//		lesson.setCourse(CommonFinder.findCourseById(courseRepository, courseId));
//		return repository.save(lesson);
//	}
//	@PutMapping("/courses/{courseId}/lessons/{id}")
//	public Lesson updateLession(@PathVariable Long courseId, @PathVariable Long id,Lesson newLession) {
//		Lesson lesson = findLessionById(courseId, id);
//		lesson.setName(newLession.getName());
//		lesson.setProfessor(newLession.getProfessor());
//		return repository.save(lesson);
//	}
//	@DeleteMapping("/courses/{courseId}/lessons/{id}")
//	public boolean deleteLession(@PathVariable Long courseId, @PathVariable Long id) {
//		repository.delete(findLessionById(courseId, id));
//		return true;
//	}
//	
//
//}
