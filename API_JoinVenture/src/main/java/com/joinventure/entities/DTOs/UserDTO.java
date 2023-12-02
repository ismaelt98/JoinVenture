package com.joinventure.entities.DTOs;

public class UserDTO {
	String username;
	String password;
	String lastname;
	String email;
	Integer phone;
	
	public UserDTO() {}
	
	public UserDTO(String username, String password, String lastname, String email, Integer phone) {
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
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

	
	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", lastname=" + lastname + ", email=" + email + ", phone=" + phone + "]";
	}
}
