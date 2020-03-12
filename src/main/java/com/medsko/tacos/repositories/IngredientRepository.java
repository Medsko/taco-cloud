package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Optional<Ingredient> findById(String id);

	Ingredient save(Ingredient ingredient);

}
