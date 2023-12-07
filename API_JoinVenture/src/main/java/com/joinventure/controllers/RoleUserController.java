package com.joinventure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.joinventure.entities.RoleUser;
import com.joinventure.repositories.RoleUserRepository;
import com.joinventure.services.RoleUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.java.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log
@RestController
@RequestMapping("/roles-user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleUserController {

    private static final Logger logger = LoggerFactory.getLogger(RoleUserController.class);
    
    @Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@GetMapping("")
	public ResponseEntity<List<RoleUser>> getAllRoleUsers(){
		return ResponseEntity.ok().body(roleUserService.findAllUsers());
	}
	@GetMapping("/role-user")
	public ResponseEntity<Optional <RoleUser>> getProgrammerRole(@RequestParam String name){
		Optional <RoleUser> user = roleUserRepository.findByName(name);
		return ResponseEntity.ok().body(user); 
	}
	
	@PostMapping("")
	public ResponseEntity<String> createRoleUser(@RequestBody RoleUser roleUser){
		
        logger.info("RoleUser received: {}", roleUser);

		return roleUserService.createNewRoleUser(roleUser);
	}

	@PutMapping("")
	public ResponseEntity<RoleUser> updateRole(@RequestParam Long id, @RequestBody RoleUser roleDetails){
		RoleUser role = roleUserService.findRoleById(id);
		role.setName(roleDetails.getName());
		
		final RoleUser updatedRole = roleUserRepository.save(role);
		return ResponseEntity.ok().body(updatedRole);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRole(@PathVariable(value = "id") Long id) {
        RoleUser user = roleUserService.findRoleById(id);

        roleUserRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response); 
	}
}
