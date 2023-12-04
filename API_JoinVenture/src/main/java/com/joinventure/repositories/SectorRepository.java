package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{
	Sector findByName(String name);
	boolean existsByName(String name);
}
