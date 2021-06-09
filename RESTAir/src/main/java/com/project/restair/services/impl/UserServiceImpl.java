package com.project.restair.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.restair.entities.User;
import com.project.restair.repositories.UserRepository;
import com.project.restair.services.UserService;

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
