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

import com.joinventure.entities.RoleSet;
import com.joinventure.entities.DTOs.RoleSetDTO;
import com.joinventure.repositories.RoleSetRepository;
import com.joinventure.services.RoleSetService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/roleset")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleSetController {

	@Autowired
	private RoleSetService roleSetService;

	@Autowired
	private RoleSetRepository roleSetRepository;

	@GetMapping("")
	public ResponseEntity<List<RoleSetDTO>> getAllRoleSets() {
		return ResponseEntity.ok().body(roleSetService.findAllRoleSetUser());
	}
	
	@GetMapping("/byEmail")
	public ResponseEntity<List<RoleSetDTO>> getByEmail(@RequestParam String email) {
	    List<RoleSetDTO> roleSets = roleSetService.getRoleSetsByEmail(email);
	    if (roleSets.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(roleSets);
	    }
	}

	

	@PostMapping("")
	public ResponseEntity<String> create(@RequestBody RoleSet user) {
//		RoleSet rs1 = new RoleSet(user.get, user.getPr(), rsu.get().getId());

		return roleSetService.createNewUser(user);

	}
	
	@PutMapping("")
	public ResponseEntity<RoleSet> updateRoleSet(@RequestParam Long id, @RequestBody RoleSet technoDetails){
		RoleSet roleSet = roleSetService.findRoleSetById(id);
		roleSet.setUser(technoDetails.getUser());
		roleSet.setRoleuser(technoDetails.getRoleuser());
		roleSet.setProgramrole(technoDetails.getProgramrole());
		
		final RoleSet updatedRoleSet = roleSetRepository.save(roleSet);
		return ResponseEntity.ok().body(updatedRoleSet);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTechnology(@PathVariable(value = "id") Long id){
		RoleSet roleSet = roleSetService.findRoleSetById(id);
		
		roleSetRepository.delete(roleSet);
		Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}