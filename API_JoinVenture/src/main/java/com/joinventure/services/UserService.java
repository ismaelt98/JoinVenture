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

import com.joinventure.entities.Framework;
import com.joinventure.entities.Language;
import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.entities.DTOs.UserProjectsDTO;
import com.joinventure.repositories.ProjectRepository;
import com.joinventure.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository userRepository1;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public List<Language> getAllLanguagesByUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));

		List<Language> languages = new ArrayList<>();
		
		for (Language user1 : user.getListLanguage()) {
			Language lang = new Language();
			lang.setId(user1.getId());
			lang.setName(user1.getName());
		

			languages.add(lang);
		}
		return languages;
	}
	
	public List<Framework> getAllFrameworksByUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));

		List<Framework> frameworks = new ArrayList<>();
		
		for (Framework framework : user.getListFrameworks()) {
			
			Framework fram = new Framework();
			fram.setId(framework.getId());
			fram.setName(framework.getName());
		

			frameworks.add(fram);
		}
		return frameworks;
	}

	public List<UserProjectsDTO> getAllProjectsByUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));

		List<UserProjectsDTO> userDTOs = new ArrayList<>();

		for (Project user1 : user.getProjectList()) {
			UserProjectsDTO userDTO = new UserProjectsDTO();
			userDTO.setId(user1.getId());
			userDTO.setName(user1.getName());
			userDTO.setMembers(user1.getNumMembers());

			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();

		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName_role(user.getRoleuser().getName()); 
			userDTO.setUsername(user.getUsername());
			userDTO.setAlias(user.getAlias());
			userDTO.setEmail(user.getEmail());

			List<String> projectNames = user.getProjectList().stream().map(project -> project.getName())
					.collect(Collectors.toList());
			userDTO.setProjectNames(projectNames);

			userDTOs.add(userDTO);
		}

		return userDTOs;
	}

	public UserDTO findUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName_role(user.getRoleuser().getName()); // Suponiendo que 'nombre' es un campo en RoleUser
		userDTO.setUsername(user.getUsername());
		userDTO.setAlias(user.getAlias());
		userDTO.setEmail(user.getEmail());

		List<String> projectNames = user.getProjectList().stream().map(project -> project.getName())
				.collect(Collectors.toList());
		userDTO.setProjectNames(projectNames);
		return userDTO;
	}

	public ResponseEntity<Object> createNewUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya existe en la base de datos");
		} else {
			user.setPassword(hashSHA256(user.getPassword()));

			return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
		}
	}
	


    public ResponseEntity<Object> updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + id));
        
        System.out.println(user);
        user.setUsername(userDetails.getUsername());
        user.setPassword(hashSHA256(userDetails.getPassword()));
        user.setEmail(user.getEmail());
        user.setAlias(userDetails.getAlias());  
        user.setPhone(user.getPhone());
        user.setProjectList(user.getProjectList());
        user.setListLanguage(user.getListLanguage());
        user.setListFrameworks(user.getListFrameworks());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok().body(updatedUser);
    }
    
    
    public void eliminarUsuarioConProyectos(Long userId) {
        
        User user = userRepository.findById(userId).orElse(null);
        
        if (user != null) {
            
            List<Project> proyectos = user.getProjectList();
            
            
            if (proyectos != null) {
                for (Project proyecto : proyectos) {
                    
                	userRepository1.delete(proyecto);
                }
            
            }
            
        }
            
            userRepository.delete(user);
        }
        

	public UserDTO findUserByEmail(String email) {
		List<User> users = userRepository.findAll();

		for (User user : users) {
			if (user.getEmail().equals(email)) {
				UserDTO userDTO = new UserDTO();
				userDTO.setId(user.getId());
				userDTO.setName_role(user.getRoleuser().getName());
				userDTO.setUsername(user.getUsername());
				userDTO.setAlias(user.getAlias());
				userDTO.setEmail(user.getEmail());

				List<String> projectNames = user.getProjectList().stream().map(project -> project.getName())
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
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Usuario no encontrado con el nombre de usuario: " + username);
	}

	public Optional<User> getPasswordByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> findByEmail(String email) {
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
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
