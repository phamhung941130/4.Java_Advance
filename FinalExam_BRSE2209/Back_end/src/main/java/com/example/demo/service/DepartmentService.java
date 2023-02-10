package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.IDepartmentRepository;

public class DepartmentService implements IDepartmentService{
	private IDepartmentRepository departmentRepo;

	public DepartmentService() throws FileNotFoundException, IOException {
		departmentRepo = new DepartmentRepository();
	}
	
	@Override
	public List<Department> getListDepartment() throws ClassNotFoundException, SQLException {
		return departmentRepo.getListDepartment();
	}
	
	public void createDepartment(String name) throws ClassNotFoundException, SQLException {
		departmentRepo.createDepartment(name);;
	}
}
