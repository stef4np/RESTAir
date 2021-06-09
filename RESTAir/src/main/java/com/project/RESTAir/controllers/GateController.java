package com.project.RESTAir.controllers;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.RESTAir.dto.FlightDTO;
import com.project.RESTAir.dto.GateDTO;
import com.project.RESTAir.dto.GateTimesDTO;
import com.project.RESTAir.exceptions.CustomValidationException;
import com.project.RESTAir.services.GateService;

@RestController
@RequestMapping("/gates")
public class GateController {
	
	@Autowired
	private GateService gateService;
	
	@GetMapping
	public List<GateDTO> allGates() {
		return gateService.getAllGates();
	}
	
	@PostMapping("/assign")
	public GateDTO assignGate(@RequestBody FlightDTO flightDTO) {
		return gateService.assignGate(flightDTO.getFlightNumber());
	}
	
	@PostMapping("/{gatenum}/assign")
	public GateDTO assignGate(@PathVariable String gatenum, @RequestBody(required = false) FlightDTO flightDTO) {
		if (flightDTO != null) {
			if (flightDTO.getFlightNumber() == null || flightDTO.getFlightNumber().trim().isEmpty()) {
				throw new CustomValidationException("Flight Number (flightNumber) is required!"); 
			}
			return gateService.assignGate(gatenum, flightDTO.getFlightNumber());
		}
		return gateService.unassignGate(gatenum);
	}
	
	@PostMapping("/{gatenum}/times")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public GateDTO setTimes(@PathVariable String gatenum, @RequestBody(required = false) GateTimesDTO gateTimesDTO) {
		if (gateTimesDTO != null) {
			if (gateTimesDTO.getAvailableFrom() == null || gateTimesDTO.getAvailableTo() == null ||
					gateTimesDTO.getAvailableFrom().isEmpty() || gateTimesDTO.getAvailableTo().isEmpty()) {
				throw new CustomValidationException("Both Time values are required (availableFrom and availableTo)!"); 
			}
			
			Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}"); 
			if (!pattern.matcher(gateTimesDTO.getAvailableFrom()).matches() || 
					!pattern.matcher(gateTimesDTO.getAvailableTo()).matches()) {
				throw new CustomValidationException("Time values need to be in HH:mm format!"); 
			}
			
			return gateService.setTimes(gatenum, gateTimesDTO.getAvailableFrom(), gateTimesDTO.getAvailableTo());
		}
		return gateService.clearTimes(gatenum);
	}

}
