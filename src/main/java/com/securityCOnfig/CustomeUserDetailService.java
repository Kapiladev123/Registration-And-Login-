package com.securityCOnfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.entity.Employee;
import com.repository.Employeelogin;

public class CustomeUserDetailService implements UserDetailsService{

	@Autowired
	private Employeelogin employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Employee employee = employeeRepo.findByEmail(email);
	if(employee == null) {
		throw new UsernameNotFoundException("Employee email not found : "+email);
	}
	else {
//		return new CostomeUser(employee);
		return new CostomeUser(employee);
	}
		
	
		
	}

}
