package com.microservicesdemo.crudoperation.exceptions;

public class NoRecordFoundException extends RuntimeException {
	private String message;

	public NoRecordFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
