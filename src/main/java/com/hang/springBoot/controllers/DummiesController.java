package com.hang.springBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DummiesController {
	@GetMapping("/dummies")
	public String testo() {
		return "ok";
	}
}
