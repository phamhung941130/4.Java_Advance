package com.example.service;

import java.util.List;

import com.example.entyti.User;

public interface IUserService {
	void createUser(User user);

	User login(String username, String password);

	List<User> getAllUsers();

}
