package com.project.RESTAir.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.RESTAir.dto.FlightDTO;
import com.project.RESTAir.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@GetMapping
	public List<FlightDTO> allGates() {
		return flightService.getAllFlights();
	}
	
}
