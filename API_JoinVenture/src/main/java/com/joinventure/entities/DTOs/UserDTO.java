package com.joinventure.entities.DTOs;

public class UserDTO {
	String username;
	String name;
	String lastname;
	String email;
	
	public UserDTO() {}
	
	public UserDTO(String username, String name, String lastname, String email) {
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
	}
}
