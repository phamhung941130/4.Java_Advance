package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.form.AccountForm;
import com.example.demo.service.AccountService;
import com.example.demo.service.IAccountService;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
	
	private IAccountService service;
	
	public AccountController() throws FileNotFoundException, IOException {
		service = new AccountService();
		
	}
	
	@GetMapping
	public List<Account> getListAccounts(
			@RequestParam(name = "role", required = false)String filterRole,
			@RequestParam(name = "departmentId", required = false)Integer filterDepartmentId, 
			@RequestParam(name = "username", required = false)String search) 
					throws ClassNotFoundException, SQLException {
		return service.getListAccounts(filterRole, filterDepartmentId, search);
	}
	
	@PostMapping
	public void createAccount(@RequestBody AccountForm form)throws ClassNotFoundException, SQLException{
		service.createAccount(form.getUsername(), form.getFirstname(),form.getLastname(), form.getRole(), form.getDepartmentId());
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteAcount(@PathVariable(name = "id") int id) throws ClassNotFoundException, SQLException {
		service.deleteAcountbyId(id);
	}
	
	@PutMapping(value="/{id}")
	public void updateAccountbyId(@PathVariable(name="id") int id, @RequestBody AccountForm form) throws ClassNotFoundException, SQLException{
		service.updateAccountbyId(form.getFirstname(),form.getLastname(), form.getRole(), form.getDepartmentId(), id);
	}
	
	@GetMapping (value="/{id}")
		public  Account getAccountbyId(@PathVariable(name ="id") int id) throws ClassNotFoundException, SQLException {
		return service.getAccountbyId(id);
	}
	
	@DeleteMapping()
	public void deleteAllAcount(@RequestParam(name = "ids") List<Integer> ids) throws ClassNotFoundException, SQLException {
		service.deleteAllAcount(ids);
	}
}
