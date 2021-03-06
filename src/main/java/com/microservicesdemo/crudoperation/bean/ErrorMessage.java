package com.microservicesdemo.crudoperation.bean;

import java.util.Date;

public class ErrorMessage {
	private Date timestamp;
	private String status;
	private String message;
	private String path;
	
	
	public ErrorMessage() {}


	public ErrorMessage(Date timestamp, String status, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		//this.details = details;
		this.path = path;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
}
