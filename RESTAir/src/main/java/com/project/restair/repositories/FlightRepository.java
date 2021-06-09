package com.project.restair.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.restair.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {
	
	public Flight findByNumber(String number);

}
