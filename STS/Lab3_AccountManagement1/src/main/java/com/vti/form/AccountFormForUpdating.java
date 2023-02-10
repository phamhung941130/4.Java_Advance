package com.vti.form;

public class AccountFormForUpdating {
	private String fullName;
	private short departmentID;
	private short positionID;

	public AccountFormForUpdating() {
		super();
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
