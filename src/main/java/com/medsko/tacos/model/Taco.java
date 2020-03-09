package com.medsko.tacos.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

	private Long id;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@Size(min = 1, message = "Choose at least one ingredient")
	private List<Ingredient> ingredients;
}
