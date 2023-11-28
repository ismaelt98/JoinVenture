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

import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.repositories.UserRepository;
import com.joinventure.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok().body(userService.findAllUsersDTO());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
		User user = userService.findUserById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/buscar/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username){
		User user = userService.findUserByUsername(username);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("")
	public ResponseEntity<String> createUser(@RequestBody User user){
		
		return userService.createNewUser(user);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User userDetails){
		User user = userService.findUserById(id);
		
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setLastname(userDetails.getLastname());
		user.setEmail(userDetails.getEmail());
		user.setCreatedAt(userDetails.getCreatedAt());
		user.setUpdatedAt(userDetails.getUpdatedAt());
		
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
	@PutMapping("/changeusername/{username}")
	public ResponseEntity<User> updateUsername(@PathVariable(value = "username") String username, @RequestBody User userDetails){
		User user = userService.findUserByUsername(username);
		
		user.setUsername(userDetails.getUsername());
		
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
	@PutMapping("/changepassword/{username}")
	public ResponseEntity<?> updatePassword(@PathVariable(value = "username") String username, @RequestBody User userDetails){
		User user = userService.findUserByUsername(username);
		
		if (userDetails.getPassword().equals(user.getPassword())) {
	        return ResponseEntity.badRequest().body("La nueva contraseña es igual a la contraseña antigua. Por favor, elige una nueva contraseña.");
	    }
		
		user.setPassword(userDetails.getPassword());
		user.setUpdatedAt(userDetails.getUpdatedAt());
		
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userService.findUserById(id);

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
	
	@DeleteMapping("/delete/{username}")
    public ResponseEntity<Map<String, Boolean>> deleteUserByUsername(@PathVariable(value = "username") String username) {
        User user = userService.findUserByUsername(username);

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
	
	@PostMapping("/login")
    public boolean getLogin(@RequestParam String email, @RequestParam String password) {
        Optional<User> users = userService.findAllUsers().stream().filter(
                streamedUser -> streamedUser.getEmail().equals(email) && streamedUser.getPassword().equals(password))
                .findFirst();
        System.out.println(users.isPresent());

        return users.isPresent();
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkIfExistUserEmail(@RequestParam String email) {
        boolean existe = userService.verificarEmailExistente(email);
        
        return ResponseEntity.ok(existe);
    }
}