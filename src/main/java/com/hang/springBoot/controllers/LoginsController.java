//package com.hang.springBoot.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hang.springBoot.component.JwtUtil;
//import com.hang.springBoot.models.User;
//import com.hang.springBoot.repositories.UserRepository;
//
//@RestController
//@RequestMapping("/")
//public class LoginsController {
//	@Autowired
//	private UserRepository repository;
//
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@PostMapping("/logins")
//	public String login(String username, String password) {
//		System.out.println("Username =========== " + username + " ========= Password ============= " + password);
//		User user = repository.findByNameAndPassword(username, password).orElseThrow(() -> new ResourceNotFoundException());
//		String token = jwtUtil.generateToken(user);
//		return token;
////		ObjectMapper mapper = new ObjectMapper();
////		try {
////			return mapper.writeValueAsString(user);
////		} catch (JsonProcessingException e) {
////			return "";
////		}
//	}
//}
