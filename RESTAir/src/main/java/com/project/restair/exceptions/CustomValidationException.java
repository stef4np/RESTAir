package com.project.restair.exceptions;

public class CustomValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7732064439035222439L;

	public CustomValidationException() {
		super();
	}
	
	public CustomValidationException(String message) {
		super(message);
	}
	
}
