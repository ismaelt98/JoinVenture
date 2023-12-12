package com.joinventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.Language;

import com.joinventure.repositories.LanguageRepository;


@Service
public class LanguageService {
	@Autowired
	private LanguageRepository languageRepository;
	
	public List<Language> findAllLanguages(){
		return languageRepository.findAll();
	}

	public ResponseEntity<?> createNewLanguage(Language roleUser) {
		languageRepository.save(roleUser);
		return ResponseEntity.ok().body("Lenguage creado correctamente");
	}

	public Language findLanguageById(Long id) {
		Language language = languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lenguage no encontrado con id: " + id));
		return language;
	}
}