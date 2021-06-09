package com.project.restair.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHT")
public class Flight extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -808125142556285450L;
	
	@Column(name = "NUMBER")
	private String number;
	
	@OneToOne(mappedBy = "flight")
	private Gate gate;
	
	public Flight() {}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Gate getGate() {
		return gate;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
	}
	
}
