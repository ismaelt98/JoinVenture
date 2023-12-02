package com.joinventure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private RoleSetDTO convertirAMiTablaDTO(RoleSet roleSet) {
		RoleSetDTO technDTO = new RoleSetDTO();
		technDTO.setUser_name(roleSet.getUser_user_id().getUsername());
		technDTO.setLast_name(roleSet.getUser_user_id().getLastname());
		technDTO.setEmail(roleSet.getUser_user_id().getEmail());
		technDTO.setPhone(roleSet.getUser_user_id().getPhone());
		
		technDTO.setName_role_programmer(roleSet.getProgrammer_roleId().getName());
		technDTO.setName_role_user(roleSet.getRoleUser_id().getName());

		return technDTO;
	}
}