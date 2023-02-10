package com.example.demo.repository;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Account;


public interface IAccountRepository {
	
	public List<Account> getListAccounts(String filterRole, Integer filterDepartmentId, String search) throws SQLException, ClassNotFoundException;

	public void createAccount(String username, String firstname, String lastname, String role, int departmentId) throws SQLException, ClassNotFoundException;

	public void deleteAcountbyId(int id) throws SQLException, ClassNotFoundException;
	
	public void updateAccountbyId (String firstname, String lastname, String role, int departmentId, int id) throws ClassNotFoundException, SQLException;
	
	public Account getAccountbyId(int id) throws ClassNotFoundException, SQLException;
	
	public void deleteAllAcount(List<Integer> ids) throws SQLException, ClassNotFoundException;
}
