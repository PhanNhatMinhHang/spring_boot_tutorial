package com.hang.stringBoot.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(mvc).isNotNull();
	}

	@Test
	public void getAllUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
}
