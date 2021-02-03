package com.hang.stringBoot.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hang.springBoot.controllers.UsersController;
import com.hang.springBoot.models.Role;
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
		Page<User> mockPage = new PageImpl<User>(mockUsers);
		Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
		this.mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("totalElements", is(2)))
			.andExpect(jsonPath("totalPages", is(1)))
			.andExpect(jsonPath("content[0].id", is(1)))
			.andExpect(jsonPath("content[0].name", is("HoangPhan")))
			.andExpect(jsonPath("content[1].id", is(2)))
			.andExpect(jsonPath("content[1].name", is("HangPhan")));
	}

	@Test
	public void testGetUserById() throws Exception {
		Optional<User> mockUser = Optional.ofNullable(new User(Long.valueOf(1),"HangPhan", "123"));
		Mockito.when(repository.findById(Long.valueOf(1))).thenReturn(mockUser);
		this.mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is (1)))
		.andExpect(jsonPath("name", is ("HangPhan")));
		this.mvc.perform(MockMvcRequestBuilders.get("/users/2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	@Test
	public void testCreateUser() throws Exception {
		Role mockRole = new Role();
		mockRole.setId(Long.valueOf(1));
		mockRole.setName("admin");
		mockRole.setAuthorities("USER_READ");
		Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(mockRole));
		User mockUser = new User(Long.valueOf(1), "HoangPhan", "123");
		mockUser.setRole(mockRole);
		Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(mockUser);
		this.mvc.perform(
				MockMvcRequestBuilders.post("/users")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("name", "HoangPhan")
					.param("password", "123")
					.param("rolename", "admin")
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("id", is(1)))
			.andExpect(jsonPath("name", is("HoangPhan")))
			.andExpect(jsonPath("role.name", is("admin")))
			.andExpect(jsonPath("role.authorities", is("USER_READ")));
	}
	@Test
	public void testUpdateUser() throws Exception {
		User mockUser = new User(Long.valueOf(1), "HoangPhan", "1234");
		Mockito.when(repository.findById(Long.valueOf(1))).thenReturn(Optional.of(mockUser));
		User updatedUser = new User(Long.valueOf(1), "HangPhan", "1234");
		Mockito.when(repository.save(mockUser)).thenReturn(updatedUser);
		this.mvc.perform(
				MockMvcRequestBuilders.put("/users/1")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("name", "HangPhan")
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("id", is(1)))
			.andExpect(jsonPath("name", is("HangPhan")));
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		User mockUser = new User(Long.valueOf(1), "HoangPhan", "1234");
		Mockito.when(repository.findById(Long.valueOf(1))).thenReturn(Optional.of(mockUser));
		Mockito.doNothing().when(repository).delete(mockUser);
		this.mvc.perform(MockMvcRequestBuilders.delete("/users/1")).andExpect(status().isOk());
	}
}
