package com.joinventure.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.ProjectDTO;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.repositories.ProjectRepository;
import com.joinventure.repositories.UserRepository;
import com.joinventure.services.ProjectService;
import com.joinventure.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

	@Autowired
	private ProjectService proService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProjectRepository proRepo;

	// METODOS GET

	@GetMapping("")
	public ResponseEntity<List<ProjectDTO>> getAllProjects() {
		return ResponseEntity.ok().body(proService.getAllProjects());
	}

	@GetMapping("/projectsUser")
	public ResponseEntity<List<ProjectDTO>> getAllProjectsByUser(@RequestParam Long id) {
		return ResponseEntity.ok().body(proService.getAllProjectsByUser(id));
	}
	
	@GetMapping("/{idProyecto}/participantes")
    public ResponseEntity<ProjectDTO> getUsersByProjectId(@PathVariable Long idProyecto) {
        ProjectDTO userList = proService.getUserListByProjectId(idProyecto);
        if (userList != null) {
            return ResponseEntity.ok(userList);
        }
        return ResponseEntity.notFound().build();
    }

	@GetMapping("/projectsCreator")
	public ResponseEntity<List<ProjectDTO>> getProjectsByCreator(@RequestParam Long id) {
		Optional<User> user = userRepo.findById(id);

		List<Project> projects = proRepo.findAll();
		List<ProjectDTO> projectsDTO = new ArrayList<>();

		for (Project proj : projects) {
			ProjectDTO projDTO = new ProjectDTO();
			if (proj.getUser().getId() == user.get().getId()) {
				projDTO.setName(proj.getName());
				projDTO.setNumMembers(proj.getNumMembers());
				projDTO.setName_sector(proj.getSector().getName());
				projDTO.setName_demanda(proj.getDemand().getName());
				projDTO.setName_creador(proj.getUser().getUsername());
				projDTO.setEmail_creador(proj.getUser().getEmail());

				projectsDTO.add(projDTO);
			}

		}
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> createProject(@RequestBody Project project) {

		return proService.createNewProject(project);
	}

	@PostMapping("/userProject")
	public boolean getLogin(@RequestParam String nameProject, @RequestParam Long idUser) {
		
		return proService.getUserProject(nameProject, idUser); 
		
		
	}

	@PutMapping("/adduserproject")
	public ResponseEntity<String> addUserToProject(@RequestParam Long projectId, @RequestParam Long userId) {
		try {
			proService.addUserToProject(projectId, userId);
			return ResponseEntity.ok("Usuario agregado al proyecto correctamente.");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya está en el proyecto.");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al agregar usuario al proyecto.");
		}
	}

	@PutMapping("/project")
	public ResponseEntity<?> updateProjectById(@RequestParam Long id, @RequestBody Project updatedProject) {
		boolean updated = proService.updateProjectById(id, updatedProject);

		if (updated) {
			return ResponseEntity.ok("Proyecto actualizado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró ningún proyecto con el ID proporcionado");
		}
	}

	// METODO DELETE

	@DeleteMapping("/project")
	public ResponseEntity<?> deleteProjectById(@RequestParam Long id) {

		boolean deleted = proService.deleteUserById(id);

		if (deleted) {
			return ResponseEntity.ok("Proyecto eliminado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró ningún proyecto con el ID proporcionado");
		}
	}
}