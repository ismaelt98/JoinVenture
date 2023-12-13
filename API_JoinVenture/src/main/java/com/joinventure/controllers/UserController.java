package com.joinventure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
		Optional<User> user = userService.findUserById(id);
		return user.isPresent()? ResponseEntity.ok(user): ResponseEntity.noContent().build();
	}
	
	@GetMapping("/checkEmail")
	public ResponseEntity<String> checkIfExistEmail(@RequestParam String email) {
		Optional<User> existEmail = userService.findIfExistEmail(email);
		return existEmail.isPresent()? ResponseEntity.status(HttpStatus.IM_USED).body(existEmail.get().getEmail() + " ya está regitrado"): ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long id){
		/* Para mirar más tarde */ 
		boolean userIsDeleted = userService.deleteUser(id);
		return userIsDeleted?ResponseEntity.ok("User was deleted!"): ResponseEntity.notFound().build();
	}
	
	@PutMapping
	public ResponseEntity<?> addUser(@RequestBody User user){
		User userSaved = userService.saveUser(user);
		if(userSaved != null){
			return ResponseEntity.ok(userSaved);
		}
		return ResponseEntity.status(HttpStatus.IM_USED).body("No se ha podido crear el user, prueba con otro email o haz login");
	}
	
	@PatchMapping("/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody User user){
		user.setId(id);
		userService.updateUser(user);
	}

	@PostMapping("/login")
    public ResponseEntity<Object> getLogin(@RequestParam String email, @RequestParam String password) {
		Optional<User> optionalUser = userService.getLoginUser(email, password);
		return optionalUser.isPresent()?ResponseEntity.ok(optionalUser.get()):ResponseEntity.noContent().build();
    }
	
	@GetMapping("/{userId}/projects")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable Long userId) {
        try {
            List<Project> projects = userService.getUserProjects(userId);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}