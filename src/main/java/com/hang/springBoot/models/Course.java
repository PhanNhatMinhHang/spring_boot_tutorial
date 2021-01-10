package com.hang.springBoot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "price")
	private Float price;

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(Long id, String name, Long userId, Float price) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;	
		}
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		if (price != null) {
			this.price = price;	
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
