package com.medsko.tacos.controllers;

import com.medsko.tacos.configuration.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHomePage() throws Exception {

		String[] validPaths = new String[] {"/", "/index", "/index.html"};

		for (String validPath : validPaths) {
			mockMvc.perform(MockMvcRequestBuilders.get(validPath))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(view().name("home"))
					.andExpect(content().string(containsString("Welcome to Taco Cloud!")));
		}
	}

}