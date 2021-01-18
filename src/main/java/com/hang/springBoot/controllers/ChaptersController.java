package com.hang.springBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.Chapter;
import com.hang.springBoot.repositories.ChapterRepository;

@RestController
@RequestMapping("/books")
public class ChaptersController {
	@Autowired
	ChapterRepository repository;

	@GetMapping("/{bookId}/chapters")
	public List<Chapter> getAllChapters(@PathVariable Long bookId) {
		return repository.findByBookId(bookId);
	}
}
