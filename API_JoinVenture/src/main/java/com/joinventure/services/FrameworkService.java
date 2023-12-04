package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.Framework;
import com.joinventure.repositories.FrameworkRepository;

@Service
public class FrameworkService {
	@Autowired
	private FrameworkRepository frameworkRepository;
	
	public List<Framework> findAllFrameworks(){
		return frameworkRepository.findAll();
	}
	
	public ResponseEntity<?> createNewFramework(Framework framework){
		frameworkRepository.save(framework);
		return ResponseEntity.ok().body("Framework creado correctamente");
	}
	
	public Framework findFrameworkById(Long id) {
		Framework framework = frameworkRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Framework no encontrado con id: " +id));
		return framework;
	}
}
