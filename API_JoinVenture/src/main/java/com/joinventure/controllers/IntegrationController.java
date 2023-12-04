package com.joinventure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.joinventure.entities.Integration;
import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.IntegrationDTO;
import com.joinventure.entities.DTOs.IntegrationRequest;
import com.joinventure.repositories.IntegrationRepository;
import com.joinventure.services.IntegrationService;
import com.joinventure.services.ProjectService;
import com.joinventure.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/integration")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IntegrationController {
	
	@Autowired
	private IntegrationService inteService;
	@Autowired
	private IntegrationRepository inteRepo;
	
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
	
	@PutMapping("")
	public ResponseEntity<Integration> updateRole(@RequestParam Long id, @RequestBody Integration integrationDetails){
		Integration integration = inteService.findInteById(id);
		integration.setProyect_proyect_id(integrationDetails.getProyect_proyect_id());
		integration.setUser_user_id(integrationDetails.getUser_user_id());

		
		final Integration updatedInte = inteRepo.save(integration);
		return ResponseEntity.ok().body(updatedInte);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRole(@PathVariable(value = "id") Long id) {
		Integration integration = inteService.findInteById(id);

		inteRepo.delete(integration);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}
