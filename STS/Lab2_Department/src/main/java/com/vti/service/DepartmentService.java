package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository dRepository;

	@Override
	public List<Department> getListDep() {

		return dRepository.findAll();
	}

	@Override
	public Department getDepartmentByID(short id) {
		return dRepository.findById(id).get();
	}

	@Override
	public Department getDepartmentByName(String name) {

		return dRepository.findByName(name);
	}

	@Override
	public void createDepartment(DepartmentFormForCreating formInput) {
		String newNameDep = formInput.getName();
		Department department = new Department();
		department.setName(newNameDep);
		dRepository.save(department);

	}

	@Override
	public void updateDepartment(short idUpdate, DepartmentFormForUpdating forminputUpdate) {
		Department department = getDepartmentByID(idUpdate);
		String updateNameDep = forminputUpdate.getName();

		department.setName(updateNameDep);

		dRepository.save(department);

	}

}
