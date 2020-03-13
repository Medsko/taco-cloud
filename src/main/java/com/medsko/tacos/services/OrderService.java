package com.medsko.tacos.services;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}
}
