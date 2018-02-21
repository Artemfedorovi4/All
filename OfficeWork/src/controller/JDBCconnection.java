package controller;

import java.sql.*;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class JDBCconnection {

	private Connection connection;
	
	public JDBCconnection() throws SQLException{
		DriverManager.registerDriver(new FabricMySQLDriver());
	}
	
	public Connection getConnetcion(String url, String user, String pass) throws SQLException {
		if(connection!= null)
		return connection;
		connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}
}
