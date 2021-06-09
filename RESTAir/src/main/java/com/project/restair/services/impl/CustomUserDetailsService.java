package com.project.restair.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.restair.entities.User;
import com.project.restair.repositories.UserRepository;

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
