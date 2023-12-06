package com.joinventure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SECTOR")
public class Sector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SECTOR_ID")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;

	public Sector() {
		super();
	}

	public Sector(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "Sector [id=" + id + ", name=" + name + "]";
	}
}
