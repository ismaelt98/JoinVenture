package com.joinventure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Lenguage;

@Repository
public interface LenguageRepository extends JpaRepository<Lenguage, Long>{
	Optional<Lenguage> findByName(String name);
}
