package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.Lenguage;

import com.joinventure.repositories.LenguageRepository;


@Service
public class LenguageService {
	@Autowired
	private LenguageRepository languageRepository;
	
	public List<Lenguage> findAllLanguages(){
		return languageRepository.findAll();
	}

	public ResponseEntity<?> createNewLanguage(Lenguage roleUser) {
		languageRepository.save(roleUser);
		return ResponseEntity.ok().body("Lenguage creado correctamente");
	}

	public Lenguage findLanguageById(Long id) {
		Lenguage language = languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lenguage no encontrado con id: " + id));
		return language;
	}
}