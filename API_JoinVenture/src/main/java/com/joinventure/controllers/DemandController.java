package com.joinventure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.Demand;
import com.joinventure.services.DemandService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/demands")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandController {
	@Autowired
	private DemandService demandService;

	@GetMapping("")
	public ResponseEntity<List<Demand>> getAllDemands() {
		return ResponseEntity.ok().body(demandService.findAllDemands());
	}

	@GetMapping("/byId")
	public ResponseEntity<Optional<Demand>> getAllDemandsById(@RequestParam Long id) {
		Optional<Demand> demand = demandService.findById(id);

		if (demand.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(demand, HttpStatus.OK);
	}

	@GetMapping("/byName")
	public ResponseEntity<List<Demand>> buscarPorStringEnNombre(@RequestParam String name) {

		List<Demand> demands = demandService.findByStringInName(name);

		if (demands.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(demands, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> createNewUser(@RequestBody Demand demand) {
		return demandService.createNewDemand(demand);
	}

	@PutMapping("/updateDemand")
	public ResponseEntity<?> updateDemand(@RequestParam String name, @RequestBody Demand updatedDemand) {
        boolean updated = demandService.updateDemandByName(name, updatedDemand);
        if (updated) {
            return ResponseEntity.ok("Demanda actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La demanda no existe");
        }
    }

	@DeleteMapping("/deleteDemand")
	public ResponseEntity<?> deleteDemand(@RequestParam Long id) {
		boolean deleted = demandService.deleteDemandById(id);
		if (deleted) {
			return ResponseEntity.ok("Demanda eliminada correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La demanda no existe");
		}
	}
}
