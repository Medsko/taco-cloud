package com.medsko.tacos.bootstrap;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.Taco;
import com.medsko.tacos.model.User;
import com.medsko.tacos.repositories.OrderRepository;
import com.medsko.tacos.repositories.UserRepository;
import com.medsko.tacos.services.IngredientService;
import com.medsko.tacos.services.TacoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

	private final IngredientService ingredientService;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final TacoService tacoService;

	public DataLoader(IngredientService ingredientService, UserRepository userRepository, OrderRepository orderRepository, TacoService tacoService) {
		this.ingredientService = ingredientService;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.tacoService = tacoService;
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

		Taco meatTornado = new Taco();
		meatTornado.setIngredients(ingredients.subList(2, ingredients.size() - 3));
		meatTornado.setName("Meat Tornado");
		meatTornado.setCreatedAt(Date.valueOf(LocalDate.now()));

		Order usual = new Order();
		usual.setUser(melle);
		usual.setDesigns(Collections.singletonList(meatTornado));
		usual.setName("The usual");
		usual.setCcNumber("5425-8610-6817-2015");
		usual.setCcCVV("701");
		usual.setCcExpiration("11/12");
		usual.setCity("Utrecht");
		usual.setZip("3562JV");
		usual.setState("Utrecht");
		usual.setStreet("Zamenhofdreef");
		usual.setPlacedAt(Date.valueOf(LocalDate.now()));

		List<Order> orders = new ArrayList<>();
		orders.add(usual);
		for (int i=0; i<=20; i++) {
			Order copy = new Order(usual);
			copy.addDesign(new Taco(usual.getDesigns().get(0)));
			orders.add(copy);
		}

		orders.forEach(order -> {
			order.getDesigns().forEach(tacoService::save);
			orderRepository.save(order);
		});
	}
}
