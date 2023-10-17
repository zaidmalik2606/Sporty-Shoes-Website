package com.sportyshoes.SportyShoes.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection{
	public Connection getConnection() {
		try {
			
			String url = "jdbc:mysql://localhost:3306/sportyshoes";
			String user ="root";
			String pwd="root";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pwd);
			
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
}
