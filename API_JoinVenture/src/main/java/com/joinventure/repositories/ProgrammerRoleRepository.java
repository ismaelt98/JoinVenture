package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.ProgrammerRole;

@Repository
public interface ProgrammerRoleRepository extends JpaRepository<ProgrammerRole, Long>{

}
