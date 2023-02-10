package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.IAccountRepository;

public class AccountService implements IAccountService {

	private IAccountRepository repository;

	public AccountService() throws FileNotFoundException, IOException {
		repository = new AccountRepository();
	}

	@Override
	public List<Account> getListAccounts(String filterRole, Integer filterDepartmentId, String search) throws SQLException, ClassNotFoundException {
		return repository.getListAccounts(filterRole, filterDepartmentId, search);
	}

	public void createAccount(String username, String firstname, String lastname, String role, int departmentId) throws SQLException, ClassNotFoundException {
		repository.createAccount(username,firstname,lastname,role,departmentId );
	}
	
	public void deleteAcountbyId(int id) throws ClassNotFoundException, SQLException {
		repository.deleteAcountbyId(id);
	}
	public void updateAccountbyId (String firstname, String lastname, String role, int departmentId, int id) throws ClassNotFoundException, SQLException{
		repository.updateAccountbyId(firstname, lastname, role, departmentId, id);
	}
	
	public Account getAccountbyId(int id) throws ClassNotFoundException, SQLException{
		return repository.getAccountbyId(id);
	}
	
	public void deleteAllAcount(List<Integer> ids) throws SQLException, ClassNotFoundException {
		repository.deleteAllAcount(ids);
	}
}
