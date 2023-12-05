package com.joinventure.entities;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROYECT")
@EntityListeners(AuditingEntityListener.class)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROYECT_ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "NUM_MEMBERS", nullable = false)
	private Integer numMembers;

	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDate createdAt;
	
	@ManyToOne
    @JoinColumn(name = "fk_sector_id")
    private Sector sector;

	@ManyToOne
    @JoinColumn(name = "fk_demand_id")
    private Demand demand;
	

	@ManyToMany
	@JoinTable(name = "user_has_proyect", joinColumns = @JoinColumn(name = "PROYECT_ID"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	List<User> userList;
	
	
	
	public Project() {
		super();
	}

	

	public Project(Long id, String name, Integer numMembers, LocalDate createdAt, Sector sector, Demand demand,
			List<User> userList) {
		super();
		this.id = id;
		this.name = name;
		this.numMembers = numMembers;
		this.createdAt = createdAt;
		this.sector = sector;
		this.demand = demand;
		this.userList = userList;
	}



//	public Project(Long id, String name, Integer numMembers, LocalDate create, LocalDate update) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.numMembers = numMembers;
//		this.createdAt = create;
//		this.updatedAt = update;
//	}

	public Sector getSector() {
		return sector;
	}



	public void setSector(Sector sector) {
		this.sector = sector;
	}



	public Demand getDemand() {
		return demand;
	}



	public void setDemand(Demand demand) {
		this.demand = demand;
	}



	public List<User> getUserList() {
		return userList;
	}



	public void setUserList(List<User> userList) {
		this.userList = userList;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

//	public LocalDate getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDate updatedAt) {
//		this.updatedAt = updatedAt;
//	}

}