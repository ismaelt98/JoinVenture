package com.joinventure.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAllUsers(){
		List<User> users = userRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User userElement : users) {
			UserDTO userDTO = modelMapper.map(userElement, UserDTO.class);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}
	
	public User findUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));
		return user;
	}
	
	public ResponseEntity<String> createNewUser(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya existe en la base de datos");
		} else if(userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe en la base de datos");
		} else {
			userRepository.save(user);
			return ResponseEntity.ok().body("Usuario registrado correctamente");
		}
	}
	
	public User findUserByUsername(String username) {
		List<User> users = userRepository.findAll();
//		for (int i=0; i<users.size(); i++) {
//			if(users.get(i).getUsername().equals(username)) {
//				return users.get(i);
//			}
//		}
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con el nombre de usuario: " + username);
	}
}
