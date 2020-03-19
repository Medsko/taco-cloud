package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Ingredient;
import com.medsko.tacos.model.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;

@Repository
@Deprecated // Use JPA repository
public class JdbcTacoRepository {

	private JdbcTemplate jdbc;

	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

//	@Override
	public Taco save(Taco taco) {
		final long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		taco.getIngredients().forEach(ingredient -> saveIngredientToTaco(ingredient, tacoId));
		return taco;
	}

	private long saveTacoInfo(Taco taco) {

		taco.setCreatedAt(Date.valueOf(LocalDate.now()));
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				"insert into Taco (name, created_at) values (?, ?)", Types.VARCHAR, Types.DATE
		).newPreparedStatementCreator(
				Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);

		// Keyholder doesn't hold any keys after update(), maybe because of H2?
		if (keyHolder.getKey() != null) {
			return keyHolder.getKey().longValue();
		}
		return 1L;
	}

	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
		jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
				tacoId, ingredient.getId());
	}
}
