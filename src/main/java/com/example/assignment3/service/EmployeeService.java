package com.example.assignment3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assignment3.entitys.Employee;
import com.example.assignment3.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	

	//save new user(employee) here
	public ResponseEntity<Object> saveEmployee(Employee employee){
		try {
			employeeRepository.save(employee);
			return new ResponseEntity<Object> ("user save successfully",HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	
	//get the employee by id
	public ResponseEntity<Object> getEmployeeById(Integer id) {
		try {
			Employee emp=employeeRepository.findByEmpId(id);
			if(emp!=null)
				return new ResponseEntity<Object>(emp,HttpStatus.OK);
			else
				throw new Exception("Wrong employee id...");
		}catch (Exception e) {
			return new ResponseEntity<Object> (e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//get all employees
	public ResponseEntity<Object> getAllEmployees(){
		try {
			return new ResponseEntity<Object> (employeeRepository.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	//update employee details 
	public ResponseEntity<Object> updateEmployeeDetails(Employee employeeUpdatedDetails) {
		try {
			Employee employee = employeeRepository.findByEmpId(employeeUpdatedDetails.getId());
			if(employeeUpdatedDetails.getName()!=null)
				employee.setName(employeeUpdatedDetails.getName());
			if(employeeUpdatedDetails.getEmail()!=null)
				employee.setEmail(employeeUpdatedDetails.getEmail());
			if(employeeUpdatedDetails.getPhone()!=null)
				employee.setPhone(employeeUpdatedDetails.getPhone());
			if(employeeUpdatedDetails.getSalary()!=0.0)
				employee.setSalary(employeeUpdatedDetails.getSalary());
			
			employeeRepository.save(employee);
			return new ResponseEntity<Object> ("Employee details updated",HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object> ("Invalid employee id !!",HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	//remove employee by id
	public ResponseEntity<Object> removeEmployeeById(int id){
		try{
			Employee emp = employeeRepository.findByEmpId(id);
			if(emp!=null) {
				employeeRepository.delete(emp);
				return new ResponseEntity<Object> ("Employee remove by id "+id,HttpStatus.OK);
			}else
				throw new Exception("Wrong email id");
		}catch (Exception e) {
			return new ResponseEntity<Object> (e.getMessage(),HttpStatus.BAD_REQUEST);	
		}
	}
	
	
	//remove all employee
	public ResponseEntity<Object> removeAllEmployees(){
		try {
			employeeRepository.deleteAll();
			return new ResponseEntity<Object> ("All employees are remove from database.",HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
