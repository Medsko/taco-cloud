package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Order;

public interface OrderRepository {

	Order save(Order order);
}
