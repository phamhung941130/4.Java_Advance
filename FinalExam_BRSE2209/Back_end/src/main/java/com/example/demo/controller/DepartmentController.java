package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentForm;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin("*")
public class DepartmentController {
	private IDepartmentService departmentService;

	public DepartmentController() throws FileNotFoundException, IOException {
		departmentService = new DepartmentService();
	}
	
	@GetMapping
	public List<Department> getListDepartment() throws ClassNotFoundException, SQLException {
		return departmentService.getListDepartment();
	}
	
	
	@PostMapping
	public void createDepartment(@RequestBody DepartmentForm form)throws ClassNotFoundException, SQLException{
		departmentService.createDepartment(form.getName());
	}
}
