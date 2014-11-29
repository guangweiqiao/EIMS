package com.symantec.repository;

import java.util.List;
import com.symantec.bean.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String>{
    public Employee findByFirstName(String firstName);
    public List<Employee> findByLastName(String lastName);
    public List<Employee> findByDepartment(String department);
}
