package com.example.demo.entity;

import java.util.Date;

public class Department {
	private int id;
	private String name;
	private int totalMember;
	private String type;
	private Date createDate;
	
	public Department(int id, String name, int totalmember,String type, Date createDate ) {
		this.id = id;
		this.name = name;
		this.totalMember = totalmember;
		this.type = type;
		this.createDate = createDate;
	}	
	
	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Department(String name) {
		this.name = name;
	}
	
	public Department(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
