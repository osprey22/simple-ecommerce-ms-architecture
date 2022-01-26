package com.mpk.orderservice.service;

import com.mpk.orderservice.model.Cart;
import com.mpk.orderservice.model.LineItem;
import com.mpk.orderservice.model.Order;

public interface OrderService {

	Cart addItemsToCart(Long customerId, LineItem item);

	Order saveOrder(Long customerId);

}
