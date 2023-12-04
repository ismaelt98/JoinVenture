package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.RoleUser;
import com.joinventure.repositories.RoleUserRepository;

@Service
public class RoleUserService {
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	public List<RoleUser> findAllUsers(){
		return roleUserRepository.findAll();
	}

	public ResponseEntity<?> createNewRoleUser(RoleUser roleUser) {
		roleUserRepository.save(roleUser);
		return ResponseEntity.ok().body("Rol de usuario creado correctamente");
	}

	public RoleUser findRoleById(Long id) {
		RoleUser roleUser = roleUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado con id: " + id));
		return roleUser;
	}
}
