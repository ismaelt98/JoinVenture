package com.joinventure.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="SET")
@EntityListeners(AuditingEntityListener.class)
public class SetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "set_id")
	private Long id;

	
	@ManyToOne
    @JoinColumn(name = "PROYECT_PROYECT_ID")
    private Project project;
	 
	@ManyToOne
    @JoinColumn(name = "SECTOR_SECTOR_ID")
    private Sector sector;
	
	
	@ManyToOne
    @JoinColumn(name = "DEMAND_DEMAND_ID") 
    private Demand demand;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


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
	
	
}
