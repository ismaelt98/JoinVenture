package com.joinventure.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.ProgrammerRole;
import com.joinventure.entities.RoleSet;
import com.joinventure.entities.RoleUser;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.RoleSetDTO;
import com.joinventure.repositories.ProgrammerRoleRepository;
import com.joinventure.repositories.RoleSetRepository;
import com.joinventure.repositories.RoleUserRepository;
import com.joinventure.repositories.UserRepository;

@Service
public class RoleSetService {
	
	@Autowired
	private RoleSetRepository roleSetRepo;
	
	@Autowired
	private RoleUserRepository roleUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProgrammerRoleRepository proRepo;

	public List<RoleSetDTO> findAllRoleSetUser() {

		List<RoleSet> users = roleSetRepo.findAll();

		return users.stream().map(this::convertirAMiTablaDTO).collect(Collectors.toList());
	}

	public ResponseEntity<String> createNewUser(RoleSet roleSet) {
		
		
		roleSet.setUser(roleSet.getUser());
		roleSet.setProgramrole(roleSet.getProgramrole());
		roleSet.setRoleuser(roleSet.getRoleuser());
		roleSetRepo.save(roleSet);

		return ResponseEntity.ok().body("RolSet registrado correctamente");
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
	
	public RoleSet findRoleSetById(Long id) {
		RoleSet roleSet = roleSetRepo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoleSet no encontrado con id: " + id));
		return roleSet;
	}
}