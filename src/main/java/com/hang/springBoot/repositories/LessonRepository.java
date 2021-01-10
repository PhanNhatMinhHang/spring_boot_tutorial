package com.hang.springBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
	List<Lesson> findByCourseId(Long courseId);
	Optional<Lesson> findByCourseIdAndId(Long courseId, Long id);
}
