package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Technology;



@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long>{

}