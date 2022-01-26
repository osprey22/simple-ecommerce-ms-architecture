package com.mpk.apigateway.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerFallbackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerFallbackController.class);

	private static final String FALLBACK_MSG = "You are seeing fallback response as the requested service might be down. Please try again later";

	@GetMapping("/accountServiceFallback")
	public String accountServiceFallbackMethod() {
		LOGGER.error("Fallback for Account Service executed at {}", LocalDateTime.now());
		return FALLBACK_MSG;
	}

	@GetMapping("/catalogServiceFallback")
	public String catalogServiceFallback() {
		LOGGER.error("Fallback for Catalog Service executed at {}", LocalDateTime.now());
		return FALLBACK_MSG;
	}

	@GetMapping("/orderServiceFallback")
	public String orderServiceFallback() {
		LOGGER.error("Fallback for Order Service executed at {}", LocalDateTime.now());
		return FALLBACK_MSG;
	}

}
