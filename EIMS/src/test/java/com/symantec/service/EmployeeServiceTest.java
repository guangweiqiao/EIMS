package com.symantec.service;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.symantec.bean.Employee;
import com.symantec.repository.EmployeeRepository;

public class EmployeeServiceTest {
	
	@Tested
	private EmployeeService employeeService;
	
	@Injectable
	EmployeeRepository employeeRepo;
	
	@Test(dataProvider = "dataProvider1")
	public void testGetEmployeeByFirstName1(final String firstName){
		//record
		new Expectations(){
			{
				employeeRepo.findByFirstName(firstName);
				result = new Employee(firstName, anyString); 
			}
		};
		
		//replay
		Employee employee = employeeService.getEmployeeByFirstName(firstName);
		
		//verify
		Assert.assertEquals(firstName, employee.getFirstName());
	}
	
	@Test
	public void testGetEmployeeByFirstName2(){

		employeeRepo = new MockUp<EmployeeRepository>(){
			@Mock
			public Employee findByFirstName(String name){
				return new Employee();
			}
		}.getMockInstance();
	
		employeeService.setRepo(employeeRepo);
	
		Employee employee = employeeService.getEmployeeByFirstName("Joe");
		
		Assert.assertNotNull(employee);
	}
	
	@Test
	public void testGetEmployeeByFirstNameWithNull(){

		Employee employee = employeeService.getEmployeeByFirstName(null);
		
		Assert.assertNull(employee);
	}
	
	@DataProvider
	public Object[][] dataProvider1(){
		return new Object[][]{
				{"Joe"},
				{"Jeff"},
				{"Peter"},
				{"Sunny"}
		};
	}
}
