package com.prolink.sync.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://192.168.0.202:3306/clientev1", "prolink", "77i#EU&K");
		}catch(ClassNotFoundException e){
			return null;
		}catch(SQLException e){
			return null;
		}
	}
	
}
