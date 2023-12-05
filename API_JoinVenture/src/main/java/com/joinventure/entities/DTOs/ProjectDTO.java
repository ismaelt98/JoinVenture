package com.joinventure.entities.DTOs;

import java.util.List;

public class ProjectDTO {
	Long id;
	String name;
	
	Integer numMembers;
	String name_sector;
	String name_demanda;
	String name_creador;
	String email_creador;
	List<String> usersName;
	
	
	
	public ProjectDTO() {
		super();
	}
	public ProjectDTO(Long id, String name, Integer numMembers, String name_sector, String name_demanda, String name_creador,
			String email_creador, List<String> usersName) {
		super();
		this.id = id;
		this.name = name;
		this.numMembers = numMembers;
		this.name_sector = name_sector;
		this.name_demanda = name_demanda;
		this.name_creador = name_creador;
		this.email_creador = email_creador;
		this.usersName = usersName;
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
	public Integer getNumMembers() {
		return numMembers;
	}
	public void setNumMembers(Integer numMembers) {
		this.numMembers = numMembers;
	}
	public String getName_sector() {
		return name_sector;
	}
	public void setName_sector(String name_sector) {
		this.name_sector = name_sector;
	}
	public String getName_demanda() {
		return name_demanda;
	}
	public void setName_demanda(String name_demanda) {
		this.name_demanda = name_demanda;
	}
	public String getName_creador() {
		return name_creador;
	}
	public void setName_creador(String name_creador) {
		this.name_creador = name_creador;
	}
	public String getEmail_creador() {
		return email_creador;
	}
	public void setEmail_creador(String email_creador) {
		this.email_creador = email_creador;
	}
	public List<String> getUsersName() {
		return usersName;
	}
	public void setUsersName(List<String> usersName) {
		this.usersName = usersName;
	}
	
	
}