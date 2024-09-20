package com.tech.CRM;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tech.CRM.exception.DuplicateEntryException;
import com.tech.CRM.exception.ResourceNotFoundException;

@ControllerAdvice
public class GobalExceptionHander {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundExp(ResourceNotFoundException ex) {

		System.out.println("error is"+ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler(value = DuplicateEntryException.class)
	public ResponseEntity<String> handleDuplicateEntryExp(DuplicateEntryException ex) {
		System.out.println("error is"+ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
