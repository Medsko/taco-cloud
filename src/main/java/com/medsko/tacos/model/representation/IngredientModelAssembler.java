package com.medsko.tacos.model.representation;

import com.medsko.tacos.controllers.TacoController;
import com.medsko.tacos.model.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

	public IngredientModelAssembler() {
		super(TacoController.class, IngredientModel.class);
	}

	@Override
	public IngredientModel toModel(Ingredient ingredient) {
		return createModelWithId(ingredient.getId(), ingredient);
	}

	@Override
	protected IngredientModel instantiateModel(Ingredient ingredient) {
		return new IngredientModel(ingredient);
	}
}
