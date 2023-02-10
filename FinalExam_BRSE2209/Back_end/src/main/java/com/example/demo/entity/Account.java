package com.example.demo.entity;

public class Account {
	private int id;
	private String username;
	private String fullname;
	private String firstname;
	private String lastname;
	private String role;
	private Department department;
	
	
	public Account(int id, String username, String fullname, String role, int departmentId, String departmentName) {
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.role = role;
		this.department = new Department(departmentId, departmentName);
	}

	public Account(int id, String username, String firstname, String lastname, String role, int departmentId) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.department = new Department(departmentId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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


}
