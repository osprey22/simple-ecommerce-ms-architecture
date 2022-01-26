package com.mpk.orderservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders_collection")
public class Order {

	@Id
	private Long orderId;

	private Long customerId;

	private OrderStatus status;

	private BigDecimal orderTotal;

	public Order() {
	}

	public Order(Long orderId, Long customerId, OrderStatus status, BigDecimal orderTotal) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.status = status;
		this.orderTotal = orderTotal;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

}
