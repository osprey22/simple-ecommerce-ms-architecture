package com.mpk.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.orderservice.model.Cart;
import com.mpk.orderservice.model.LineItem;
import com.mpk.orderservice.model.Order;
import com.mpk.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/{customerId}/cart")
	public ResponseEntity<Cart> addItemsToCart(@PathVariable String customerId, @RequestBody LineItem item) {
		return new ResponseEntity<>(orderService.addItemsToCart(Long.valueOf(customerId), item), HttpStatus.OK);
	}

	@PostMapping("/{customerId}/save")
	public ResponseEntity<Order> saveOrder(@PathVariable String customerId) {
		return new ResponseEntity<>(orderService.saveOrder(Long.valueOf(customerId)), HttpStatus.CREATED);
	}
}
