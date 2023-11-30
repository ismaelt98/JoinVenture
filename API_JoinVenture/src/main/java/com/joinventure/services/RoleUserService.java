package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joinventure.entities.RoleUser;
import com.joinventure.repositories.RoleUserRepository;

@Service
public class RoleUserService {
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	public List<RoleUser> findAllUsers(){
		return roleUserRepository.findAll();
	}

	public ResponseEntity<String> createNewUser(RoleUser roleUser) {
		roleUserRepository.save(roleUser);
		return ResponseEntity.ok().body("Rol de usuario creado correctamente");
	}
}
