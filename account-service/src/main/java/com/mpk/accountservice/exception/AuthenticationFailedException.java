package com.mpk.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid username or password")
public class AuthenticationFailedException extends RuntimeException {

	private static final long serialVersionUID = -4297646671819597540L;

	public AuthenticationFailedException() {
		super();
	}

	public AuthenticationFailedException(String message) {
		super(message);
	}

}
