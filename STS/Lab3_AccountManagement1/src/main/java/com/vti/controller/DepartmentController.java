package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentDto;
import com.vti.entity.Department;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	private IDepartmentService dService;

	@GetMapping()
	public ResponseEntity<?> getListDepartment() {
//		Đẩy ra danh sach Department
		List<Department> list = dService.getListDep();
// Chuyển đổi dữ liệu lấy được từ ĐB thành dữ liệu DTO
		List<DepartmentDto> listDtos = new ArrayList<>();
		for (Department department : list) {
			DepartmentDto departmentDto = new DepartmentDto();
			departmentDto.setName(department.getName());
			departmentDto.setId(department.getId());
			listDtos.add(departmentDto);
		}

		return new ResponseEntity<>(listDtos, HttpStatus.OK);

	}
}
