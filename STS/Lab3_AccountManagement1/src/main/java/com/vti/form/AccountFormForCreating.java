package com.vti.form;

public class AccountFormForCreating {
	private String email;
	private String userName;
	private String fullName;
	private short departmentID;
	private short positionID;

	public AccountFormForCreating() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public short getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(short departmentID) {
		this.departmentID = departmentID;
	}

	public short getPositionID() {
		return positionID;
	}

	public void setPositionID(short positionID) {
		this.positionID = positionID;
	}

}
