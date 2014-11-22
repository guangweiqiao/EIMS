package com.symantec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symantec.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private static final Log logger = LogFactory.getLog(EmployeeService.class);
	
	private EmployeeRepository repo;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository){
		this.repo = employeeRepository;
	}
	
	
}
