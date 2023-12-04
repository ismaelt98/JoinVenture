package com.joinventure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.DTOs.SetDTO;
import com.joinventure.services.SetService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/sets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SetController {

	@Autowired
	private SetService setService;

	

	@GetMapping("")
	public ResponseEntity<List<SetDTO>> getAllRoleSets() {
		return ResponseEntity.ok().body(setService.findAllRoleSetUser());
	}
}
