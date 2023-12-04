package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.SetEntity;


@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {

}
