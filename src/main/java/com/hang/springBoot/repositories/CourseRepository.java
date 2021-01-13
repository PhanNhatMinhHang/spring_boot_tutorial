package com.hang.springBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	List<Course> findByIdIn(List<Long> ids);
}
