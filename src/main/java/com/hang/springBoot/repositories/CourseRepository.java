package com.hang.springBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	List<Course> findByUserId(Long userId);
	Optional<Course> findByUserIdAndId(Long userId, Long id);
}
