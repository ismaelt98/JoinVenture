package com.joinventure.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Demand;


@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
	Demand findByName(String name);
	boolean existsByName(String name);
}
