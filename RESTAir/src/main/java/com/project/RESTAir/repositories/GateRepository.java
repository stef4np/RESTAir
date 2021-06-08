package com.project.RESTAir.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.RESTAir.entities.Gate;

public interface GateRepository extends CrudRepository<Gate, Long> {
	
	public Gate findByNumber(String number);

}
