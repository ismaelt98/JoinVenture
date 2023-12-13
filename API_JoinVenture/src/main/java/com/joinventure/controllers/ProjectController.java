package com.joinventure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.Project;
import com.joinventure.services.ProjectService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public ResponseEntity<?> getAllProjects() {
		List<Project> projects = projectService.findAllProjects();
		return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(projects);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable(value = "id") Long id) {
		Optional<Project> project = projectService.findProjectById(id);
		return project.isPresent() ? ResponseEntity.ok(project) : ResponseEntity.noContent().build();
	}

	@GetMapping("/bySector/{sector}")
	public ResponseEntity<?> getProjectsBySector(@PathVariable(value = "sector") String sector) {
		List<Project> projects = projectService.findProjectsBySector(sector);
		if (!projects.isEmpty()) {
			return ResponseEntity.ok(projects);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProjectById(@PathVariable(value = "id") Long id) {
		boolean projectIsDeleted = projectService.deleteProject(id);
		return projectIsDeleted ? ResponseEntity.ok("Project was deleted!") : ResponseEntity.notFound().build();
	}

	@PutMapping
	public ResponseEntity<?> addProject(@RequestBody Project project) {
		Project projectSaved = projectService.saveProject(project);
		if (projectSaved != null) {
			return ResponseEntity.ok(projectSaved);
		}
		return ResponseEntity.status(HttpStatus.IM_USED).body("No se ha podido crear el project");
	}

	@PatchMapping("/{id}")
	public void updateProject(@PathVariable Long id, @RequestBody Project project) {
		project.setId(id);
		projectService.updateProject(project);
	}

	@PostMapping("/{projectId}/addMember/{userId}")
	public ResponseEntity<Project> addMemberToProject(@PathVariable Long projectId, @PathVariable Long userId) {
		try {
			Project updatedProject = projectService.addMemberToProject(projectId, userId);
			return new ResponseEntity<>(updatedProject, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{projectId}/removeMember/{userId}")
	public ResponseEntity<Project> deleteMemberFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
		try {
			Project updatedProject = projectService.deleteMemberFromProject(projectId, userId);
			return new ResponseEntity<>(updatedProject, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
