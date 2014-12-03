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
	
	@RequestMapping(value = "/employees/list", method = RequestMethod.GET)
	public List<Employee> listEmployees(){
		return employeeService.findAll();
	}
	
	@RequestMapping(value="/employee/add", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeService.add(employee);
	}
	
	@RequestMapping(value = "/employee/{firstName}", method = RequestMethod.GET)
	public Employee getEmployeeByFirstName(@PathVariable String firstName, HttpServletRequest request){
		Employee employee = employeeService.getEmployeeByFirstName(firstName);
		
		if( null == employee ){
			throw new EmployeeNotFoundException("No employee named: "+ firstName);
		}
		
		return employee;
	}
	
	@RequestMapping(value = "/employee/get/{id}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable String id){
		Employee employee = employeeService.getEmployeeById(id);
		
		if( null == employee ){
			throw new EmployeeNotFoundException("No employee with id: "+ id);
		}
		
		return employee;
	}
	
	@RequestMapping(value="/employee/update", method = RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee employee){
		System.out.println(employee);
		return employeeService.update(employee);
	}
	
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable String id){
		employeeService.deleteEmployee(id);
	}
}
