package com.vti.service;

import java.util.List;

import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;

public interface IDepartmentService {
	public List<Department> getListDep();

	public Department getDepartmentByID(short id);

	public Department getDepartmentByName(String name);

	public void createDepartment(DepartmentFormForCreating formInput);

	public void updateDepartment(short idUpdate, DepartmentFormForUpdating forminputUpdate);

}
