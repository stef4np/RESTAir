package com.project.RESTAir.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.RESTAir.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
}
