package com.hang.stringBoot.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hang.springBoot.controllers.DummiesController;

@WebMvcTest(DummiesController.class)
@Import(DummiesController.class)
public class DummiesControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	public void testDummy() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/dummies")).andExpect(status().isOk());
	}
}
