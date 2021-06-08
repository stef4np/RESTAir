package com.project.RESTAir.services;

import com.project.RESTAir.entities.User;

public interface UserService {
	
	public User findUserByUsername(final String username);

}
