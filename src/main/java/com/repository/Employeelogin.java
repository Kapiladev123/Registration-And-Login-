package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Employee;

public interface Employeelogin extends JpaRepository<Employee, Integer>{

	public Employee findByEmail(String email);
}
