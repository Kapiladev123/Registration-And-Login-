package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Exception.ResourceNotFoundException;
import com.entity.Employee;
import com.repository.EmployeeRepo;
import com.repository.Employeelogin;

@Service
public class EmplpyeeServiceImpl  implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private Employeelogin employeelogin;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Employee saveEmployee(Employee employee) {
		// employee.setRole("ROLE_USER");
		employee.setPassword(encoder.encode(employee.getPassword()));
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeebyId(int id) {
		return employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Id not found : "+id));
	}

	@Override
	public void deleteEmployeeById(int id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Id not found : "+id));
		employeeRepo.delete(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee, String email) {
			Employee employee2 = employeelogin.findByEmail(email);
			employee2.setPassword(employee.getPassword());
			return employeeRepo.save(employee2);
	}

	


}
