package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.form.AccountFormForCreatingRegister;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accountsRegister")
@CrossOrigin("*")
public class AccountRegisterController {
	@Autowired
	private IAccountService accountService;

	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountFormForCreatingRegister accountCreateRegisterFormInput) {
		accountService.createAccountRegister(accountCreateRegisterFormInput);

		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

}
