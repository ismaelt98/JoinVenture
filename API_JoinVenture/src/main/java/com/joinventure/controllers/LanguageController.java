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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.joinventure.entities.Language;
import com.joinventure.repositories.LanguageRepository;
import com.joinventure.services.LanguageService;

import lombok.extern.java.Log;


@Log
@RestController
@RequestMapping("/languages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LanguageController {
    
    @Autowired
	private LanguageRepository roleUserRepository;
	
	@Autowired
	private LanguageService roleUserService;
	
	@GetMapping("")
	public ResponseEntity<List<Language>> getAllLanguages(){
		return ResponseEntity.ok().body(roleUserService.findAllLanguages());
	}
	@GetMapping("/language")
	public ResponseEntity<Optional <Language>> getProgrammerRole(@RequestParam String name){
		Optional <Language> language = roleUserRepository.findByName(name);
		return ResponseEntity.ok().body(language); 
	}
	
	@PostMapping("")
	public ResponseEntity<?> createLanguage(@RequestBody Language language){
		return roleUserService.createNewLanguage(language);
	}

	@PutMapping("")
	public ResponseEntity<Language> updateLanguage(@RequestParam Long id, @RequestBody Language languageDetails){
		Language language = roleUserService.findLanguageById(id);
		language.setName(languageDetails.getName());
		
		final Language updatedLanguage = roleUserRepository.save(language);
		return ResponseEntity.ok().body(updatedLanguage);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLanguage(@PathVariable(value = "id") Long id) {
		Language language = roleUserService.findLanguageById(id);

        roleUserRepository.delete(language);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
	}
}
