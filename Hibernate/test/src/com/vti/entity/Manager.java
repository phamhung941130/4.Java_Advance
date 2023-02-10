package com.vti.entity;

public class Manager extends User {

	public Manager(String name, Double salaryRatio) {
		super(name, salaryRatio);

	}

	@Override
	public Double calculatePay() {
		return salaryRatio * 520;
	}

}
