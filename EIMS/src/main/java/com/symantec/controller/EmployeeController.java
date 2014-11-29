package com.symantec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.symantec.bean.Employee;
import com.symantec.exception.EmployeeNotFoundException;
import com.symantec.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> listEmployees(){
		return employeeService.findAll();
	}
	
	@RequestMapping(value="/createEmployee", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeService.add(employee);
	}
	
	@RequestMapping(value = "/employees/{firstName}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String firstName, HttpServletRequest request){
		Employee employee = employeeService.getEmployeeByFirstName(firstName);
		
		if( null == employee ){
			throw new EmployeeNotFoundException("No employee named: "+ firstName);
		}
		
		return employee;
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable String id){
		employeeService.deleteEmployee(id);
	}
}
