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
@Table(name ="TECHNOLOGIES")
@EntityListeners(AuditingEntityListener.class)
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "technology_id")
	private Long id;

	@ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user_user_id;
	 
	@ManyToOne
	@JoinColumn(name = "LENGUAGE_LENGUAGE_ID")
	private Language lenguage_lenguage_id;

    @ManyToOne
    @JoinColumn(name = "FRAMEWORKS_FRAMEWORKS_ID")
    private Framework frameworks_frameworks_id;

	public User getUser_user_id() {
		return user_user_id;
	}

	public void setUser_user_id(User user_user_id) {
		this.user_user_id = user_user_id;
	}

	public Language getLenguage_lenguage_id() {
		return lenguage_lenguage_id;
	}

	public void setLenguage_lenguage_id(Language lenguage_lenguage_id) {
		this.lenguage_lenguage_id = lenguage_lenguage_id;
	}

	public Framework getFrameworks_frameworks_id() {
		return frameworks_frameworks_id;
	}

	public void setFrameworks_frameworks_id(Framework frameworks_frameworks_id) {
		this.frameworks_frameworks_id = frameworks_frameworks_id;
	}

   
}
