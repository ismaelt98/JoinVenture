package com.joinventure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.joinventure.entities.Language;
import com.joinventure.entities.User;
import com.joinventure.entities.DTOs.UserDTO;
import com.joinventure.entities.DTOs.UserLanguagesDTO;

import com.joinventure.repositories.UserRepository;
import com.joinventure.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;	
	
	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok().body(userService.getAllUsers());
	}
	
	@GetMapping("user")
	public ResponseEntity<UserDTO> getUserById(@RequestParam Long id){
		UserDTO user = userService.findUserById(id);
		
		
		return ResponseEntity.ok().body(user);
	}	
	
	
	
	@GetMapping("userLanguages")
	public ResponseEntity<List<Language>> getAllLanguagesByUser(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.getAllLanguagesByUser(id));
	}
	
	
	@GetMapping("userFrameworks")
	public ResponseEntity<List<Framework>> getAllFrameworksByUser(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.getAllFrameworksByUser(id));
	}
	
	@GetMapping("/buscarEmail")
	public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email){
		UserDTO user = userService.findUserByEmail(email);
		
		return ResponseEntity.ok().body(user);
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		
		return userService.createNewUser(user);
	}
	
	
	
	
	
	
//	
//	
//	
//	@GetMapping("/buscar/{username}")
//	public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username){
//		User user = userService.findUserByUsername(username);
//		return ResponseEntity.ok().body(user);
//	}
	
	
	
//	@PutMapping("/updateUser")
//	 public ResponseEntity<Object> updateUser(@RequestParam Long id, @RequestBody User userDetails){
//		return userService.updateUser(id, userDetails);
//	}
	
	
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
		
		user.setPassword(userService.hashSHA256(userDetails.getPassword()));
		user.setUpdatedAt(userDetails.getUpdatedAt());
		
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
//	@DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "id") Long id) {
//        User user = userService.findUserById(id);
//
//        userRepository.delete(user);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("eliminado", Boolean.TRUE);
//        return ResponseEntity.ok().body(response);
//    }
	
	
	 @DeleteMapping("/deleteUser")
	    public ResponseEntity<String> eliminarUsuarioYProyectos(@RequestParam Long userId) {
		 
		 boolean joined =  userService.eliminarUsuarioConProyectos(userId);

	        if (joined) {
	            return ResponseEntity.ok("Usuario y proyectos eliminados correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo unir al proyecto");
	        }
	       
	        
	    }
	
	//Elimina todos los lenguajes que estan relacionados con este usuario
	//Elimina todos los frameworks que estan relacionados con este usuario
	//Elimina el usuario
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
        Optional <User> user = userService.findByEmail(email);

        if(user.isPresent()) {
            User user1 = user.get();
            String hashedPassword = userService.hashSHA256(password);
            return hashedPassword.equals(user1.getPassword());
        }

        return false;
    }
	
	@PostMapping("/password/cifrar")
    public String cifrarPassword(@RequestParam String password) {
        String passwordCifrado = userService.hashSHA256(password);
        return passwordCifrado;
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkIfExistUserEmail(@RequestParam String email) {
        boolean existe = userService.verificarEmailExistente(email);
        
        return ResponseEntity.ok(existe);
    }
    
    @GetMapping("/checkPassword")
    public ResponseEntity<Boolean> checkifExistUserPassword(@RequestParam String password){
    	boolean existe = userService.verificarPasswordExistente(password);
    	return ResponseEntity.ok(existe); 
    }
    
    @GetMapping("/checkPhone")
    public ResponseEntity<Boolean> checkifExistUserPhone(@RequestParam String phone){
    	boolean existe = userService.verificarPhoneExistente(phone);
    	return ResponseEntity.ok(existe); 
    }
}