package com.symantec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No employee found")
public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String reason){
		super(reason);
	}
}
