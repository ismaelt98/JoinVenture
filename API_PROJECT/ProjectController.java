package com.joinventure.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joinventure.entities.Project;
import com.joinventure.services.ProjectService;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

	@Autowired
	private ProjectService proService;

	// METODOS GET

	@GetMapping("")
	public ResponseEntity<List<Project>> getAllUsers() {
		return ResponseEntity.ok().body(proService.findAllUsers());
	}

	@GetMapping("/project")
	public ResponseEntity<?> getProjectById(@RequestParam Long id) {
		Optional<Project> project = proService.findProjectById(id);
		return project.isPresent() ? ResponseEntity.ok(project)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
	}

	// METODOS POST Y PUT

	@PutMapping("")
	public ResponseEntity<String> createProject(@RequestBody Project project) {

		return proService.createNewProject(project);
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
