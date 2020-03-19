package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {

	Order save(Order order);

	List<Order> findFlooblecranksByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
