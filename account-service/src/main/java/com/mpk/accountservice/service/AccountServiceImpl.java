package com.mpk.accountservice.service;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpk.accountservice.exception.AuthenticationFailedException;
import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.model.Order;
import com.mpk.accountservice.repository.CustomerRepository;
import com.mpk.accountservice.util.ShaHashing;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	CustomerRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public Customer authenticate(String username, String password) throws NoSuchAlgorithmException {
		Customer customer = repository.findByUsername(username);
		if (customer != null && customer.getPassword().equals(ShaHashing.encrypt(password))) {
			LOGGER.info("Customer {} logged in!", customer.getUsername());
			return customer;
		} else
			throw new AuthenticationFailedException();
	}

	@Override
	public Long addCustomer(Customer customer) throws NoSuchAlgorithmException {
		customer.setPassword(ShaHashing.encrypt(customer.getPassword()));
		Customer created = repository.save(customer);
		LOGGER.info("New Customer {} added!", customer.getUsername());
		return created.getCustomerId();
	}

	@Override
	@Transactional
	public void addNewOrder(Long customerId, Order order) {
		Customer customer = repository.findById(customerId).orElseThrow();

		customer.getOrders().add(order);

		repository.save(customer);
	}

}
