package br.com.fiap.fintech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static ConnectionManager connectionManager;
	
	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		if(connectionManager.equals(null)) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "");
				return connection;
			} catch (SQLException e) {
				System.err.println("Erro ao conectar no banco de dados.");
				return null;
			}
	}
}

