package com.joinventure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.RoleSet;
import com.joinventure.entities.DTOs.RoleSetDTO;

@Repository
public interface RoleSetRepository extends JpaRepository<RoleSet, Long> {

	List<RoleSet> findByUser_Email(String email);
}