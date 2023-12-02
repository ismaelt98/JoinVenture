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
@Table(name ="ROLE_SET")
@EntityListeners(AuditingEntityListener.class)
public class RoleSet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_set_id")
	private Long id;

	@ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user_user_id;
	 
	@ManyToOne
	@JoinColumn(name = "PROGRAMMER_ROLE_ROLE_PRO_ID")
	private ProgrammerRole programmer_roleId;

    @ManyToOne
    @JoinColumn(name = "ROLE_USER_ROLE_ID")
    private RoleUser roleUser_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser_user_id() {
		return user_user_id;
	}

	public void setUser_user_id(User user_user_id) {
		this.user_user_id = user_user_id;
	}

	public ProgrammerRole getProgrammer_roleId() {
		return programmer_roleId;
	}

	public void setProgrammer_roleId(ProgrammerRole programmer_roleId) {
		this.programmer_roleId = programmer_roleId;
	}

	public RoleUser getRoleUser_id() {
		return roleUser_id;
	}

	public void setRoleUser_id(RoleUser roleUser_id) {
		this.roleUser_id = roleUser_id;
	}
    
    
}
