package com.joinventure.entities.DTOs;

public class UserDTO {
	String username;
	String password;
	String alias;
	String email;
	
	public UserDTO() {}
	
	public UserDTO(String username, String password, String alias, String email) {
		this.username = username;
		this.password = password;
		this.alias = alias;
		this.email = email;
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

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", alias=" + alias + ", email=" + email + "]";
	}
}
