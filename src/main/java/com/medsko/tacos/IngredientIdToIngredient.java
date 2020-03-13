package com.medsko.tacos;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.services.IngredientService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientIdToIngredient implements Converter<String, Ingredient> {

	private final IngredientService service;

	public IngredientIdToIngredient(IngredientService service) {
		this.service = service;
	}

	@Override
	public Ingredient convert(String id) {
		return service.findById(id);
	}
}
