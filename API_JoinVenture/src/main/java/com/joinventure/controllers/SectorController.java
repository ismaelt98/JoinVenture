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

import com.joinventure.entities.Sector;
import com.joinventure.services.SectorService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/sectors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SectorController {
	@Autowired
	private SectorService sectorService;
	
	@GetMapping("")
	public ResponseEntity<List<Sector>> getAllSectors() {
		return ResponseEntity.ok().body(sectorService.findAllSectors());
	}
	
	@GetMapping("/byId")
	public ResponseEntity<Optional<Sector>> getAllSectorsById(@RequestParam Long id){
		Optional<Sector> sector = sectorService.findById(id);
		
		if (sector.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sector, HttpStatus.OK);
	}
	
	@GetMapping("/byName")
	public ResponseEntity<List<Sector>> buscarPorStringEnNombre(@RequestParam String name){
		List<Sector> sector = sectorService.findByStringInName(name);
		
		if (sector.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sector, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createNewSector(@RequestBody Sector sector){
		return sectorService.createNewSector(sector);
	}
	
	@PutMapping("/updateSector")
	public ResponseEntity<?> updateSector(@RequestParam String name, @RequestBody Sector updateSector){
		boolean updated = sectorService.updateSectorByName(name, updateSector);
		if(updated) {
			return ResponseEntity.ok("Sector actualiza correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El sector no existe");
		}
	}
	
	@DeleteMapping("/deleteSector")
	public ResponseEntity<?> deleteSector(@RequestParam Long id){
		boolean deleted = sectorService.deleteSectorById(id);
		if(deleted) {
			return ResponseEntity.ok("Sector eliminada correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El sector no existe");
		}
	}
}
