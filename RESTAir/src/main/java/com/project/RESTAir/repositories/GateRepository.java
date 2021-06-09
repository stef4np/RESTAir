package com.project.RESTAir.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.RESTAir.entities.Gate;

public interface GateRepository extends CrudRepository<Gate, Long> {
	
	public Gate findByNumber(String number);
	
	@Query("SELECT g FROM Gate g WHERE g.flight IS NULL AND ((g.availableFrom IS NULL AND g.availableTo IS NULL) OR"
			+ "(g.availableFrom >= ?1 AND g.availableTo <= ?1)) AND ROWNUM = 1")
	public Gate findFirstAvailableGate(Integer currentTime);

}
