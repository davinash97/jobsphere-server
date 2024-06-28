package com.portal.jobsphere.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.portal.jobsphere.model.ResponseObject;

public class NotFound extends RuntimeException {

	public ResponseObject<?> profile(UUID id) {
		return new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "not found", "invalid id");
	}

	public ResponseObject<?> post(UUID id) {
		return new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "not found", "invalid id");
	}
}
