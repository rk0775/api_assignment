package com.example.assignment3.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.assignment3.entitys.Employee;
import com.example.assignment3.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository empRepository;
	
	private List<Employee> employees=new ArrayList();
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		Employee emp1=new Employee(11,"rohit ","rohit@gmail.com","8323416743",40000);
		Employee emp2=new Employee(12,"amar ahuja","amar@gmail.com","9373818743",50000);
		employees.add(emp1);
		employees.add(emp2);
	}

	//test the save employee service
	@Test
	void testSaveEmployee() {
		when(empRepository.save(employees.get(0))).thenReturn(employees.get(0));
		ResponseEntity<Object> actual = employeeService.saveEmployee(employees.get(0));
		System.out.println("save : "+actual);
		assertEquals(actual.getStatusCodeValue(), 200);
	}

	//test the get employee by id service
	@Test
	void testGetEmployeeById() {
		when(empRepository.findByEmpId(13)).thenReturn(null);
		ResponseEntity<Object> actual = employeeService.getEmployeeById(13);
		assertEquals(actual.getStatusCodeValue(), 400);
	}
	
	
	@Test
	void test2GetEmployeeById() {
		when(empRepository.findByEmpId(12)).thenReturn(employees.get(1));
		ResponseEntity<Object> actual = employeeService.getEmployeeById(12);
		assertEquals(actual.getStatusCodeValue(), 200);
	}

	//test get all employee service
	@Test
	void testGetAllEmployees() {
		when(empRepository.findAll()).thenReturn(employees);
		
		ResponseEntity<Object> actual = employeeService.getAllEmployees();
		assertEquals(actual.getStatusCodeValue(), 200);
	}

	//test update employee service
	@Test
	void testUpdateEmployeeDetails() {
		Employee updatedEmp = new Employee(11,"rohit",null,null,45000);
		
		when(empRepository.findByEmpId(updatedEmp.getId())).thenReturn(employees.get(0));
		when(empRepository.save(employees.get(0))).thenReturn(updatedEmp);
		
		ResponseEntity<Object> actual = employeeService.updateEmployeeDetails(updatedEmp);
		assertEquals(actual.getStatusCodeValue(), 200);

	}

	//test remove employee by id service
	@Test
	void testRemoveEmployeeById() {
		when(empRepository.findByEmpId(13)).thenReturn(null);
		
		ResponseEntity<Object> actual = employeeService.removeEmployeeById(13);
		assertEquals(actual.getStatusCodeValue(), 400);
	}
	
	@Test
	void test2RemoveEmployeeById() {
		when(empRepository.findByEmpId(11)).thenReturn(employees.get(0));
		ResponseEntity<Object> actual = employeeService.removeEmployeeById(11);
		assertEquals(actual.getStatusCodeValue(), 200);
	}

	//test remove all employee service
	@Test
	void testRemoveAllEmployees() {
		ResponseEntity<Object> actual = employeeService.removeAllEmployees();
		assertEquals(actual.getStatusCodeValue(), 200);
	}

}
