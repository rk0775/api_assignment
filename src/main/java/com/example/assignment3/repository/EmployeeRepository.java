package com.example.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.assignment3.entitys.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	@Query("select emp from Employee emp where emp.id=:empId")
	public Employee findByEmpId(@Param("empId")int id);
}
