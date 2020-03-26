package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.services.IngredientService;
import com.medsko.tacos.services.TacoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class DesignTacoControllerTest {

	private MockMvc mockMvc;

	@Mock
	IngredientService ingredientService;

	@Mock
	TacoService tacoService;

	@InjectMocks
	DesignTacoController controller;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
	}

	@Test
	void testShowDesignForm() throws Exception {

		final List<Ingredient> ingredients = new ArrayList<>();
		Stream.of(Ingredient.Type.values())
				.forEach(ingredientType -> ingredients.add(new Ingredient("", "", ingredientType)));

		String[] ingredientTypes = Stream.of(Ingredient.Type.values())
				.map(Enum::toString)
				.map(String::toLowerCase)
				.toArray(String[]::new);

		when(ingredientService.findAll()).thenReturn(ingredients);

		mockMvc.perform(MockMvcRequestBuilders.get("/design"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists(ingredientTypes))
				.andExpect(model().attributeExists("design"))
				.andExpect(view().name("designForm"));
	}
}