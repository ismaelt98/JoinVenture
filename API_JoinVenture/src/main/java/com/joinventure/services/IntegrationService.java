package com.joinventure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.Integration;
import com.joinventure.entities.DTOs.IntegrationDTO;
import com.joinventure.repositories.IntegrationRepository;

@Service
public class IntegrationService {
	@Autowired
	private IntegrationRepository integrationRepo;
	
	public List<IntegrationDTO> finAllIntegration() {
		List<Integration> integrations = integrationRepo.findAll();
		return integrations.stream().map(this::convertirAMiTablaDTO).collect(Collectors.toList());
	}
	
	private IntegrationDTO convertirAMiTablaDTO(Integration integration) {
		IntegrationDTO inteDTO = new IntegrationDTO();
		inteDTO.setUser_name(integration.getUser_user_id().getUsername());
		inteDTO.setAlias(integration.getUser_user_id().getAlias());
		inteDTO.setEmail(integration.getUser_user_id().getEmail());
		inteDTO.setPhone(integration.getUser_user_id().getPhone());
		inteDTO.setName_project(integration.getProyect_proyect_id().getName());
		
		return inteDTO;
	}
	
	public Integration createIntegration(Integration integration) {
        return integrationRepo.save(integration);
    }
	
	public Integration findInteById(Long id) {
		Integration inte = integrationRepo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado con id: " + id));
		return inte;
	}
}
