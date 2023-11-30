package com.joinventure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.RoleUser;
import com.joinventure.repositories.RoleUserRepository;
import com.joinventure.services.RoleUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/role-users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleUserController {
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@GetMapping("")
	public ResponseEntity<List<RoleUser>> getAllRoleUsers(){
		return ResponseEntity.ok().body(roleUserService.findAllUsers());
	}
	
	@PostMapping("")
	public ResponseEntity<String> createUser(@RequestBody RoleUser roleUser){
		
		return roleUserService.createNewUser(roleUser);
	}

}
