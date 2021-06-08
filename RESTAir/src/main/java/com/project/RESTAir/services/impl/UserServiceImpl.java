package com.project.RESTAir.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RESTAir.entities.User;
import com.project.RESTAir.repositories.UserRepository;
import com.project.RESTAir.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByUsername(final String username) {
		return userRepository.findByUsername(username);
	}

}
