package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JWT.JwtHelper;
import com.Response.CustomeResponse;
import com.dto.LoginDto;
import com.entity.Employee;
import com.repository.EmployeeRepo;
import com.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService  service;
	
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager manager;
	
	
	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginDto loginDto){
		Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authenticate);
	String token = jwtHelper.generateToken(authenticate);
	
	new CustomeResponse();
	return CustomeResponse.login("Success", token, loginDto);
	//return CustomeResponse.response("Login Success", HttpStatus.OK, loginDto);
	
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<Object> save(@Valid @RequestBody Employee employee){
		Optional<Employee> email = repo.findByEmail(employee.getEmail());
//		if(repo.existByEmail(employee.getEmail())) {
			if(email.isPresent()) {
			new CustomeResponse();
			return CustomeResponse.alreadyExist("Employee Email already present in the DATABASE", HttpStatus.FOUND, true);
		}
		new CustomeResponse();
		return CustomeResponse.response("Success", HttpStatus.CREATED, service.saveEmployee(employee));
	}
	
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Object> getById(@PathVariable int id){
		new CustomeResponse();
		return CustomeResponse.response("Success", HttpStatus.OK, service.getEmployeebyId(id));
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable int id){
		Optional<Employee> optional = repo.findById(id);
		if(optional.isEmpty()) {
			new CustomeResponse();
			return CustomeResponse.alreadyExist("Employee not found ", HttpStatus.NOT_FOUND,false);
		}
		service.deleteEmployeeById(id);
		return CustomeResponse.deleteResponse("Success", HttpStatus.OK, "Deleted SuccessFully");
	}
	
	@PutMapping("/employees/{email}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable String email){
		Optional<Employee> email1 = repo.findByEmail(employee.getEmail());
		if(email1==null) {
			return CustomeResponse.alreadyExist("Email not found in database", HttpStatus.NOT_FOUND, false);
		}
		return CustomeResponse.response("Success", HttpStatus.OK, service.updateEmployee(employee, email));
	}
	
}
