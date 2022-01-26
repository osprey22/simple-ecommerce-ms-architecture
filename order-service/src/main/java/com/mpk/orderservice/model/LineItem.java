package com.mpk.orderservice.model;

import java.math.BigDecimal;

public class LineItem {

	private Long productId;

	private Integer quantity;

	private BigDecimal total;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void calculateTotal(BigDecimal price) {
		total = price.multiply(BigDecimal.valueOf(quantity));
	}

}
