package com.example.assignment3.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.assignment3.entitys.Employee;
import com.example.assignment3.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MainController.class)
class MainControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
	ObjectMapper objectMapper=new ObjectMapper();
	
	//this method provide employee object for testing
    private Employee getEmployee() {
        Employee employee = new Employee(11,"ram","ram@test.com","8976556688",40000);
        return employee;
    }
	

	@Test
	void testCreateNewUser() throws Exception {
		Employee emp =getEmployee();
		String jsonReq= objectMapper.writeValueAsString(emp);
		when(employeeService.saveEmployee(emp)).thenReturn(new ResponseEntity<Object>("user save successfully",HttpStatus.OK));
		
		MvcResult result=mockMvc.perform(put("/saveEmp").content(jsonReq).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}
	

	@Test
	void testGetEmployeeById() throws Exception {
		when(employeeService.getEmployeeById(2)).thenReturn(new ResponseEntity<Object>(getEmployee(),HttpStatus.OK));
		MvcResult result=mockMvc.perform(get("/getEmpById").param("id","2").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}
	

	@Test
	void testGetEmployees() throws Exception {
		when(employeeService.getAllEmployees()).thenReturn(new ResponseEntity<Object>(getEmployee(),HttpStatus.OK));
		MvcResult result=mockMvc.perform(get("/getAllEmp").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

	
	@Test
	void testUpdateEmployee() throws Exception {
		Employee emp =getEmployee();
		String jsonReq= objectMapper.writeValueAsString(emp);
		when(employeeService.updateEmployeeDetails(emp)).thenReturn(new ResponseEntity<Object>("employee updated",HttpStatus.OK));
		
		MvcResult result=mockMvc.perform(post("/updateEmp").content(jsonReq).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}
	
	@Test
	void test2UpdateEmployee() throws Exception {
		Employee emp =getEmployee();
		String jsonReq= objectMapper.writeValueAsString(emp);
		when(employeeService.updateEmployeeDetails(emp)).thenReturn(new ResponseEntity<Object>("employee updated",HttpStatus.BAD_REQUEST));
		
		MvcResult result=mockMvc.perform(post("/updateEmp").content(jsonReq).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

	
	@Test
	void testRemoveEmployeeById() throws Exception {
		when(employeeService.removeEmployeeById(3)).thenReturn(new ResponseEntity<Object>(getEmployee(),HttpStatus.OK));
		MvcResult result=mockMvc.perform(delete("/removeEmpById").param("id","3").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

	
	@Test
	void testRemoveAllEmployees() throws Exception {
		when(employeeService.removeAllEmployees()).thenReturn(new ResponseEntity<Object>(getEmployee(),HttpStatus.OK));
		MvcResult result=mockMvc.perform(delete("/removeAllEmp").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

}
