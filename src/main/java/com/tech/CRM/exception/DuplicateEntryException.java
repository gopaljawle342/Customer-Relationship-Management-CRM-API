package com.tech.CRM.exception;

public class DuplicateEntryException extends RuntimeException {
	
private static final long serialVersionUID = -4083847240873379881L;
	
	private String msg;

	public DuplicateEntryException(String msg) {
		super(msg);
		this.msg = msg;
	}
	 

}
