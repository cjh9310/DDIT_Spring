package com.java.datasource;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class JDBCDataSource {

	private static JDBCDataSource instance = new JDBCDataSource();
	private Properties properties = new Properties();

	private JDBCDataSource() {
	}

	{
		String resource = "com/java/properties/db.properties";
		

		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
			
			String driverClassName = properties.getProperty("jdbc.driverClassName");
			
			Class.forName(driverClassName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JDBCDataSource getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn;
		
		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");

		conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
}








