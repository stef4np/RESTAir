package com.project.RESTAir.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RESTAir.dto.FlightDTO;
import com.project.RESTAir.entities.Flight;
import com.project.RESTAir.repositories.FlightRepository;
import com.project.RESTAir.services.FlightService;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<FlightDTO> getAllFlights() {
		ArrayList<FlightDTO> retVal = new ArrayList<>();
		Iterable<Flight> flights = flightRepository.findAll();
		for (Flight f : flights) {
			retVal.add(new FlightDTO(f.getNumber()));
		}
		return retVal;
	}

}
