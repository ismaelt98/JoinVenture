package com.joinventure.entities.DTOs;

import java.util.List;

public class UserDTO {
	
	Long id;
	String username;
	
	String alias;
	String email;
	String phone;
	String name_role;
	List<String> projectNames;
	
	public UserDTO() {}
	
	

	




	




	




	public UserDTO(Long id, String username, String alias, String email, String phone, String name_role,
			List<String> projectNames) {
		super();
		this.id = id;
		this.username = username;
		this.alias = alias;
		this.email = email;
		this.phone = phone;
		this.name_role = name_role;
		this.projectNames = projectNames;
	}


















	public String getPhone() {
		return phone;
	}


















	public void setPhone(String phone) {
		this.phone = phone;
	}


















	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}


	public List<String> getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(List<String> projectNames) {
		this.projectNames = projectNames;
	}




	public String getName_role() {
		return name_role;
	}

	public void setName_role(String name_role) {
		this.name_role = name_role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", alias=" + alias + ", email=" + email + ", role=" + name_role + "]";
	}
}
