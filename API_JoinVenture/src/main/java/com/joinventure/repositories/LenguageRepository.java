package com.joinventure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Language;


@Repository
public interface LenguageRepository extends JpaRepository<Language, Long>{
	Optional<Language> findByName(String name);
}
