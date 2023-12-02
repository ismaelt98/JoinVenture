package com.joinventure.entities;
import java.time.LocalDate;


import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name ="PROYECT")
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

  //  @Column(name = "UPDATED_AT", nullable = false)
  //  @LastModifiedDate    
  //  private LocalDate updatedAt;

    
	public Project() {
		super();
	}

	public Project(Long id, String name, Integer numMembers, LocalDate createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.numMembers = numMembers;
		this.createdAt = createdAt;
	}
	
//	public Project(Long id, String name, Integer numMembers, LocalDate create, LocalDate update) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.numMembers = numMembers;
//		this.createdAt = create;
//		this.updatedAt = update;
//	}


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