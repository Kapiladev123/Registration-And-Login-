package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	public Optional<Employee> findByEmail(String email);
	

	
//	Boolean existByEmail(String email);

}
