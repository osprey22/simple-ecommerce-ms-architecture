package com.mpk.orderservice;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mpk.orderservice.model.Cart;
import com.mpk.orderservice.repository.CartRepository;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	CartRepository cartRepository;

	@Test
	void saveCart() {

		Cart cart = new Cart();
		cart.setCustomerId(2L);
		cart.setLineItems(new ArrayList<>());
		cart.setCartTotal(BigDecimal.ZERO);

		cartRepository.save(cart);
	}

}
