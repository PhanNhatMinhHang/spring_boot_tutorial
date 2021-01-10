package com.hang.springBoot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "professor")
	private String professor;
	@Column(name = "course_id")
	private Long courseId;
	public Lesson() {
		// TODO Auto-generated constructor stub
	}
	public Lesson(Long id, String name, String professor,Long courseId) {
		this.id = id;
		this.name = name;
		this.professor = professor;
		this.courseId=courseId;
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
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		if (professor != null) {
			this.professor = professor;	
		}
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
