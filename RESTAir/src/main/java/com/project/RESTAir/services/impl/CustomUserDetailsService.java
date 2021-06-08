package com.project.RESTAir.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.RESTAir.entities.User;
import com.project.RESTAir.repositories.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Username or Password!");
		}
		
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if (user.getIsAdmin()) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return org.springframework.security.core.userdetails.User.withUsername(username).
				password(user.getPassword()).authorities(roles).build();
	}

}
