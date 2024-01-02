package com.securityCOnfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entity.Employee;

public class CostomeUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Employee employee;
	
	
	public CostomeUser(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority Authority = new SimpleGrantedAuthority(employee.getRole());
		return Arrays.asList(Authority);
	}

	@Override
	public String getPassword() {
		
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	

}
