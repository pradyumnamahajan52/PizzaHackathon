package com.pizzabite.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String URL = "jdbc:mysql://172.18.5.36:3306/pizza_delivery";
	private static final String USERNAME = "pizzabite";
	private static final String PASSWORD = "Pradyumna@1234";
	

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
