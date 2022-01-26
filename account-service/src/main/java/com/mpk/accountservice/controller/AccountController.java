package com.mpk.accountservice.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.accountservice.dto.CreateCustomerRequest;
import com.mpk.accountservice.dto.LoginRequest;
import com.mpk.accountservice.dto.OrderRequest;
import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.model.Order;
import com.mpk.accountservice.model.OrderStatus;
import com.mpk.accountservice.service.AccountService;

@RestController
@RequestMapping("/users")
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody LoginRequest request) throws NoSuchAlgorithmException {
		Customer customer = service.authenticate(request.getUsername(), request.getPassword());
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Void> addCustomer(@RequestBody CreateCustomerRequest request, HttpServletRequest httpRequest)
			throws NoSuchAlgorithmException, URISyntaxException {
		Customer customer = new Customer(request.getFirstName(), request.getLastName(), request.getUsername(),
				request.getPassword());
		Long id = service.addCustomer(customer);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(httpRequest.getRequestURL().toString().replace("/create", "") + "/" + id));
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}

	@PostMapping("/{customerId}/order")
	public ResponseEntity<String> addNewOrder(@PathVariable String customerId, @RequestBody OrderRequest request) {
		service.addNewOrder(Long.valueOf(customerId), new Order(Long.valueOf(request.getOrderId()),
				new BigDecimal(request.getOrderTotal()), OrderStatus.valueOf(request.getStatus())));
		return new ResponseEntity<>("Order Added", HttpStatus.OK);
	}

}
