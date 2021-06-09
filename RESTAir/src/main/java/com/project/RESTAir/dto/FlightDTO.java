package com.project.RESTAir.dto;

import com.project.RESTAir.entities.Flight;

public class FlightDTO {
	
	private String flightNumber;
	
	public FlightDTO() {}
	
	public FlightDTO(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public FlightDTO(Flight flight) {
		this.flightNumber = flight.getNumber();
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
