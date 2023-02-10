package com.example.demo.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.Department;
import com.example.demo.utils.JDBCUtils;

public class DepartmentRepository implements IDepartmentRepository{
	private JDBCUtils jdbcUtils;

	public DepartmentRepository() throws FileNotFoundException, IOException {
		jdbcUtils = new JDBCUtils();
	}
	
	@Override
	public List<Department> getListDepartment() throws ClassNotFoundException, SQLException {
		List<Department> listDepartment = new ArrayList<Department>();
		Connection connection = jdbcUtils.getConnection();
		String sql = "SELECT id, `name`, total_member, `type`, created_date  FROM Department";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int totalMember = resultSet.getInt("total_member");
			String type = resultSet.getString("type");
			Date createDate = resultSet.getDate("created_date");
			

			Department department = new Department(id, name, totalMember, type, createDate );
			listDepartment.add(department);
		}
		return listDepartment;
	}
	
	public void createDepartment(String name) throws ClassNotFoundException, SQLException {
		Connection connection = jdbcUtils.getConnection();
		String sql = "INSERT INTO Department (name)"
				     + "VALUE                (?)";
		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,name);
		
		preparedStatement.executeUpdate();
		jdbcUtils.disConnection();
	}
	
	
	
}
