package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
class DesignTacoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testShowDesignForm() throws Exception {
		String[] ingredientTypes = Stream.of(Ingredient.Type.values())
				.map(Enum::toString)
				.map(String::toLowerCase)
				.toArray(String[]::new);

		mockMvc.perform(MockMvcRequestBuilders.get("/design"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists(ingredientTypes))
				.andExpect(model().attributeExists("design"));
	}
}