package com.joinventure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.joinventure.entities.Framework;
import com.joinventure.repositories.FrameworkRepository;
import com.joinventure.services.FrameworkService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/frameworks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FrameworkController {

	@Autowired
	private FrameworkRepository frameworkRepository;
	
	@Autowired
	private FrameworkService frameworkService;
	
	@GetMapping("")
	public ResponseEntity<List<Framework>> getAllFrameworks(){
		return ResponseEntity.ok().body(frameworkService.findAllFrameworks());
	}
	@GetMapping("/framework")
	public ResponseEntity<Optional<Framework>> getFramework(@RequestParam String name){
		Optional<Framework> framework = frameworkRepository.findByName(name);
		return ResponseEntity.ok().body(framework);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createFramework(@RequestBody Framework framework){
		return frameworkService.createNewFramework(framework);
	}
	
	@PutMapping("")
	public ResponseEntity<Framework> updateFramework(@RequestParam Long id, @RequestBody Framework frameworkDetails){
		Framework framework = frameworkService.findFrameworkById(id);
		framework.setName(frameworkDetails.getName());
		
		final Framework updatedFramework  = frameworkRepository.save(framework);
		return ResponseEntity.ok().body(updatedFramework);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLanguage(@PathVariable(value = "id") Long id) {
		Framework framework = frameworkService.findFrameworkById(id);

        frameworkRepository.delete(framework);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}
