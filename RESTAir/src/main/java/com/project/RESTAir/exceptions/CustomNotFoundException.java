package com.project.RESTAir.exceptions;

public class CustomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1902531103084225647L;

	public CustomNotFoundException() {
		super();
	}
	
	public CustomNotFoundException(String message) {
		super(message);
	}

}
