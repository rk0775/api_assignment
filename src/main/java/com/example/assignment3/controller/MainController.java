package com.example.assignment3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment3.entitys.Employee;
import com.example.assignment3.service.EmployeeService;

@RestController
public class MainController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	//this for save the employee
	@PutMapping("/saveEmp")
	public ResponseEntity<Object> createNewUser(@RequestBody Employee employee){
		ResponseEntity<Object> response= employeeService.saveEmployee(employee);
		return response;
	}
	
	//this for get the employee by id
	@GetMapping("/getEmpById")
	public ResponseEntity<Object> getEmployeeById(@RequestParam("id") Integer id){
		ResponseEntity<Object> response= employeeService.getEmployeeById(id);
		return response;
	}
	
	//this for get all employee
	@GetMapping("/getAllEmp")
	public ResponseEntity<Object> getEmployees(){
		ResponseEntity<Object> response= employeeService.getAllEmployees();
		return response;
	}
	
	//this for update employee
	@PostMapping("/updateEmp")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employeeUpdatedDetails){
		if(employeeUpdatedDetails.getId()<=0) {
			return new ResponseEntity<Object>("employee id required for update employee!!",HttpStatus.BAD_REQUEST);
		}else {
			ResponseEntity<Object> response= employeeService.updateEmployeeDetails(employeeUpdatedDetails);
			return response;
		}
	}
	
	//this for remove employee by id
	@DeleteMapping("/removeEmpById")
	public ResponseEntity<Object> removeEmployeeById(@RequestParam("id") int id){
		ResponseEntity<Object> response= employeeService.removeEmployeeById(id);
		return response;
	}
	
	//this for remove all employee
	@DeleteMapping("/removeAllEmp")
	public ResponseEntity<Object> removeAllEmployees(){
		ResponseEntity<Object> response= employeeService.removeAllEmployees();
		return response;
	}
	
}
