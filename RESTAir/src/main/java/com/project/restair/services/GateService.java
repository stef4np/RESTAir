package com.project.restair.services;

import java.util.List;

import com.project.restair.dto.GateDTO;

public interface GateService {
	
	public List<GateDTO> getAllGates();
	
	public GateDTO assignGate(final String flightNum);
	
	public GateDTO assignGate(final String gateNum, final String flightNum);
	
	public GateDTO unassignGate(final String gateNum);

	public GateDTO setTimes(final String gateNum, final String availableFrom, final String availableTo);

	public GateDTO clearTimes(final String gateNum);

}
