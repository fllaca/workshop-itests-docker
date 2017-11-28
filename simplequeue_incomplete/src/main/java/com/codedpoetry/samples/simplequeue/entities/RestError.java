package com.codedpoetry.samples.simplequeue.entities;

public class RestError {

	private String message;

	private String code;

	public RestError(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}

	public RestError() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
			this.code = code;
	}
}
