package com.joinventure.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	private static final String UPLOAD_DIR = "uploads"; // Directorio donde se almacenarán las imágenes

	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			// Verifica si el directorio de carga existe, si no, créalo
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// Copia el archivo al directorio de carga
			Path filePath = Path.of(UPLOAD_DIR, file.getOriginalFilename());
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			// Devuelve la URL del archivo cargado
			String fileUrl = "/api/images/" + file.getOriginalFilename();
			return new ResponseEntity<>(fileUrl, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>("Error al cargar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{filename}")
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename) {
		try {
			Path filePath = Path.of(UPLOAD_DIR, filename);
			byte[] fileContent = Files.readAllBytes(filePath);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
