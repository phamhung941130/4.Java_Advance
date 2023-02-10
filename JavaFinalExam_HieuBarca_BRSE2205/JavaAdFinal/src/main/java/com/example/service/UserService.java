package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entyti.User;
import com.example.repository.IUserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserRepository repository;

	@Override
	public void createUser(User user) {
		repository.save(user);
	}

	@Override
	public User login(String username, String password) {
		return repository.findByUserNameAndPassword(username, password);

	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

}
