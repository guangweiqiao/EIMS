package com.symantec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.symantec.bean.Employee;
import com.symantec.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody List<Employee> listEmployees(){
		return employeeService.findAll();
	}
	
	@RequestMapping(value="/createEmployee", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeService.add(employee);
	}
	
	@RequestMapping(value = "/employees/{firstName}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String firstName){
		Employee employee = employeeService.getEmployeeByFirstName(firstName);
		
//		if( null == employee ){
//			throw new EmployeeNotFoundException("No employee named: "+ firstName);
//		}
		
		return employee;
	}
}
