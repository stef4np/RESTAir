package com.project.restair.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.restair.dto.FlightDTO;
import com.project.restair.entities.Flight;
import com.project.restair.repositories.FlightRepository;
import com.project.restair.services.FlightService;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<FlightDTO> getAllFlights() {
		/*
		 This code could be used instead of the one that is used now
		 but it would be harder to debug
		 
		 return ((List<Flight>) flightRepository.findAll())
				.stream().map(FlightDTO::new).collect(Collectors.toList());
		 */
		
		ArrayList<FlightDTO> retVal = new ArrayList<>();
		Iterable<Flight> flights = flightRepository.findAll();
		for (Flight f : flights) {
			retVal.add(new FlightDTO(f));
		}
		return retVal;
	}

}
