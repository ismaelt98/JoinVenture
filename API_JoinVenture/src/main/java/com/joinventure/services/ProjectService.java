package com.joinventure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Project;
import com.joinventure.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
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
}
