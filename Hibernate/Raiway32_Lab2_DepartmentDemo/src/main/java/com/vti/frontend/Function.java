package com.vti.frontend;

import java.util.List;
import java.util.Scanner;

import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;

public class Function {
	private DepartmentRepository departmentRepository;
	private Scanner scanner;

	public Function() {
		departmentRepository = new DepartmentRepository();
		scanner = new Scanner(System.in);
	}

	public void showlistDep() {
		System.out.println("danh sach");
		List<Department> listDep = departmentRepository.getListDep();

		for (Department department : listDep) {
			System.out.println(department.toString());
		}
	}

	public void findByID() {
		System.out.println("moi ban nhập ID");
		int idFind = scanner.nextInt();
		Department department = departmentRepository.getDepartmentByID((short) idFind);
		if (department != null) {
			System.out.println("Pòng ban ban cần tìm là");
			System.out.println(department.toString());
		} else {
			System.out.println("ko ton tai!");
		}
	}
}
