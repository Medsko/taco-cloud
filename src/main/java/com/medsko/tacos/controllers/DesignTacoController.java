package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {

		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
		);

		Stream.of(Ingredient.Type.values())
				.forEach(type -> model.addAttribute(type.toString().toLowerCase(),
						ingredients.stream()
								.filter(ingredient -> ingredient.getType() == type)
								.collect(Collectors.toList())));

		model.addAttribute("design", new Taco());

		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco newTaco, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}

		log.debug("Processing taco design: " + newTaco);

		return "redirect:/orders/current";
	}

}
