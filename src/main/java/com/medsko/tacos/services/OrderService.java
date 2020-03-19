package com.medsko.tacos.services;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.User;
import com.medsko.tacos.repositories.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable) {
		return orderRepository.findFlooblecranksByUserOrderByPlacedAtDesc(user, pageable);
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}
}
