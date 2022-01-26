package com.mpk.orderservice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders_collection")
public class Cart {

	@Id
	private Long customerId;

	private List<LineItem> lineItems = new ArrayList<>();

	private BigDecimal cartTotal = BigDecimal.ZERO;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(BigDecimal cartTotal) {
		this.cartTotal = cartTotal;
	}

	public void calculateCartTotal() {
		cartTotal = lineItems.isEmpty() ? BigDecimal.ZERO
				: lineItems.stream().map(LineItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
