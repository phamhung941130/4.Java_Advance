package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entyti.Group;
import com.example.form.FormGroup;
import com.example.service.IGroupService;

@CrossOrigin
@RestController
@RequestMapping(value = "api/final-exam/groups")
public class GroupController {

	@Autowired
	private IGroupService groupService;

	@GetMapping
	public Page<Group> listGroups(Pageable pageable) {

		return groupService.getAllGroups(pageable);
	}

	@PostMapping
	public void createGroup(@RequestBody Group group) {
		groupService.createGroup(group);
	}

	@PutMapping()
	public Group updateGroup(@RequestBody FormGroup fomFormGroup) {
		return groupService.updateGroup(fomFormGroup);
	}

	@DeleteMapping()
	public void deleteGroup(@RequestBody FormGroup formGroup) {
		groupService.deleteGroup(formGroup);
	}

	@PutMapping(value = "/idIsExists")
	public boolean idIsExists(@RequestBody FormGroup formGroup) {
		if (groupService.idIsExists(formGroup)) {
			return true;
		}
		return false;
	}

}
