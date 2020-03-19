package com.medsko.tacos.controllers;

import com.medsko.tacos.configuration.OrderProps;
import com.medsko.tacos.model.Order;
import com.medsko.tacos.model.User;
import com.medsko.tacos.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
//@ConfigurationProperties(prefix = "taco.orders")      // Inflexible way of specifying properties (managed by controller itself)
public class OrderController {

	private final OrderService orderService;

	private final OrderProps props;
//	private int pageSize = 20;

	public OrderController(OrderService orderService, OrderProps props) {
		this.orderService = orderService;
		this.props = props;
	}

	@GetMapping("/current")
	public String showOrderForm(Model model) {
		return "orderForm";
	}

	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderService.findByUserOrderByPlacedAtDesc(user, pageable));
		return "orderlist";
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

//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
}
