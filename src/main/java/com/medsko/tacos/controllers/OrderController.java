package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.User;
import com.medsko.tacos.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/current")
	public String showOrderForm(Model model) {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
	                           SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.debug("Processing order: " + order);
		order.setUser(user);

		orderService.save(order);
		sessionStatus.setComplete();

		return "redirect:/";
	}
}
