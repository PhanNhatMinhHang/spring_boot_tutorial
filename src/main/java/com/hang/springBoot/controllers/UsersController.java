package com.hang.springBoot.controllers;
//
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.Role;
import com.hang.springBoot.models.User;
import com.hang.springBoot.repositories.RoleRepository;
import com.hang.springBoot.repositories.UserRepository;

@Controller
@RestController
@RequestMapping("/")
//@CrossOrigin("http://localhost:8080")
public class UsersController {
	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return repository.findAll();
	}

//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	@GetMapping("/users/search")
	public List<User> searchUser(String name) {
		List<User> users = repository.findByNameLike("%" + name + "%");
		for (User user : users) {
			user.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(PaymentsController.class).findAllPayment(user.getId()))
					.withRel("payments"));
		}
		return users;
	}

//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable(value = "id") Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
		return user;
	}

//	@PreAuthorize("hasAnyAuthority('USER_WRITE')")
	@PostMapping("/users")
	public User createUser(@Valid User user) {
		Role role = roleRepository.findByName(user.getRolename()).orElseThrow(() -> new ResourceNotFoundException());
		user.setRole(role);
		return repository.save(user);
	}

//	@PreAuthorize("hasAnyAuthority('USER_WRITE')")
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long id, @Valid User newUser) {
		User user = findUserById(id);
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		return repository.save(user);
	}

//	@PreAuthorize("hasAnyAuthority('USER_DELETE')")
	@DeleteMapping("/users/{id}")
	public boolean deleteUser(@PathVariable(value = "id") Long id) {
		repository.delete(findUserById(id));
		return true;
	}
}
