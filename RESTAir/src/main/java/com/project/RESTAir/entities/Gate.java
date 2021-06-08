package com.project.RESTAir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "GATE")
public class Gate extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763137563553335568L;

	@Column(name = "NUMBER")
	private String number;
	
	@OneToOne
	@JoinColumn(name = "FLIGHT_ID", unique = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private Flight flight;
	
	@Column(name = "AVAILABLEFROM")
	private Integer availableFrom;
	
	@Column(name = "AVAILABLETO")
	private Integer availableTo;
	
	public Gate() {}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Integer availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Integer getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(Integer availableTo) {
		this.availableTo = availableTo;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
