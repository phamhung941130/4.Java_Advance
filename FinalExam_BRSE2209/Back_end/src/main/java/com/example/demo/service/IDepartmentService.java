package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Department;

public interface IDepartmentService {
	
	List<Department> getListDepartment() throws ClassNotFoundException, SQLException;
	
	public void createDepartment(String name) throws ClassNotFoundException, SQLException;
	
}
