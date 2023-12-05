package com.joinventure.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
	

	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId()); 
            userDTO.setName_role(user.getRoleuser().getName()); // Suponiendo que 'nombre' es un campo en RoleUser
            userDTO.setUsername(user.getUsername());
            userDTO.setAlias(user.getAlias());
            userDTO.setEmail(user.getEmail());

            List<String> projectNames = user.getProjectList().stream()
                    .map(project -> project.getName()) 
                    .collect(Collectors.toList());
            userDTO.setProjectNames(projectNames);

            userDTOs.add(userDTO);
        }

        return userDTOs;
    }
	

	
	public User findUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));
		return user;
	}
	
	public ResponseEntity<Object> createNewUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya existe en la base de datos");
        } else {
            user.setPassword(hashSHA256(user.getPassword()));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        }
    }
	
	public UserDTO findUserByEmail(String email) {
		List<User> users = userRepository.findAll();
		
		for (User user : users) {
			if(user.getEmail().equals(email)) {
				 UserDTO userDTO = new UserDTO();
				 userDTO.setId(user.getId()); 
		            userDTO.setName_role(user.getRoleuser().getName()); 
		            userDTO.setUsername(user.getUsername());
		            userDTO.setAlias(user.getAlias());
		            userDTO.setEmail(user.getEmail());

		            List<String> projectNames = user.getProjectList().stream()
		                    .map(project -> project.getName()) 
		                    .collect(Collectors.toList());
		            userDTO.setProjectNames(projectNames);

				return userDTO;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con el email: " + email);
	}
	
	public User findUserByUsername(String username) {
		List<User> users = userRepository.findAll();

		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con el nombre de usuario: " + username);
	}
	
	public Optional<User> getPasswordByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
	public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean verificarEmailExistente(String email) {
        return userRepository.existsByEmail(email);
    }
//    public Optional<User> findUserByEmail(String email) {
//        Optional<User> user = userRepository.findAll().stream()
//                .filter(user1 -> user1.getEmail().equals(email)).findFirst();
//
//        return user;
//    }
    public String hashSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
