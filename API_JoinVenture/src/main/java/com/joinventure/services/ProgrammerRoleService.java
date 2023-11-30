package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.ProgrammerRole;
import com.joinventure.repositories.ProgrammerRoleRepository;

@Service
public class ProgrammerRoleService {
	@Autowired
	private ProgrammerRoleRepository programmerRoleRepository;
	
	public List<ProgrammerRole> findAllRoles(){
		return programmerRoleRepository.findAll();
	}
	
	public ResponseEntity<String> createNewRoleUser(ProgrammerRole role) {
		programmerRoleRepository.save(role);
		return ResponseEntity.ok().body("Rol de usuario creado correctamente");
	}

	public ProgrammerRole findRoleById(Long id) {
		ProgrammerRole role = programmerRoleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado con id: " + id));
		return role;
	}
}
