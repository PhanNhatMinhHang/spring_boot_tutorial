package com.hang.springBoot.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private Long newCourseId;
	private Long newUserId;
	
	public UserCourse() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@JsonIgnore
	public Long getNewCourseId() {
		return newCourseId;
	}

	public void setNewCourseId(Long newCourseId) {
		this.newCourseId = newCourseId;
	}

	@JsonIgnore
	public Long getNewUserId() {
		return newUserId;
	}

	public void setNewUserId(Long newUserId) {
		this.newUserId = newUserId;
	}
	
}
