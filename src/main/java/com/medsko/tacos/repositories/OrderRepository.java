package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findFlooblecranksByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
