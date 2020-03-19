package com.medsko.tacos.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.Taco;
import com.medsko.tacos.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
@Deprecated // Use JPA repository
public class JdbcOrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;

	public JdbcOrderRepository(JdbcTemplate jdbc) {
		orderInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");

		orderTacoInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order_Tacos");

		objectMapper = new ObjectMapper();
	}

//	@Override
	public Order save(Order order) {
		order.setPlacedAt(Date.valueOf(LocalDate.now()));
		final long orderId = saveOrderDetails(order);
		order.setId(orderId);

		order.getDesigns().forEach(taco -> saveTacoToOrder(taco, orderId));

		return order;
	}

//	@Override
	public List<Order> findFlooblecranksByUserOrderByPlacedAtDesc(User user, Pageable pageable) {
		return new ArrayList<>();
	}

	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		return orderInserter.executeAndReturnKey(values).longValue();
	}

	private void saveTacoToOrder(Taco taco, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("taco", taco.getId());
		values.put("tacoOrder", orderId);
		orderTacoInserter.execute(values);
	}
}
