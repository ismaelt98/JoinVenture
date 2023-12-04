package com.joinventure.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.RoleSet;


@Repository
public interface RoleSetRepository extends JpaRepository<RoleSet, Long> {

	List<RoleSet> findByUser_Email(String email);
}