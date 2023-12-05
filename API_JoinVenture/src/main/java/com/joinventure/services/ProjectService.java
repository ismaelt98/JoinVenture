package com.joinventure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.ProjectDTO;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.repositories.ProjectRepository;
import com.joinventure.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Project> findAllUsers() {
		return projectRepo.findAll();
	}

	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectRepo.findAll();
		List<ProjectDTO> userDTOs = new ArrayList<>();

		for (Project proj : projects) {
			ProjectDTO userDTO = new ProjectDTO();
			userDTO.setName(proj.getName());
			userDTO.setNumMembers(proj.getNumMembers());
			userDTO.setName_sector(proj.getSector().getName());
			userDTO.setName_demanda(proj.getDemand().getName());
			userDTO.setName_creador(proj.getUser().getUsername());
			userDTO.setEmail_creador(proj.getUser().getEmail());

			List<String> usersNames = proj.getUserList().stream().map(project -> project.getUsername())
					.collect(Collectors.toList());
			userDTO.setUsersName(usersNames);

			userDTOs.add(userDTO);
		}

		return userDTOs;
	}

	public ResponseEntity<Object> createNewProject(Project project) {

		return ResponseEntity.status(HttpStatus.CREATED).body(projectRepo.save(project));
	}

	@Transactional
	public void addUserToProject(Long projectId, Long userId) {
		Optional<User> userToAdd = userRepo.findById(userId);

		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado."));

		List<User> userList = project.getUserList();
		if(userToAdd.isPresent()) {
			userList.add(userToAdd.get());
			project.setUserList(userList);
			projectRepo.save(project);
		}

		
		
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
			// existingProject.setUpdatedAt(updatedProject.getUpdatedAt());
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