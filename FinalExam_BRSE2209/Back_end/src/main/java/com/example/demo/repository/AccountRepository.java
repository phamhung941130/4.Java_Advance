package com.example.demo.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.demo.entity.Account;
import com.example.demo.utils.JDBCUtils;
import com.mysql.cj.util.StringUtils;

public class AccountRepository implements IAccountRepository {
	private JDBCUtils jdbcUtils;

	public AccountRepository() throws FileNotFoundException, IOException {
		jdbcUtils = new JDBCUtils();
	}

	public List<Account> getListAccounts(String filterRole, Integer filterDepartmentId, String search)
			throws SQLException, ClassNotFoundException {

		List<Account> accounts = new ArrayList<>();
		boolean isFilterRole = !StringUtils.isNullOrEmpty(filterRole);
		boolean isFilterDepartment = filterDepartmentId != null;
		boolean isSearch = !StringUtils.isNullOrEmpty(search);
		// get connection
		Connection connection = jdbcUtils.getConnection();

		// Create a statement object
		String sql = "SELECT 	a.id, a.username, CONCAT(a.first_name, ' ', a.last_name) AS full_name, a.`role`, a.department_id, d.`name` "
				+ "FROM 	`Account` a " + "JOIN	`Department` d ON a.department_id = d.id ";
		if (isFilterRole) {
			sql += "WHERE `role` = ? ";
		}
		if (isFilterDepartment) {
			if (sql.contains("WHERE")) {
				sql += "and department_id = ? ";
			} else {
				sql += "WHERE department_id = ? ";
			}
		}
		if (isSearch) {
			if (sql.contains("WHERE") && sql.contains("and")) {
				sql += "and username LIKE ?";
			} else if (isFilterDepartment || isFilterRole) {
				sql += "and username LIKE ?";
			} else {
				sql += "WHERE username LIKE ?";
			}
		}
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		if (isFilterRole) {
			preparedStatement.setString(1, filterRole);
		}

		if (isFilterDepartment) {
			if (isFilterRole) {
				preparedStatement.setInt(2, filterDepartmentId);
			} else {
				preparedStatement.setInt(1, filterDepartmentId);
			}
		}

		if (isSearch) {
			if (isFilterRole && isFilterDepartment) {
				preparedStatement.setString(3, "%" + search + "%");
			} else if (isFilterDepartment || isFilterRole) {
				preparedStatement.setString(2, "%" + search + "%");
			} else {
				preparedStatement.setString(1, "%" + search + "%");
			}
		}
		// execute query
		ResultSet resultSet = preparedStatement.executeQuery();

		// handling result set
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String username = resultSet.getString("username");
			String fullname = resultSet.getString("full_name");
			String role = resultSet.getString("role");
			int departmentId = resultSet.getInt("department_id");
			String departmentName = resultSet.getString("name");

			Account account = new Account(id, username, fullname, role, departmentId, departmentName);

			accounts.add(account);
		}

		jdbcUtils.disConnection();

		return accounts;
	}

	public void createAccount(String username, String firstname, String lastname, String role, int departmentId)
			throws SQLException, ClassNotFoundException {
		String password = "123456";
		if (role == null) {
			role = "EMPLOYEE";
		}
		Connection connection = jdbcUtils.getConnection();
		String sql = "INSERT INTO `Account` (`username`, `password`, `first_name`,`last_name`, `role`, `department_id` )"
				+ "VALUES                (?, 			?,			?,				?, 			?,      ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, firstname);
		preparedStatement.setString(4, lastname);
		preparedStatement.setString(5, role);
		preparedStatement.setInt(6, departmentId);

		preparedStatement.executeUpdate();
		jdbcUtils.disConnection();
	}

	public void deleteAcountbyId(int id) throws SQLException, ClassNotFoundException {
		Connection connection = jdbcUtils.getConnection();
		String sql = "DELETE FROM `Account` WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		jdbcUtils.disConnection();
	}

	public void updateAccountbyId(String firstname, String lastname, String role, int departmentId, int id)
			throws ClassNotFoundException, SQLException {
		Connection connection = jdbcUtils.getConnection();
		String sql = "UPDATE `Account` SET first_name=?, last_name=?, role=?, department_id=? WHERE id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, firstname);
		preparedStatement.setString(2, lastname);
		preparedStatement.setString(3, role);
		preparedStatement.setInt(4, departmentId);
		preparedStatement.setInt(5, id);

		preparedStatement.executeUpdate();
		jdbcUtils.disConnection();
	}

	public Account getAccountbyId(int id) throws ClassNotFoundException, SQLException {
		Account account = null;
		Connection connection = jdbcUtils.getConnection();
		String sql = "SELECT id, username, first_name, last_name, `role`, department_id " + "FROM `Account` "
				+ "WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			String firstname = resultSet.getString("first_name");
			String lastname = resultSet.getString("last_name");
			String role = resultSet.getString("role");
			int departmentId = resultSet.getInt("department_id");

			account = new Account(id, username, firstname, lastname, role, departmentId);
		}
		jdbcUtils.disConnection();
		return account;
	}

	public void deleteAllAcount(List<Integer> ids) throws SQLException, ClassNotFoundException {
		Connection connection = jdbcUtils.getConnection();

		String param = String.join(",", Collections.nCopies(ids.size(), "?")); // 1,2,3

		String sql = "DELETE FROM `Account` WHERE id IN (" + param + ")";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < ids.size(); i++) {
			preparedStatement.setInt(i + 1, ids.get(i));
		}

		preparedStatement.executeUpdate();
		jdbcUtils.disConnection();
	}
}
