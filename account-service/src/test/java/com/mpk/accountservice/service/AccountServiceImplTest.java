package com.mpk.accountservice.service;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mpk.accountservice.exception.AuthenticationFailedException;
import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.repository.CustomerRepository;
import com.mpk.accountservice.util.ShaHashing;

@SpringBootTest
class AccountServiceImplTest {

	@MockBean
	private CustomerRepository repo;

	@Autowired
	private AccountService accountServiceImpl;

	@Test
	void authenticationShouldWorkForCorrectCredentials() throws NoSuchAlgorithmException {
		Mockito.when(repo.findByUsername("osprey22"))
				.thenReturn(new Customer("Pavan Kumar", "Modalavalasa", "osprey22", ShaHashing.encrypt("securepwd")));

		Customer customer = accountServiceImpl.authenticate("osprey22", "securepwd");

		Assertions.assertEquals("Modalavalasa", customer.getLastName());
	}

	@Test
	void authenticationShouldThrowExceptionForNonHashedPwd() throws NoSuchAlgorithmException {
		Mockito.when(repo.findByUsername("osprey22"))
				.thenReturn(new Customer("Pavan Kumar", "Modalavalasa", "osprey22", "securepwd"));

		Assertions.assertThrows(AuthenticationFailedException.class,
				() -> accountServiceImpl.authenticate("osprey22", "securepwd"));
	}

}
