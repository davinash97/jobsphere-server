package com.portal.jobsphere.exception;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.portal.jobsphere.model.ResponseObject;

public class Exception extends RuntimeException {
	private static final Logger logger = LoggerFactory.getLogger(Exception.class);

	public static ResponseObject<?> notFoundException(UUID id, String message) {
		String response = message + " not found";
		logger.error(response);
		return new ResponseObject<>(HttpStatus.NOT_FOUND.value(), response);
	}
}
