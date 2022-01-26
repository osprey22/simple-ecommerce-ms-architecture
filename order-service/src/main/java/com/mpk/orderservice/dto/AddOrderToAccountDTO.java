package com.mpk.orderservice.dto;

public class AddOrderToAccountDTO {

	private String orderId;

	private String status;

	private String orderTotal;

	public AddOrderToAccountDTO(String orderId, String status, String orderTotal) {
		this.orderId = orderId;
		this.status = status;
		this.orderTotal = orderTotal;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

}
