package com.joinventure.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Project;
import com.joinventure.entities.User;
import com.joinventure.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findIfExistEmail(String email) {
		return userRepository.findAll().stream().filter(e -> e.getEmail().equals(email)).findFirst();
	}

	public boolean deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public User saveUser(User user) {
		try {
			Optional<User> existsUser = userRepository.findAll().stream()
					.filter(u -> u.getEmail().equals(user.getEmail())).findFirst();
			if (existsUser.isPresent()) {
				return null;
			} else {
				user.setPassword(hashSHA256(user.getPassword()));
				userRepository.save(user);
				return user;
			}
		} catch (Exception e) {
			return null;
		}
	}

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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateUser(User user) {
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user.getId()));
		existingUser.setUsername(user.getUsername());
		existingUser.setAlias(user.getAlias());

		if (!existingUser.getPassword().equals(user.getPassword())) {
			existingUser.setPassword(existingUser.getPassword());
		}
		userRepository.save(existingUser);
	}

	public Optional<User> getLoginUser(String email, String password) {
		Optional<User> user = userRepository.findAll().stream()
				.filter(u -> u.getEmail().equals(email) && u.getPassword().equals(hashSHA256(password))).findFirst();
		return user;
	}

	public List<Project> getUserProjects(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user.getProjectsList();
		} else {
			throw new EntityNotFoundException("User not found with id: " + userId);
		}
	}
}
