package com.medsko.tacos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
//@RestResource(rel = "tacos", path = "tacos")  // Dit werkt op zich ook, maar omdat de Springers @RepositoryRestResource
//  aanraden, heb ik die maar boven de TacoRepo geplakt...al heb ik er uiteindelijk maar van afgezien om itemResourceRel
//  op een interessante/onmogelijke waarde te zetten (al heette een taco heel even een hotseflots in de JSON).
public class Taco {

	@Id
	@GeneratedValue
	private Long id;

	@CreatedDate
	private Date createdAt;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@ManyToMany
	@Size(min = 1, message = "Choose at least one ingredient")
	private List<Ingredient> ingredients;

	public Taco(Taco original) {
		this.createdAt = original.createdAt;
		this.name = original.name;
		this.ingredients = new ArrayList<>(original.ingredients);
	}

}
