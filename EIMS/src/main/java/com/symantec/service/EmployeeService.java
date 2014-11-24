package com.symantec.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symantec.bean.Employee;
import com.symantec.repository.EmployeeRepository;
import com.symantec.util.Util;

@Service
public class EmployeeService {
	
	private static final Log logger = LogFactory.getLog(EmployeeService.class);
	
	private EmployeeRepository repo;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository){
		this.repo = employeeRepository;
	}
	
	public List<Employee> findAll(){
		return repo.findAll();
	}

	public Employee add(Employee employee) {
		return repo.save(employee);
	}

	public Employee getEmployeeByFirstName(String firstName) {

		if(!Util.isEmptyOrNull(firstName)){
			return repo.findByFirstName(firstName);
		}
		
		return null;
	}
}
