package com.hang.springBoot.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.User;
import com.hang.springBoot.repositories.UserRepository;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:8080")
@PreAuthorize("hasAnyAuthority('USER_READ')")
public class UsersController {
	@Autowired
	private UserRepository repository;

	@GetMapping("/users")
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable(value = "id")Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
	}

	@PostMapping("/users")
	public User createUser(User user) {
		return repository.save(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id")Long id, User newUser) {
		User user = findUserById(id);
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		return repository.save(user);
	}

	@DeleteMapping("/users/{id}")
	public boolean deleteUser(@PathVariable(value = "id")Long id) {
		repository.delete(findUserById(id));
		return true;
	}
}
