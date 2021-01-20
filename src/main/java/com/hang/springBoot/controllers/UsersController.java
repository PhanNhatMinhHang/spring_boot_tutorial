package com.hang.springBoot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

class UserAssembler implements RepresentationModelAssembler<User, User> {
	@Override
	public User toModel(User entity) {
		return entity;
	}

	@Override
	public CollectionModel<User> toCollectionModel(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		CollectionModel<User> collectionModel = RepresentationModelAssembler.super.toCollectionModel(entities);
		collectionModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).findAllUsers())
				.withSelfRel());
		return collectionModel;
	}
}

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:8080")
//@PreAuthorize("hasAnyAuthority('USER_READ')")
public class UsersController {
	@Autowired
	private UserRepository repository;

	@Autowired
    private PagedResourcesAssembler<User> pagedResourcesAssembler;
 
	private UserAssembler userAssembler = new UserAssembler();

	@GetMapping("/users")
	public CollectionModel<User> findAllUsers() {
		return userAssembler.toCollectionModel(repository.findAll());
	}

	@GetMapping("/users-list")
	public List<User> findAllUsers(Pageable pageable) {
		Page<User> users = repository.findAll(pageable);
		return users.getContent();
	}

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

	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable(value = "id") Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
		return user;
	}

	@PostMapping("/users")
	public User createUser(@Valid User user) {
		return repository.save(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long id, @Valid User newUser) {
		User user = findUserById(id);
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		return repository.save(user);
	}

	@DeleteMapping("/users/{id}")
	public boolean deleteUser(@PathVariable(value = "id") Long id) {
		repository.delete(findUserById(id));
		return true;
	}
}
