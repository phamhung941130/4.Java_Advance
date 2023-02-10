package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entyti.Group;
import com.example.form.FormGroup;

public interface IGroupService {
	public Page<Group> getAllGroups(Pageable pageable);

	void createGroup(Group group);

	Group updateGroup(FormGroup formGroup);

	void deleteGroup(FormGroup formGroup);

	boolean idIsExists(FormGroup formGroup);
}
