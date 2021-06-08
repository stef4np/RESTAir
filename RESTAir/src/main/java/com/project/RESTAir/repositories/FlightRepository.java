package com.project.RESTAir.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.RESTAir.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {
	
	public Flight findByNumber(String number);

}
