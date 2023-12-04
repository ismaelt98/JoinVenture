package com.joinventure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.Technology;
import com.joinventure.entities.DTOs.TechnologyDTO;
import com.joinventure.repositories.TechnologyRepository;
import com.joinventure.services.TechnologyService;


import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/technologies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TechnologyController {

	
	@Autowired
	private TechnologyService userService;
	
	@Autowired
	private TechnologyRepository technoRepo;
	
	@GetMapping("")
	public ResponseEntity<List<TechnologyDTO>> getAllTechnologies(){
		return ResponseEntity.ok().body(userService.findAllTechnologiesDTO());
	}
	
	@GetMapping("/technology")
	public ResponseEntity<List<TechnologyDTO>> getTechnologyById(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.findAllTechnologiesDTO());
	}
	
	@PostMapping("")
	public ResponseEntity<String> create(@RequestBody Technology technology){
		return userService.createNewTechnology(technology);
	}
	
	@PutMapping("")
	public ResponseEntity<Technology> updateTechnology(@RequestParam Long id, @RequestBody Technology technoDetails){
		Technology technology = userService.findTechById(id);
		technology.setUser_user_id(technoDetails.getUser_user_id());
		technology.setLenguage_lenguage_id(technoDetails.getLenguage_lenguage_id());
		technology.setFrameworks_frameworks_id(technoDetails.getFrameworks_frameworks_id());
		
		final Technology updatedTechno = technoRepo.save(technology);
		return ResponseEntity.ok().body(updatedTechno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTechnology(@PathVariable(value = "id") Long id){
		Technology technology = userService.findTechById(id);
		
		technoRepo.delete(technology);
		Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}