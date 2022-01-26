package com.mpk.accountservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.util.ShaHashing;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository repository;

	@BeforeEach
	void setUp() throws NoSuchAlgorithmException {
		Customer customer = new Customer("Rick", "Sanchez", "ricksanchez101", ShaHashing.encrypt("someSecurePwd"));

		entityManager.persist(customer);
	}

	@Test
	void whenFindByUsername_ThenReturnCustomer() {
		Customer customer = repository.findByUsername("ricksanchez101");

		assertEquals("Rick", customer.getFirstName());
	}

}
