package com.joinventure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.RoleUser;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
	Optional<RoleUser> findByName(String name);
}
