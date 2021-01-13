package com.hang.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.hang.springBoot.models.User;
import com.hang.springBoot.repositories.UserRepository;

public class CommonFinder {
	public static User findUserById(UserRepository repository, Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}
}
