package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	// dùng để đọc file config
		private Properties properties;
		// kết nối đến DB
		private Connection connection;

		// Đây là contructor
		// Mục đích đọc luôn file config khi khởi tạo đối tượng
		public JDBCUtils() throws FileNotFoundException, IOException {
			properties = new Properties();
			properties
					.load(new FileInputStream("C:\\Users\\Admin\\Desktop\\CRUD\\Back_end\\src\\main\\resources\\db.properties"));
		}
		// Hàm để kết nối tới Database
			public Connection getConnection() throws ClassNotFoundException, SQLException {
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String pass = properties.getProperty("password");
				String driver = properties.getProperty("driver");

				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, pass);
				// System.out.println("Connection success...");
				return connection;
			}

			// Hàm để ngắt kết nối đến Database sau khi sử dụng xong
			public void disConnection() throws SQLException {
				connection.close();
			}

			// Hàm chung dành cho các câu lệnh select
			// sql = select * from department;
			public ResultSet excuteQuery(String sql) throws ClassNotFoundException, SQLException {
				Connection connection = getConnection();
				Statement stament = connection.createStatement();
				ResultSet resultSet = stament.executeQuery(sql);
				return resultSet;
			}

			// Hàm chung giành cho các câu lệnh insert, update, delete
			public PreparedStatement createPrepareStatement(String sql) throws ClassNotFoundException, SQLException {
				Connection connnection = getConnection();
				PreparedStatement preStatement = connnection.prepareStatement(sql);
				return preStatement;
			}
}
