package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entyti.Group;
import com.example.entyti.GroupUser;
import com.example.entyti.User;
import com.example.form.FormCreateGroupUser;
import com.example.repository.IGroupRepository;
import com.example.repository.IGroupUserRepository;
import com.example.repository.IUserRepository;

@Service
public class GroupUserService implements IGroupUserService {

	@Autowired
	private IGroupUserRepository repository;

	@Autowired
	private IGroupRepository groupRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public void create(FormCreateGroupUser form) {
		User user = userRepository.getById(form.getUser_id());
		Group group = groupRepository.getById(form.getGroup_id());
		GroupUser groupUser = new GroupUser();
		groupUser.setGroup(group);
		groupUser.setUser(user);
		repository.save(groupUser);
	}

}
