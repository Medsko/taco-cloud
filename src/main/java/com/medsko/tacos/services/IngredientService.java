package com.medsko.tacos.services;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<Ingredient> findAll() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(ingredients::add);
		return ingredients;
	}

	public Ingredient findById(String id) {
		return ingredientRepository.findById(id).orElse(null);
	}

	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

}
