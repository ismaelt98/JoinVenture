package com.joinventure.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int nummembers;
	private String sector;
	private String demand;

	@ManyToOne
	@JoinColumn(name = "usercreatorid", nullable = false)
	private User userCreator;

	@ManyToMany
	@JoinTable(name = "userhasproject", joinColumns = @JoinColumn(name = "projectid"), inverseJoinColumns = @JoinColumn(name = "userid"))
	List<User> usersList = new ArrayList<>();
}
