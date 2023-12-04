package com.joinventure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Framework;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, Long>{
	Optional<Framework> findByName(String name);
}
