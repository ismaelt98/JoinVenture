package com.joinventure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Demand;
import com.joinventure.entities.User;
import com.joinventure.repositories.DemandRepository;

@Service
public class DemandService {

	@Autowired
	private DemandRepository demandRepo;

	public List<Demand> findAllDemands() {
		return demandRepo.findAll();
	}

	public Optional<Demand> findById(Long id) {
		return demandRepo.findById(id);
	}

	public List<Demand> findByStringInName(String searchString) {
		List<Demand> demandList1 = demandRepo.findAll().stream()
				.filter(demand -> demand.getName().contains(searchString)).toList();
		return demandList1;
	}
	
	public ResponseEntity<Object> createNewDemand(Demand demand) {
		
		if (demandRepo.existsByName(demand.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El nombre ya existe en la base de datos");
        } else {
            
            return ResponseEntity.status(HttpStatus.CREATED).body(demandRepo.save(demand));
        }
       
           
       
    }
	
	public boolean updateDemandByName(String name, Demand updatedDemand) {
        if (demandRepo.existsByName(name)) {
            
        	Demand existingDemand = demandRepo.findByName(name);
            
            existingDemand.setName(updatedDemand.getName());
            
            
            demandRepo.save(existingDemand); 
            return true; 
        } else {
            return false; 
        }
    }
	
	public boolean deleteDemandById(Long id) {
        if (demandRepo.existsById(id)) {
            demandRepo.deleteById(id);
            return true; 
        } else {
            return false; 
        }
    }
	
	
}
