package com.example.assignment3.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.assignment3.entitys.Employee;
import com.example.assignment3.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
	//testing controller
	@InjectMocks
	MainController mainController;
	
	@Mock
	EmployeeService employeeService;
	
	ObjectMapper objectMapper =new ObjectMapper();
	
	
	//this method provide employee object for testing 
    private Employee getEmployee() {
        Employee employee = new Employee(11,"ram","ram@test.com","8976556688",40000);
        return employee;
     }
    
    //test the save new user controller
	@Test
	void testCreateNewUser() {
		Employee emp=getEmployee();
		when(employeeService.saveEmployee(emp)).thenReturn(new ResponseEntity<Object>("user save successfully",HttpStatus.OK));
		ResponseEntity<Object> result=mainController.createNewUser(emp);
		assertEquals(result.getStatusCodeValue(),200);
	}
	

	//test the get employee by id method logic
	@Test
	void testGetEmployeeById() {
		Employee emp=getEmployee();
		when(employeeService.getEmployeeById(emp.getId())).thenReturn(new ResponseEntity<Object>(emp,HttpStatus.OK));
		ResponseEntity<Object> result=mainController.getEmployeeById(emp.getId());

		assertEquals(result.getStatusCodeValue(),200);
		assertTrue(result.getBody().equals(emp));
	}
	
	
	@Test
	void test2GetEmployeeById() {
		//suppose employee is not exist by this id
		when(employeeService.getEmployeeById(400)).thenReturn(new ResponseEntity<Object>("Wrong employee id...",HttpStatus.BAD_REQUEST));
		ResponseEntity<Object> result=mainController.getEmployeeById(400);
		assertEquals(result.getStatusCodeValue(),400);
	}
	
	
	@Test
	void testGetEmployees() {
		List<Employee> emps = new ArrayList<>();
		emps.add(getEmployee());
		
		when(employeeService.getAllEmployees()).thenReturn(new ResponseEntity<Object>(emps,HttpStatus.OK));
		ResponseEntity<Object> result=mainController.getEmployees();

		assertEquals(result.getStatusCodeValue(),200);
		assertTrue(result.getBody().equals(emps));
	}
	

	@Test
	void testUpdateEmployee() {
		Employee emp= getEmployee(); 
		when(employeeService.updateEmployeeDetails(emp)).thenReturn(new ResponseEntity<Object>("Employee details updated",HttpStatus.OK));
		ResponseEntity<Object> result=mainController.updateEmployee(emp);
		assertEquals(result.getStatusCodeValue(),200);
	}
	
	
	@Test
	void test2UpdateEmployee() {
		//suppose employee id not provided
		Employee emp=getEmployee();
		emp.setId(0);
		ResponseEntity<Object> result=mainController.updateEmployee(emp);
		assertEquals(result.getStatusCodeValue(),400);
	}
	

	@Test
	void test3UpdateEmployee() {
		//suppose employee does not exist
		Employee emp= getEmployee(); 
		when(employeeService.updateEmployeeDetails(emp)).thenReturn(new ResponseEntity<Object>("Invalid employee id !!",HttpStatus.BAD_REQUEST));
		ResponseEntity<Object> result=mainController.updateEmployee(emp);
		assertEquals(result.getStatusCodeValue(),400);
	}
	
	
	@Test
	void testRemoveEmployeeById() {
		Employee emp= getEmployee(); 
		when(employeeService.removeEmployeeById(emp.getId())).thenReturn(new ResponseEntity<Object>("Employee remove by id "+emp.getId(),HttpStatus.OK));
		ResponseEntity<Object> result=mainController.removeEmployeeById(emp.getId());
		assertEquals(result.getStatusCodeValue(),200);
	}
	
	
	@Test
	void test2RemoveEmployeeById() {
		//suppose employee not exist by this id
		when(employeeService.removeEmployeeById(234)).thenReturn(new ResponseEntity<Object>("Wrong employee id",HttpStatus.BAD_REQUEST));
		ResponseEntity<Object> result=mainController.removeEmployeeById(234);
		assertEquals(result.getStatusCodeValue(),400);
	}

	
	@Test
	void testRemoveAllEmployees() {
		when(employeeService.removeAllEmployees()).thenReturn(new ResponseEntity<Object>("",HttpStatus.OK));
		ResponseEntity<Object> result=mainController.removeAllEmployees();
		assertEquals(result.getStatusCodeValue(),200);
	}

}
