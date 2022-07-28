package com.jwtexample.userservice.crosscutting.errormanagement;


public class ObjectNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 1546749534567007391L;

	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException() {
		super();
	}
	
	
}
