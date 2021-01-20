package com.hang.springBoot.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book extends RepresentationModel<Book> {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	@Column( name = "title", nullable = false)
	private String title;
	@Column( name = "price", nullable = false)
	private Float price;

	@JsonIgnore
	@OneToMany( mappedBy = "book")
	private List<Chapter> chapters;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public Book(Long id, String title, Float price) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
}
