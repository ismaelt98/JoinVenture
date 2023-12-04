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

import com.joinventure.entities.ProgrammerRole;
import com.joinventure.entities.User;
import com.joinventure.repositories.ProgrammerRoleRepository;
import com.joinventure.services.ProgrammerRoleService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/programmers-roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProgrammerRoleController {

	@Autowired
	private ProgrammerRoleRepository roleUserRepository;
	
	@Autowired
	private ProgrammerRoleService roleUserService;
	
	@GetMapping("")
	public ResponseEntity<List<ProgrammerRole>> getAllRoleUsers(){
		return ResponseEntity.ok().body(roleUserService.findAllRoles());
	}
	
	@GetMapping("/programer-role")
	public ResponseEntity<Optional <ProgrammerRole>> getProgrammerRole(@RequestParam Long name){
		Optional <ProgrammerRole> user = roleUserRepository.findById(name);
		return ResponseEntity.ok().body(user); 
	}
	
	@PostMapping("")
	public ResponseEntity<String> createRoleUser(@RequestBody ProgrammerRole roleUser){
		
		return roleUserService.createNewRoleUser(roleUser);
	}

	@PutMapping("")
	public ResponseEntity<ProgrammerRole> updateRole(@RequestParam Long id, @RequestBody ProgrammerRole roleDetails){
		ProgrammerRole role = roleUserService.findRoleById(id);
		role.setName(roleDetails.getName());
		
		final ProgrammerRole updatedRole = roleUserRepository.save(role);
		return ResponseEntity.ok().body(updatedRole);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRole(@PathVariable(value = "id") Long id) {
		ProgrammerRole user = roleUserService.findRoleById(id);

        roleUserRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}

