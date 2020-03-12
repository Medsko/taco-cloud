package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.model.Taco;
import com.medsko.tacos.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientService ingredientService;

	public DesignTacoController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public String showDesignForm(Model model) {

		List<Ingredient> ingredients = ingredientService.findAll();

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
