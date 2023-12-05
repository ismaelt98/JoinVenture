package com.joinventure.entities.DTOs;

public class UserProjectsDTO {
	Long id;
	String name;
	
	Integer members;
	
	

	public UserProjectsDTO() {
		super();
	}

	public UserProjectsDTO(Long id, String name, Integer members) {
		super();
		this.id = id;
		this.name = name;
		this.members = members;
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

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "UserProjectsDTO [id=" + id + ", name=" + name + ", members=" + members + "]";
	}
	
}
