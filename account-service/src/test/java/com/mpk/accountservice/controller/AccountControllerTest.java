package com.mpk.accountservice.controller;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpk.accountservice.dto.LoginRequest;
import com.mpk.accountservice.model.Customer;
import com.mpk.accountservice.service.AccountService;
import com.mpk.accountservice.util.ShaHashing;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	private Customer customer;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		customer = new Customer("Pavan Kumar", "Modalavalasa", "osprey22", ShaHashing.encrypt("someSecurePwd#1"));

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Test
	void testLogin() throws Exception {
		Mockito.when(accountService.authenticate("osprey22", "someSecurePwd#1")).thenReturn(customer);

		LoginRequest loginRequest = new LoginRequest("osprey22", "someSecurePwd#1");

		mockMvc.perform(MockMvcRequestBuilders.post(new URI("/users/login")).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loginRequest)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(ShaHashing.encrypt("someSecurePwd#1")));
	}

}
