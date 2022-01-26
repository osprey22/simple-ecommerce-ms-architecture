package com.mpk.accountservice.service;

import java.security.NoSuchAlgorithmException;

import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.model.Order;

public interface AccountService {

	Customer authenticate(String username, String password) throws NoSuchAlgorithmException;

	Long addCustomer(Customer customer) throws NoSuchAlgorithmException;

	void addNewOrder(Long customerId, Order order);

}
