package com.hang.springBoot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chapter {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	@Column( name = "name", nullable = false)
	private String name;
	@Column( name = "content", nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn( name = "book_id")
	private Book book;
	public Chapter() {
		// TODO Auto-generated constructor stub
	}
	public Chapter(Long id, String title, String content) {
		super();
		this.id = id;
		this.name = title;
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return name;
	}
	public void setTitle(String title) {
		this.name = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
