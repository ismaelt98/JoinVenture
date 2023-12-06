package com.joinventure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinventure.entities.Framework;
import com.joinventure.entities.Project;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, Long> {

}
