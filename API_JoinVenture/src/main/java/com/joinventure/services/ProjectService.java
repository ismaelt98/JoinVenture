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
import com.joinventure.entities.DTOs.ProjectsUserDTO;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.entities.DTOs.UserProjectDTO;
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

	public ProjectDTO getProjectById(Long id) {
		Optional<Project> project = projectRepo.findById(id);
		ProjectDTO proDTO = new ProjectDTO();
		
		proDTO.setId(project.get().getId());
		proDTO.setName(project.get().getName());
		proDTO.setNumMembers(project.get().getNumMembers());
		
		proDTO.setName_sector(project.get().getSector().getName());
		proDTO.setName_demanda(project.get().getDemand().getName());
		proDTO.setName_creador(project.get().getUser().getUsername());
		proDTO.setEmail_creador(project.get().getUser().getEmail());

		List<UserProjectDTO> usersNames = project.get().getUserList().stream().map(user -> new UserProjectDTO(user.getId(),
				user.getUsername(), user.getAlias(), user.getEmail(), user.getPhone())).collect(Collectors.toList());

		proDTO.setUsersName(usersNames);
		
		return proDTO;
	}

	public List<UserProjectDTO> getUserListByProjectId(Long projectId) {
		Optional<Project> optionalProject = projectRepo.findById(projectId);
		List<UserProjectDTO> projectDTO = new ArrayList<>();
		if (optionalProject.isPresent()) {
			List<User> users = optionalProject.get().getUserList();
			for (User us : users) {
				UserProjectDTO userDTO = new UserProjectDTO();
				userDTO.setId(us.getId());
				userDTO.setUsername(us.getUsername());
				userDTO.setAlias(us.getAlias());
				userDTO.setEmail(us.getEmail());
				userDTO.setPhone(us.getPhone());
				;

				projectDTO.add(userDTO);
			}

			return projectDTO;
		}
		return null;
	}

	public List<ProjectsUserDTO> getAllProjectsByUser(Long id) {

		Optional<User> usuario = userRepo.findById(id);
		List<ProjectsUserDTO> userDTOs = new ArrayList<>();

		if (usuario.isPresent()) {
			User user = usuario.get();
			List<Project> projects = user.getProjectList();

			for (Project proj : projects) {
				ProjectsUserDTO userDTO = new ProjectsUserDTO();
				userDTO.setId(proj.getId());
				userDTO.setName(proj.getName());
				userDTO.setNumMembers(proj.getNumMembers());
				userDTO.setName_sector(proj.getSector().getName());
				userDTO.setName_demanda(proj.getDemand().getName());
				userDTO.setName_creador(proj.getUser().getUsername());
				userDTO.setEmail_creador(proj.getUser().getEmail());

				userDTOs.add(userDTO);
			}
		}

		return userDTOs;

	}

	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectRepo.findAll();
		List<ProjectDTO> projectDTOs = new ArrayList<>();

		for (Project proj : projects) {
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setId(proj.getId());
			projectDTO.setName(proj.getName());
			projectDTO.setNumMembers(proj.getNumMembers());
			projectDTO.setName_sector(proj.getSector().getName());
			projectDTO.setName_demanda(proj.getDemand().getName());
			projectDTO.setName_creador(proj.getUser().getUsername());
			projectDTO.setEmail_creador(proj.getUser().getEmail());

			List<UserProjectDTO> usersNames = proj.getUserList().stream().map(user -> new UserProjectDTO(user.getId(),
					user.getUsername(), user.getAlias(), user.getEmail(), user.getPhone()))
					.collect(Collectors.toList());

			projectDTO.setUsersName(usersNames);
			projectDTOs.add(projectDTO);
		}

		return projectDTOs;
	}

	public ResponseEntity<Object> createNewProject(Project project) {

		return ResponseEntity.status(HttpStatus.CREATED).body(projectRepo.save(project));
	}

	public boolean getUserProject(String nameProject, Long idUser) {
		Optional<Project> pro = projectRepo.findByName(nameProject);
		if (pro != null) {
			List<User> users = pro.get().getUserList();
			for (User usuario : users) {
				if (usuario.getId().equals(idUser)) {
					return true;
				}
			}
		}

		return false;
	}

	@Transactional
	public void addUserToProject(Long projectId, Long userId) {
		Optional<User> userToAdd = userRepo.findById(userId);

		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado."));

		List<User> userList = project.getUserList();

		if (userToAdd.isPresent()) {

			if (!project.getUserList().contains(userToAdd)) {
				userList.add(userToAdd.get());
				project.setUserList(userList);
				projectRepo.save(project);
			}

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