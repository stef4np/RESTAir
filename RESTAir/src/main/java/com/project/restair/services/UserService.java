package com.project.restair.services;

import com.project.restair.entities.User;

public interface UserService {
	
	public User findUserByUsername(final String username);

}
