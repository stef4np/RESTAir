package com.project.restair.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.project.restair.entities.User;
import com.project.restair.services.UserService;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		User user = userService.findUserByUsername(auth.getName());
		if (user != null) {
			Authentication retVal = super.authenticate(auth);
	        return new UsernamePasswordAuthenticationToken(user, retVal.getCredentials(), retVal.getAuthorities());
		}
		throw new AuthenticationCredentialsNotFoundException("Invalid Username or Password");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
