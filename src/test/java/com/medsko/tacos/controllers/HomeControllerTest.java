package com.medsko.tacos.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public class HomeControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setUp() {
	}

	@Test
	public void testHomePage() throws Exception {

//		String[] validPaths = new String[] {"/", "/index", "/index.html"};
//
//		for (String validPath : validPaths) {
//			mockMvc.perform(MockMvcRequestBuilders.get(validPath))
//					.andExpect(MockMvcResultMatchers.status().isOk())
//					.andExpect(view().name("home"))
//					.andExpect(content().string(containsString("Welcome to Taco Cloud!")));
//		}
	}

}