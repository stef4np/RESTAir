package com.project.restair.exceptions;

public class CustomAlreadyAssignedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1585277350525589002L;

	public CustomAlreadyAssignedException() {
		super();
	}
	
	public CustomAlreadyAssignedException(String message) {
		super(message);
	}

}
