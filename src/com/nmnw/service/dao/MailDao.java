package com.nmnw.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nmnw.service.utility.DbConnector;

public class MailDao {
	private static final String TABLE_NAME = "mail";

	/**
	 * select
	 * @param code
	 * @return Mail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Mail selectByCode(String code)
			throws ClassNotFoundException, SQLException {
		Connection connection = DbConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where code = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, code);
		ResultSet result = statement.executeQuery();
		Mail mail = new Mail();
		while (result.next()) {
			mail.setCode(result.getString("code"));
			mail.setSubject(result.getString("subject"));
			mail.setMessage(result.getString("message"));
		}
		result.close();
		statement.close();
		connection.close();
		return mail;
	}
}