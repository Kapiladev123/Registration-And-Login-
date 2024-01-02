package com.service;

import java.util.List;

import com.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployeebyId(int id);
	
	public void deleteEmployeeById(int id);

	Employee updateEmployee(Employee employee, String email);
}
