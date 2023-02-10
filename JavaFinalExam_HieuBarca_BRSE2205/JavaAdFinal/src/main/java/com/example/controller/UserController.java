package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entyti.User;
import com.example.service.IUserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/final-exam/users")
public class UserController {

	@Autowired
	private IUserService service;

	@PostMapping
	public User login(@RequestBody User user) {
		return service.login(user.getUserName(), user.getPassword());
	}

}
