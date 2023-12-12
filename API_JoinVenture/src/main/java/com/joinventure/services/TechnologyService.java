package com.joinventure.services;


import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.Technology;

import com.joinventure.entities.DTOs.TechnologyDTO;

import com.joinventure.repositories.TechnologyRepository;


@Service
public class TechnologyService {

	@Autowired
	private TechnologyRepository technoRepo;

	public List<TechnologyDTO> findAllTechnologiesDTO() {
		List<Technology> users = technoRepo.findAll();

		return users.stream().map(this::convertirAMiTablaDTO).collect(Collectors.toList());
	}

	public TechnologyDTO getTechnologyById(Long id) {
		Technology techn = technoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID no encontrado"));

		return convertirAMiTablaDTO(techn);
	}

	private TechnologyDTO convertirAMiTablaDTO(Technology techn) {
		TechnologyDTO technDTO = new TechnologyDTO();
		technDTO.setUser_name(techn.getUser_user_id().getUsername());
		technDTO.setLast_name(techn.getUser_user_id().getAlias());
		technDTO.setEmail(techn.getUser_user_id().getEmail());
		technDTO.setName_framework(techn.getFrameworks_frameworks_id().getName());
		technDTO.setName_language(techn.getLenguage_lenguage_id().getName());

		return technDTO;
	}
	
	public ResponseEntity<String> createNewTechnology(Technology technology) {
		technoRepo.save(technology);
		return ResponseEntity.ok().body("Tecnologia registrado correctamente");
	}
	
	public Technology findTechById(Long id) {
		Technology techno = technoRepo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnologia no encontrado con id: " + id));
		return techno;
	}
}