package com.medsko.tacos.model.representation;

import com.medsko.tacos.model.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Relation(itemRelation = "taco", collectionRelation = "tacos")
@Getter
public class TacoModel extends RepresentationModel<TacoModel> {

	private final String name;
	private final Date createdAt;
	private final CollectionModel<IngredientModel> ingredients;

	public TacoModel(Taco taco) {
		name = taco.getName();
		createdAt = taco.getCreatedAt();
		ingredients = new IngredientModelAssembler().toCollectionModel(taco.getIngredients());
	}

}
