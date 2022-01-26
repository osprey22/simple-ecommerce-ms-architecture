package com.mpk.orderservice.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mpk.orderservice.dto.AddOrderToAccountDTO;
import com.mpk.orderservice.model.Cart;
import com.mpk.orderservice.model.LineItem;
import com.mpk.orderservice.model.Order;
import com.mpk.orderservice.model.OrderStatus;
import com.mpk.orderservice.repository.CartRepository;
import com.mpk.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private Random orderKeyRandom;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional
	public Cart addItemsToCart(Long customerId, LineItem item) {
		String price = restTemplate.getForObject("http://CATALOG-SERVICE/products/" + item.getProductId() + "/price",
				String.class);
		item.calculateTotal(new BigDecimal(price));
		// Assumes that there is already a cart associated for the given customer ID
		Cart cart = cartRepository.findById(customerId).orElseThrow();
		cart.getLineItems().add(item);
		cart.calculateCartTotal();
		cartRepository.save(cart);
		return cart;
	}

	@Override
	@Transactional
	public Order saveOrder(Long customerId) {
		Cart cart = cartRepository.findById(customerId).orElseThrow();
		Order order = new Order(generateLongKey(), customerId, OrderStatus.NEW, cart.getCartTotal());
		orderRepository.save(order);

		AddOrderToAccountDTO orderDTO = new AddOrderToAccountDTO(order.getOrderId().toString(), "NEW",
				order.getOrderTotal().toString());

		restTemplate.postForEntity("http://ACCOUNT-SERVICE/users/" + customerId + "/order", orderDTO, String.class);
		return order;
	}

	private Long generateLongKey() {
		Long leftLimit = 1L;
		Long rightLimit = 1000000000L;
		return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}

}
