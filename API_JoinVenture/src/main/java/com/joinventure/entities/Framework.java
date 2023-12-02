package com.joinventure.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="FRAMEWORKS")
@EntityListeners(AuditingEntityListener.class)
public class Framework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FRAMEWORKS_ID")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	
	
}
