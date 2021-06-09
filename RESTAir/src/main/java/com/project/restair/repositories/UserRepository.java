package com.project.restair.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.restair.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
}
