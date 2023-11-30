package com.joinventure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Project;
import com.joinventure.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;

	public List<Project> findAllUsers() {
		return projectRepo.findAll();
	}

	public ResponseEntity<String> createNewProject(Project project) {

		projectRepo.save(project);
		return ResponseEntity.ok().body("Proyecto registrado correctamente");

	}

	public boolean deleteUserById(Long id) {

		Optional<Project> userOptional = projectRepo.findById(id);

		if (userOptional.isPresent()) {
			projectRepo.delete(userOptional.get());
			return true;
		} else {
			return false;
		}
	}

	public boolean updateProjectById(Long id, Project updatedProject) {
		Optional<Project> projectOptional = projectRepo.findById(id);

		if (projectOptional.isPresent()) {
			Project existingProject = projectOptional.get();

			existingProject.setName(updatedProject.getName());
			existingProject.setNumMembers(updatedProject.getNumMembers());
			//existingProject.setUpdatedAt(updatedProject.getUpdatedAt());
			projectRepo.save(existingProject);
			return true;
		} else {
			return false;
		}
	}

	public Optional<Project> findProjectById(long id) {
		return projectRepo.findById(id);
	}

}
