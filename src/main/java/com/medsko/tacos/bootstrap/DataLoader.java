package com.medsko.tacos.bootstrap;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.model.User;
import com.medsko.tacos.repositories.UserRepository;
import com.medsko.tacos.services.IngredientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

	private final IngredientService ingredientService;
	private final UserRepository userRepository;

	public DataLoader(IngredientService ingredientService, UserRepository userRepository) {
		this.ingredientService = ingredientService;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
		);
		ingredients.forEach(ingredientService::save);

		User melle = new User("mvries", "Welkom123", "Melle Edsko de Vries",
				"Zamenhofdreef", "Utrecht", "Utrecht", "3562 VH", "0612343948");
		userRepository.save(melle);

	}
}
