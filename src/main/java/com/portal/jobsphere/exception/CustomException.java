package com.portal.jobsphere.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.portal.jobsphere.model.ResponseObject;

public class CustomException extends RuntimeException {

	public ResponseObject<?> notFound(UUID id) {
		return new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "not found", "invalid id");
	}
}
