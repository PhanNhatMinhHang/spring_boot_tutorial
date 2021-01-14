package com.hang.springBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.UserCourse;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
	List<UserCourse> findByUserId(Long userId);
	List<UserCourse> findByCourseId(Long courseId);
	Optional<UserCourse> findByUserIdAndId(Long userId, Long id);
	Optional<UserCourse> findByCourseIdAndId(Long courseId, Long id);
	Optional<UserCourse> findByUserIdAndCourseId(Long userId, Long courseId);
}
