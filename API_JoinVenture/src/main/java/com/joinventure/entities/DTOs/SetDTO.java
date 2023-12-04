package com.joinventure.entities.DTOs;

public class SetDTO {

	private String name_project;
	private Integer numMaxMembers;
	private String name_Sector; 
	private String name_Demand;
	
	
	
	public SetDTO() {
		super();
	}
	public SetDTO(String name_project, Integer numMaxMembers, String name_Sector, String name_Demand) {
		super();
		this.name_project = name_project;
		this.numMaxMembers = numMaxMembers;
		this.name_Sector = name_Sector;
		this.name_Demand = name_Demand;
	}
	public String getName_project() {
		return name_project;
	}
	public void setName_project(String name_project) {
		this.name_project = name_project;
	}
	public Integer getNumMaxMembers() {
		return numMaxMembers;
	}
	public void setNumMaxMembers(Integer numMaxMembers) {
		this.numMaxMembers = numMaxMembers;
	}
	public String getName_Sector() {
		return name_Sector;
	}
	public void setName_Sector(String name_Sector) {
		this.name_Sector = name_Sector;
	}
	public String getName_Demand() {
		return name_Demand;
	}
	public void setName_Demand(String name_Demand) {
		this.name_Demand = name_Demand;
	}
	
	
}
