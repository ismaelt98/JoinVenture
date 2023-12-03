package com.joinventure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.Integration;
import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.IntegrationDTO;
import com.joinventure.entities.DTOs.IntegrationRequest;
import com.joinventure.services.IntegrationService;
import com.joinventure.services.ProjectService;
import com.joinventure.services.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/integration")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IntegrationController {
	
	@Autowired
	private IntegrationService inteService;
	
	@Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<IntegrationDTO>> getAllIntegrations(){
		return ResponseEntity.ok().body(inteService.finAllIntegration());
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createIntegration(@RequestBody IntegrationRequest integrationDTO) {
		System.out.println("IntegrationRequest received: " + integrationDTO.toString());
	    Long projectId = integrationDTO.getProjectId();
	    System.out.println("projectId: " + projectId); 
	    User user = userService.findUserById(integrationDTO.getUserId());

	    if (projectId != null) {
	        Optional<Project> optionalProject = projectService.findProjectById(projectId);

	        if (optionalProject.isPresent()) {
	            Project project = optionalProject.get();

	            Integration integration = new Integration(project, user, integrationDTO.getIntegratedAt());
	            Integration createdIntegration = inteService.createIntegration(integration);

	            return ResponseEntity.status(HttpStatus.CREATED)
	                .body("Integration created with ID: " + createdIntegration.getId());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Project not found for the given ID.");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body("ProjectId must not be null.");
	    }
	}
}
