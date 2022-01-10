package com.example.AccountService.services.exceptions;


public class ServiceComunicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceComunicationException(String s) {
		super(s);
	}
	
	public ServiceComunicationException(String s, Throwable th) {
		super(s, th);
	}
}
