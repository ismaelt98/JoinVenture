package com.joinventure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.DTOs.TechnologyDTO;

import com.joinventure.services.TechnologyService;


import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/technologies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TechnologyController {

	
	@Autowired
	private TechnologyService userService;
	
	@GetMapping("")
	public ResponseEntity<List<TechnologyDTO>> getAllTechnologies(){
		return ResponseEntity.ok().body(userService.findAllTechnologiesDTO());
	}
	
	@GetMapping("/technology")
	public ResponseEntity<List<TechnologyDTO>> getTechnologyById(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.findAllTechnologiesDTO());
	}
}
