package com.medsko.tacos.model.representation;

import com.medsko.tacos.model.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class IngredientModel extends RepresentationModel<IngredientModel> {

	private final String name;
	private final Ingredient.Type type;

	public IngredientModel(Ingredient ingredient) {
		name = ingredient.getName();
		type = ingredient.getType();
	}
}
