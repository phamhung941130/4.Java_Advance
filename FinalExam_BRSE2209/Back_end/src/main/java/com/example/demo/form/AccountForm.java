package com.example.demo.form;

public class AccountForm {
	private String username;
	private String firstname;
	private String lastname;
	private String role;
	private int departmentId;
	
	public AccountForm() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "AccountForm [username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", role=" + role + ", departmentId=" + departmentId + "]";
	}
	
}
