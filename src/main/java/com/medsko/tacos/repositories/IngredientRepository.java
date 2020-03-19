package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
