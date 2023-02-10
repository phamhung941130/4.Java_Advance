package com.example.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entyti.Group;
import com.example.form.FormGroup;
import com.example.repository.IGroupRepository;

@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupRepository groupRepository;
	@Autowired
	private IGroupUserService groupUserService;

	@Override
	public Page<Group> getAllGroups(Pageable pageable) {
		return groupRepository.findAll(pageable);
	}

	@Override
	public void createGroup(Group group) {
		groupRepository.save(group);
	}

	@Override
	public Group updateGroup(FormGroup formGroup) {
		if (idIsExists(formGroup)) {
			int id = formGroup.getId();
			String name = formGroup.getGroupName();
			int member = formGroup.getMember();
			Optional<Group> groups = groupRepository.findById(id);
			Group group = groups.get();
			group.setId(id);
			group.setGroupName(name);
			group.setMember(member);
			group.setCreateDate(new Date());
			return groupRepository.save(group);
		} else {
			return null;
		}

	}

	@Override
	public void deleteGroup(FormGroup formGroup) {
		if (idIsExists(formGroup)) {
			groupRepository.deleteById(formGroup.getId());
		}
	}

	@Override
	public boolean idIsExists(FormGroup formGroup) {
		int id = formGroup.getId();
		if (groupRepository.existsById(id)) {
			return true;
		}
		return false;
	}

}
