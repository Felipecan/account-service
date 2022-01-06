package com.example.AccountService.services.exceptions;


public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String s) {
		super(s);
	}
	
	public ObjectNotFoundException(String s, Throwable th) {
		super(s, th);
	}
}
