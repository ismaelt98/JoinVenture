package com.joinventure.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name ="INTEGRATION")
@EntityListeners(AuditingEntityListener.class)
public class Integration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTEGRATION_id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "proyect_proyect_id")
    private Project proyect_proyect_id;
	
	@ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user_user_id;
	
	@Column(name = "INTEGRATED_AT", nullable = false, updatable = false)
    @CreatedDate 
    private LocalDate integratedAt;

	public Integration() {
		super();
	}

	public Integration(Project proyect_proyect_id, User user_user_id, LocalDate integratedAt) {
		super();
		this.proyect_proyect_id = proyect_proyect_id;
		this.user_user_id = user_user_id;
		this.integratedAt = integratedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProyect_proyect_id() {
		return proyect_proyect_id;
	}

	public void setProyect_proyect_id(Project proyect_proyect_id) {
		this.proyect_proyect_id = proyect_proyect_id;
	}

	public User getUser_user_id() {
		return user_user_id;
	}

	public void setUser_user_id(User user_user_id) {
		this.user_user_id = user_user_id;
	}

	public LocalDate getIntegratedAt() {
		return integratedAt;
	}

	public void setIntegratedAt(LocalDate integratedAt) {
		this.integratedAt = integratedAt;
	}
	
	

}
