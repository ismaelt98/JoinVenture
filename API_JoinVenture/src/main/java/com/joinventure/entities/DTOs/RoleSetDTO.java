package com.joinventure.entities.DTOs;

public class RoleSetDTO {
	
	private String user_name;
	private String last_name;
	private String email;
	private Integer phone;
	private String name_role_user;
	private String name_role_programmer;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getName_role_user() {
		return name_role_user;
	}

	public void setName_role_user(String name_role_user) {
		this.name_role_user = name_role_user;
	}

	public String getName_role_programmer() {
		return name_role_programmer;
	}

	public void setName_role_programmer(String name_role_programmer) {
		this.name_role_programmer = name_role_programmer;
	}


}