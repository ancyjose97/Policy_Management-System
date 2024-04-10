package com.cts.pms.exception;



public class PolicyApiException extends RuntimeException {

	
	String message;

	public PolicyApiException(String message) {
		
		super(String.format("%s", message));
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
