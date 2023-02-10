package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.FormCreateGroupUser;
import com.example.service.IGroupUserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/final-exam/groupUsers")
public class GroupUserController {
	@Autowired
	private IGroupUserService service;

	@PostMapping
	public void create(@RequestBody FormCreateGroupUser form) {
		service.create(form);
	}
}
