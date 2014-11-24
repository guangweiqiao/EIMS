package com.symantec;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.symantec.bean.Employee;
import com.symantec.service.EmployeeService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner{

	@Autowired
	private EmployeeService employeeService;
	
    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        
    }

	@Override
	public void run(String... args) throws Exception {
		
		Employee e = employeeService.getEmployeeByFirstName("Joe");
		if(null == e){
			e = employeeService.add(new Employee("Joe", "Qiao", "AS", "Guangwei_Qiao@symantec.com"));
			System.out.println("----------Add an employee to database : "+ e);
		}
	}
}
