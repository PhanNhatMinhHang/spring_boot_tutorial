package com.hang.springBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	List<Chapter> findByBookId(Long bookId);
}
