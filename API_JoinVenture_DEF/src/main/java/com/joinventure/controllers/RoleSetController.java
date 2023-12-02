package com.joinventure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.DTOs.RoleSetDTO;

import com.joinventure.services.RoleSetService;


import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/roleset")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleSetController {
	
	@Autowired
	private RoleSetService userService;
	
	@GetMapping("")
	public ResponseEntity<List<RoleSetDTO>> getAllTechnologies(){
		return ResponseEntity.ok().body(userService.findAllRoleSetUser());
	}

}
