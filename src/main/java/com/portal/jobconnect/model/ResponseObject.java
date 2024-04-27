package com.portal.jobconnect.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseObject<T> {
	public int code;
	public String status;
	public T result;

	public ResponseObject() {
	};

	public ResponseObject(int code, String status, T result) {
		this.code = code;
		this.result = result;
		this.status = status;
	}
}
