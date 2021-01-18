package com.hang.springBoot.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hang.springBoot.models.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
