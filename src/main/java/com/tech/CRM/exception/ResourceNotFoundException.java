package com.tech.CRM.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4083847240873379881L;
	
	private String msg;

	public ResourceNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
	 

}
