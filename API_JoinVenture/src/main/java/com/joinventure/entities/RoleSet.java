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
    private User user;
	 
	@ManyToOne
    @JoinColumn(name = "PROGRAMMER_ROLE_ROLE_PRO_ID")
    private ProgrammerRole programrole;
	
	
	@ManyToOne
    @JoinColumn(name = "ROLE_USER_ROLE_ID") 
    private RoleUser roleuser;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ProgrammerRole getProgramrole() {
		return programrole;
	}


	public void setProgramrole(ProgrammerRole programrole) {
		this.programrole = programrole;
	}


	public RoleUser getRoleuser() {
		return roleuser;
	}


	public void setRoleuser(RoleUser roleuser) {
		this.roleuser = roleuser;
	}
	
   
	
	

	
    
    
}