package com.joinventure.entities.DTOs;

public class UserProjectDTO {
	Long id;
	String username;
	
	String alias;
	String email;
	String phone;
	
	
	public UserProjectDTO() {
		super();
	}
	public UserProjectDTO(Long id, String username, String alias, String email, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.alias = alias;
		this.email = email;
		this.phone = phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
