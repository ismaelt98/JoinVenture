package com.joinventure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.ProgrammerRole;
import com.joinventure.entities.User;

@Repository
public interface ProgrammerRoleRepository extends JpaRepository<ProgrammerRole, Long>{

	ProgrammerRole findByName(String name);
}
