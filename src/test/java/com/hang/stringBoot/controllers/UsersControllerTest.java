package com.hang.stringBoot.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hang.springBoot.controllers.UsersController;
import com.hang.springBoot.models.User;
import com.hang.springBoot.repositories.RoleRepository;
import com.hang.springBoot.repositories.UserRepository;

@WebMvcTest(UsersController.class)
@Import({UsersController.class})
public class UsersControllerTest {
	@Autowired
	MockMvc mvc;

	@MockBean
	private UserRepository repository;

	@MockBean
	private RoleRepository roleRepository;

	@Test
	public void testUserList() throws Exception {
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(new User(Long.valueOf(1), "HoangPhan", "123"));
		mockUsers.add(new User(Long.valueOf(2), "HangPhan", "23421"));
		Mockito.when(repository.findAll()).thenReturn(mockUsers);
		this.mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("[{\"id\":1,\"name\":\"HoangPhan\",\"role\":null,\"links\":[]},{\"id\":2,\"name\":\"HangPhan\",\"role\":null,\"links\":[]}]"));
	}
}
