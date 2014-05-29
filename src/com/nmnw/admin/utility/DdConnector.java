package com.nmnw.admin.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nmnw.admin.constant.ConfigConstants;

public class DdConnector {
	public static Connection getConnection()
			throws ClassNotFoundException, SQLException {
		Class.forName (ConfigConstants.DB_DRIVER_NAME);
		Connection connection = DriverManager.getConnection(ConfigConstants.JDBC_URL, ConfigConstants.DB_USER_NAME, ConfigConstants.DB_PASSWORD);
		connection.setAutoCommit(false);
		return connection;
	}
}