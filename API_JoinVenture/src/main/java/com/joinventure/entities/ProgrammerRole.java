package com.joinventure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="PROGRAMMER_ROLE")
public class ProgrammerRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_PRO_ID")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
    public String name;
	
	public ProgrammerRole() {
		super();
	}

	public ProgrammerRole(Long id, String name) {
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
		return "ProgrammerRole [id=" + id + ", name=" + name + "]";
	}
}
