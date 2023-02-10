package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentDto;
import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
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
			listDtos.add(departmentDto);
		}

		return new ResponseEntity<>(listDtos, HttpStatus.OK);

	}

// http://localhost:8080/api/v1/departments/1
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getDepartmentByID(@PathVariable(name = "id") short idInput) {
		Department department = dService.getDepartmentByID(idInput);
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setName(department.getName());
		return new ResponseEntity<>(departmentDto, HttpStatus.OK);
	}

	// http://localhost:8080/api/v1/departments/saerchbyname/Sale
	@GetMapping(value = "/searchbyname/{name}")
	public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String nameInput) {
		Department department = dService.getDepartmentByName(nameInput);

		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setName(department.getName());
		return new ResponseEntity<>(departmentDto, HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentFormForCreating forminputCreate) {
//		String newNameDep = formInput.getName();
		dService.createDepartment(forminputCreate);
		return new ResponseEntity<>("Create Success!!", HttpStatus.CREATED);

	}

	// http://localhost:8080/api/v1/departments/1
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") short idUpdate,
			@RequestBody DepartmentFormForUpdating forminputUpdate) {
//		String newNameDep = formInput.getName();
		dService.updateDepartment(idUpdate, forminputUpdate);
		return new ResponseEntity<>("Update Success!!", HttpStatus.CREATED);

	}
}
