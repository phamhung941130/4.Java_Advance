package com.vti.dto;

public class DepartmentDto {
	private short id;
	private String name;

	public DepartmentDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

}
