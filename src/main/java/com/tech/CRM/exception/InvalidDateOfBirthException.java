package com.tech.CRM.exception;

public class InvalidDateOfBirthException extends RuntimeException {

	private static final long serialVersionUID = -4083847240873379881L;
	
	private String msg;
	public InvalidDateOfBirthException(String msg) {
		super(msg);
		this.msg = msg;
    }
}
