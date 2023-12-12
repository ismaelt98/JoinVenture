package com.joinventure.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
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

	@JsonIgnore
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
}
