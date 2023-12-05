package com.joinventure.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name ="USER")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USER_NAME", nullable = false)
    private String username;   

    @Column(name = "ALIAS", nullable = false)
    private String alias;

	@Column(name = "EMAIL", unique = true)
    private String email;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreatedDate 
    private LocalDate createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    @LastModifiedDate
    private LocalDate updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "fk_roleuser_id")
    private RoleUser roleuser;
    
    @ManyToMany
	@JoinTable(name = "user_has_proyect", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "PROYECT_ID"))
	List<Project> projectList;
    
    @ManyToMany
	@JoinTable(name = "user_has_lenguage", joinColumns = @JoinColumn(name = "fk_user_id"), inverseJoinColumns = @JoinColumn(name = "fk_lenguage_id"))
	List<Language> listLanguage;
    
    @ManyToMany
	@JoinTable(name = "user_has_frameworks", joinColumns = @JoinColumn(name = "fk_user_id"), inverseJoinColumns = @JoinColumn(name = "fk_frameworks_id"))
	List<Framework> listFrameworks;
	
	public User() {}

	public User(Long id, String username, String alias, String email, String password, String phone,
			LocalDate createdAt, LocalDate updatedAt, RoleUser roleuser, List<Project> projectList,
			List<Language> listLanguage, List<Framework> listFrameworks) {
		super();
		this.id = id;
		this.username = username;
		this.alias = alias;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roleuser = roleuser;
		this.projectList = projectList;
		this.listLanguage = listLanguage;
		this.listFrameworks = listFrameworks;
	}






	public List<Language> getListLanguage() {
		return listLanguage;
	}






	public void setListLanguage(List<Language> listLanguage) {
		this.listLanguage = listLanguage;
	}






	public List<Framework> getListFrameworks() {
		return listFrameworks;
	}






	public void setListFrameworks(List<Framework> listFrameworks) {
		this.listFrameworks = listFrameworks;
	}






	public RoleUser getRoleuser() {
		return roleuser;
	}



	public void setRoleuser(RoleUser roleuser) {
		this.roleuser = roleuser;
	}



	public List<Project> getProjectList() {
		return projectList;
	}



	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	 public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", alias=" + alias
                + ", email=" + email + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", phone=" + phone + "]";
    }
}
