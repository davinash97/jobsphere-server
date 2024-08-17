package com.portal.jobsphere.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResponseObject<T> {
	public int code;
	public String status;
	public T result;

	public ResponseObject() {
	}

	public ResponseObject(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public ResponseObject(int code, String status, T result) {
		this.code = code;
		this.status = status;
		this.result = result;
	}
}
