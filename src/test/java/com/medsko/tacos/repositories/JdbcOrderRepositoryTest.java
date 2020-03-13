package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.Taco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JdbcOrderRepositoryTest {

	@Mock
	private SimpleJdbcInsert orderInserter;

	@Mock
	private SimpleJdbcInsert orderTacoInserter;

	@InjectMocks
	private JdbcOrderRepository repository = new JdbcOrderRepository(new JdbcTemplate());

	@Test
	void testSave() {
		long mockinglyGeneratedOrderId = 10L;
		when(orderInserter.executeAndReturnKey(anyMap())).thenReturn(mockinglyGeneratedOrderId);

		Order order = new Order();
		order.addDesign(new Taco());
		order.setName("Melle de Vries");
		Order savedOrder = repository.save(order);

		assertNotNull(savedOrder);
		assertNotNull(order.getId());
		assertEquals(mockinglyGeneratedOrderId, order.getId());
	}
}