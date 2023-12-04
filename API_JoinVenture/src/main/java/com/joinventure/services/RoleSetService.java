package com.joinventure.services;

import java.util.List;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joinventure.entities.RoleSet;


import com.joinventure.entities.DTOs.RoleSetDTO;

import com.joinventure.repositories.RoleSetRepository;


@Service
public class RoleSetService {
	
	@Autowired
	private RoleSetRepository roleSetRepo;

	public List<RoleSetDTO> findAllRoleSetUser() {

		List<RoleSet> users = roleSetRepo.findAll();

		return users.stream().map(this::convertirAMiTablaDTO).collect(Collectors.toList());
	}

	public ResponseEntity<String> createNewUser(RoleSet roleSet) {		
		
		roleSet.setUser(roleSet.getUser());
		roleSet.setProgramrole(roleSet.getProgramrole());
		roleSet.setRoleuser(roleSet.getRoleuser());
		roleSetRepo.save(roleSet);

		return ResponseEntity.ok().body("Usuario registrado correctamente");
	}
	
	public List<RoleSetDTO> getRoleSetsByEmail(String email) {
	   
		List<RoleSet> users = roleSetRepo.findByUser_Email(email);
		
		return users.stream().map(this::convertirAMiTablaDTO).collect(Collectors.toList());
	}

	private RoleSetDTO convertirAMiTablaDTO(RoleSet roleSet) {
		RoleSetDTO technDTO = new RoleSetDTO();	
		
		technDTO.setUser_name(roleSet.getUser().getUsername());
		technDTO.setLast_name(roleSet.getUser().getAlias());
		technDTO.setEmail(roleSet.getUser().getEmail());
		technDTO.setPhone(roleSet.getUser().getPhone());

		technDTO.setName_role_programmer(roleSet.getProgramrole().getName());
		technDTO.setName_role_user(roleSet.getRoleuser().getName());

		return technDTO;
	}
}