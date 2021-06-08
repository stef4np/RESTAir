package com.project.RESTAir.dto;

public class FlightDTO {
	
	private String flightNumber;
	
	public FlightDTO() {}
	
	public FlightDTO(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
