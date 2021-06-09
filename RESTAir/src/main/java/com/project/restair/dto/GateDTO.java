package com.project.restair.dto;

import com.project.restair.entities.Gate;
import com.project.restair.utils.TimesCalcUtil;

public class GateDTO {
	
	private String number;
	
	private String flightNumber;
	
	private String availableFrom;
	
	private String availableTo;
	
	public GateDTO() {}

	public GateDTO(String number, String flightNumber, String availableFrom, String availableTo) {
		this.number = number;
		this.flightNumber = flightNumber;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
	}
	
	public GateDTO(Gate gate) {
		this.number = gate.getNumber();
		if (gate.getFlight() != null) {
			this.flightNumber = gate.getFlight().getNumber();
		}
		if (gate.getAvailableFrom() != null) {
			this.availableFrom = TimesCalcUtil.formatTimeToString(gate.getAvailableFrom());			
		}
		if (gate.getAvailableTo() != null) {
			this.availableTo = TimesCalcUtil.formatTimeToString(gate.getAvailableTo());			
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(String availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(String availableTo) {
		this.availableTo = availableTo;
	}

}
