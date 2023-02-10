package com.example.demo.repository;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Department;

public interface IDepartmentRepository {

	List<Department> getListDepartment() throws ClassNotFoundException, SQLException;
	public void createDepartment(String name) throws ClassNotFoundException, SQLException;
}
