package com.wcbeh.exception;

public class CrudErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public static final String CODE = "ERROR_CREATE - ";
		
	public CrudErrorException(String message) {
		super(CODE.concat(message));
	}

}
