package com.example.assignment3.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.assignment3.entitys.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

	@Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    //this method provide employee object for testing
    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName("Ram");
        employee.setEmail("Ram@test.com");
        employee.setPhone("8989765543");
        employee.setSalary(40000);
        return employee;
     }

    
	@Test
	void testFindByEmpId() {
		Employee employee = getEmployee();	
	    employeeRepository.save(employee);
	    Employee result = employeeRepository.findByEmpId(employee.getId());
	    assertEquals(employee.getId(), result.getId());	
	}

	@Test
	void testSave() {
		Employee employee = getEmployee();
	    employeeRepository.save(employee);
	    Employee found = employeeRepository.findById(employee.getId()).get();
	    assertEquals(employee.getId(), found.getId());	

	}

	@Test
	void testFindAll() {
		Employee employee = getEmployee();
	    employeeRepository.save(employee);
	    List<Employee> result = new ArrayList<>();
	    employeeRepository.findAll().forEach(e -> result.add(e));
	    assertEquals(result.size(), 1);	     	
	    
	}

	@Test
	void testDelete() {
	    employeeRepository.deleteById(4);
	    List<Employee> result = new ArrayList<>();
	    employeeRepository.findAll().forEach(e -> result.add(e));
	    assertEquals(result.size(), 0);
	}

	@Test
	void testDeleteAll() {
	      employeeRepository.deleteAll();
	      List<Employee> result = new ArrayList<>();
	      employeeRepository.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 0);
	}

}
