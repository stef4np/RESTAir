package com.project.restair.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.restair.dto.GateDTO;
import com.project.restair.entities.Flight;
import com.project.restair.entities.Gate;
import com.project.restair.exceptions.CustomAlreadyAssignedException;
import com.project.restair.exceptions.CustomNotFoundException;
import com.project.restair.exceptions.CustomValidationException;
import com.project.restair.repositories.FlightRepository;
import com.project.restair.repositories.GateRepository;
import com.project.restair.services.GateService;
import com.project.restair.utils.TimesCalcUtil;

@Service
@Transactional
public class GateServiceImpl implements GateService {
	
	@Autowired
	private GateRepository gateRepository;
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<GateDTO> getAllGates() {
		/*
		 This code could be used instead of the one that is used now
		 but it would be harder to debug
		 
		 return ((List<Gate>) gateRepository.findAll())
				.stream().map(GateDTO::new).collect(Collectors.toList());
		 */
		ArrayList<GateDTO> retVal = new ArrayList<>();
		Iterable<Gate> gates = gateRepository.findAll();
		for (Gate g : gates) {
			retVal.add(new GateDTO(g));
		}
		return retVal;
	}
	
	@Override
	public GateDTO assignGate(final String flightNum) {
		Flight flight = flightRepository.findByNumber(flightNum);
		if (flight == null) {
			throw new CustomNotFoundException(String.format("Flight %s was not found!", flightNum));
		}
		
		if (flight.getGate() != null) {
			throw new CustomAlreadyAssignedException(String.format("Flight %s already assigned!", flightNum));
		}
		
		Integer currTimeValue = TimesCalcUtil.calcTimeFromString(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		Gate gate = gateRepository.findFirstAvailableGate(currTimeValue);
		
		if (gate == null) {
			throw new CustomAlreadyAssignedException("All gates are currently unavailable!");
		}
		
		gate.setFlight(flight);
		gateRepository.save(gate);
		
		return new GateDTO(gate);
	}

	@Override
	public GateDTO assignGate(final String gateNum, final String flightNum) {
		Flight flight = flightRepository.findByNumber(flightNum);
		if (flight == null) {
			throw new CustomNotFoundException(String.format("Flight %s was not found!", flightNum));
		}
		
		Gate gate = gateRepository.findByNumber(gateNum);
		if (gate == null) {
			throw new CustomNotFoundException(String.format("Gate %s was not found!", gateNum));
		}
		
		if (flight.getGate() != null) {
			throw new CustomAlreadyAssignedException(String.format("Flight %s already assigned!", flightNum));
		}
		
		if (gate.getFlight() != null) {
			throw new CustomAlreadyAssignedException(String.format("Gate %s already assigned!", gateNum));
		}
		
		if (gate.getAvailableFrom() != null && gate.getAvailableTo() != null) {
			Integer currTimeValue = TimesCalcUtil.calcTimeFromString(
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
			if (currTimeValue < gate.getAvailableFrom() || currTimeValue > gate.getAvailableTo()) {
				throw new CustomAlreadyAssignedException(String.format("Gate %s not available at this time!", gateNum));
			}
		}
		
		gate.setFlight(flight);
		gateRepository.save(gate);
		
		return new GateDTO(gate);
	}

	@Override
	public GateDTO unassignGate(final String gateNum) {
		Gate gate = gateRepository.findByNumber(gateNum);
		if (gate == null) {
			throw new CustomNotFoundException(String.format("Gate %s was not found!", gateNum));
		}
		
		if (gate.getFlight() != null) {
			gate.setFlight(null);
			gateRepository.save(gate);
		}
			
		return new GateDTO(gate);
	}

	@Override
	public GateDTO setTimes(final String gateNum, final String availableFrom, final String availableTo) {
		Gate gate = gateRepository.findByNumber(gateNum);
		if (gate == null) {
			throw new CustomNotFoundException(String.format("Gate %s was not found!", gateNum));
		}
		//Not the greatest string to time conversion but it will do for now
		Integer avFrom = TimesCalcUtil.calcTimeFromString(availableFrom);
		Integer avTo = TimesCalcUtil.calcTimeFromString(availableTo);
		
		if (avFrom < 0 || avFrom > 86340 || avTo < 0 || avTo > 86340) {
			throw new CustomValidationException("Times must be between 00:00 and 23:59!");
		}
		
		if (avFrom > avTo) {
			throw new CustomValidationException("From Time can't be after To Time!");
		}
		
		if (avTo - avFrom < 900) {
			throw new CustomValidationException("Times must be at least 15 minutes apart!");
		}
				
		gate.setAvailableFrom(avFrom);
		gate.setAvailableTo(avTo);
		gateRepository.save(gate);
		
		return new GateDTO(gate);
	}

	@Override
	public GateDTO clearTimes(final String gateNum) {
		Gate gate = gateRepository.findByNumber(gateNum);
		if (gate == null) {
			throw new CustomNotFoundException(String.format("Gate %s was not found!", gateNum));
		}
		gate.setAvailableFrom(null);
		gate.setAvailableTo(null);
		gateRepository.save(gate);
		
		return new GateDTO(gate);
	}
	

}
