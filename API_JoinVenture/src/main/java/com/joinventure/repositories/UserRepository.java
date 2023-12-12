package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByPassword(String password);
	
	boolean existsByPhone(String phone);
	
//	Optional<User> findByEmail(String email);
}
