package com.hang.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.Book;
import com.hang.springBoot.repositories.BookRepository;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	BookRepository repository;

	@GetMapping("/")
	public Page<Book> getAllBooks() {
		return repository.findAll(PageRequest.of(0, 0));
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		EntityModel<Book> resource = EntityModel.of(book);
		book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChaptersController.class).getAllChapters(id)).withRel("allChapters"));
		return book;
	}
}
