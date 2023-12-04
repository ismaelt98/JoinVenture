package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Integration;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long>{

}
