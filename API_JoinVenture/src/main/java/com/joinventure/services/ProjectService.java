package com.joinventure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.repositories.ProjectRepository;
import com.joinventure.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	public Optional<Project> findProjectById(Long id) {
		return projectRepository.findById(id);
	}

	public boolean deleteProject(Long id) {
		if (projectRepository.existsById(id)) {
			projectRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Project saveProject(Project project) {
//		try {
//			Optional<Project> existsProject = projectRepository.findAll().stream().filter(p -> p.)
//		}
		if (project.getUserCreator() != null && !project.getUsersList().contains(project.getUserCreator())) {
            project.getUsersList().add(project.getUserCreator());
        }
		projectRepository.save(project);
		return project;
	}

	public void updateProject(Project project) {
		project.setName(project.getName());
		project.setNummembers(project.getNummembers());
		project.setSector(project.getSector());
		project.setDemand(project.getDemand());
		project.setUserCreator(project.getUserCreator());
		projectRepository.save(project);
	}

	public Project addMemberToProject(Long projectId, Long userId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

		if (project.getNummembers() < project.getUsersList().size() + 1) {
			throw new RuntimeException("Project has reached the maximum number of members.");
		}

		if (!project.getUsersList().contains(user)) {
			project.getUsersList().add(user);
		}

		return projectRepository.save(project);
	}
}
