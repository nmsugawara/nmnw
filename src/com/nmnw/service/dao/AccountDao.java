package com.nmnw.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.utility.DdConnector;

public class AccountDao {
	private static final String TABLE_NAME = "account";

	/**
	 * select(account_id指定)
	 * @param id
	 * @return Account
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Account selectByAccountId(int id)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		Account account = new Account();
		while (result.next()) {
			account.setId(result.getInt("id"));
			account.setName(result.getString("name"));
			account.setNameKana(result.getString("name_kana"));
			account.setMail(result.getString("mail"));
			account.setPassWord(result.getString("password"));
			account.setZipCode(result.getString("zip_code"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phone_number"));
			account.setDelFlg(result.getBoolean("del_flg"));
			account.setToken(result.getString("token"));
			account.setTokenExpireTime(result.getTimestamp("token_expire_time"));
			account.setSalt(result.getString("salt"));
		}
		result.close();
		statement.close();
		connection.close();
		return account;
	}

	/**
	 * select(mailをキーとして会員情報を取得)
	 * @param mail
	 * @return Account
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Account selectByMail(String mail)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where mail = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, mail);
		ResultSet result = statement.executeQuery();
		Account account = new Account();
		while (result.next()) {
			account.setId(result.getInt("id"));
			account.setName(result.getString("name"));
			account.setNameKana(result.getString("name_kana"));
			account.setMail(result.getString("mail"));
			account.setPassWord(result.getString("password"));
			account.setZipCode(result.getString("zip_code"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phone_number"));
			account.setDelFlg(result.getBoolean("del_flg"));
			account.setToken(result.getString("token"));
			account.setTokenExpireTime(result.getTimestamp("token_expire_time"));
			account.setSalt(result.getString("salt"));
		}
		result.close();
		statement.close();
		connection.close();
		return account;
	}

	/**
	 * select(token,token有効期限をキーとして会員情報を取得)
	 * @param token
	 * @param currentDate
	 * @return Account
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Account selectByTokenAndTokenExpireTime(String token, Calendar currentDateTime)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where token = ?"
				+ " and token_expire_time > ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, token);
		statement.setTimestamp(2, new Timestamp(currentDateTime.getTimeInMillis()));
		ResultSet result = statement.executeQuery();
		Account account = new Account();
		while (result.next()) {
			account.setId(result.getInt("id"));
			account.setName(result.getString("name"));
			account.setNameKana(result.getString("name_kana"));
			account.setMail(result.getString("mail"));
			account.setPassWord(result.getString("password"));
			account.setZipCode(result.getString("zip_code"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phone_number"));
			account.setDelFlg(result.getBoolean("del_flg"));
			account.setToken(result.getString("token"));
			account.setTokenExpireTime(result.getTimestamp("token_expire_time"));
			account.setSalt(result.getString("salt"));
		}
		result.close();
		statement.close();
		connection.close();
		return account;
	}

	/**
	 * select(文字列データをキーとして会員情報を取得)
	 * @param String
	 * @return Account
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Account selectByStringColumn(String value, String columnName)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where " + columnName + " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, value);
		ResultSet result = statement.executeQuery();
		Account account = new Account();
		while (result.next()) {
			account.setId(result.getInt("id"));
			account.setName(result.getString("name"));
			account.setNameKana(result.getString("name_kana"));
			account.setMail(result.getString("mail"));
			account.setPassWord(result.getString("password"));
			account.setZipCode(result.getString("zip_code"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phone_number"));
			account.setDelFlg(result.getBoolean("del_flg"));
			account.setToken(result.getString("token"));
			account.setTokenExpireTime(result.getTimestamp("token_expire_time"));
			account.setSalt(result.getString("salt"));
		}
		result.close();
		statement.close();
		connection.close();
		return account;
	}

	/**
	 * insert
	 * @param account
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insert (Account account)
			throws ClassNotFoundException, SQLException {
			Connection connection = DdConnector.getConnection();
			String sql = "insert into " + TABLE_NAME
					+ " (name, name_kana, mail, zip_code, address, phone_number)"
					+ " values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, account.getName());
			statement.setString(2, account.getNameKana());
			statement.setString(3, account.getMail());
			statement.setString(4, account.getZipCode());
			statement.setString(5, account.getAddress());
			statement.setString(6, account.getPhoneNumber());
			int updateCount = statement.executeUpdate();
			statement.close();
			connection.commit();

			String getIdSql = "select last_insert_id() as id";
			statement = connection.prepareStatement(getIdSql);
			ResultSet result = statement.executeQuery();
			int id = 0; 
			while (result.next()) {
				id = result.getInt("id");
			}
			connection.close();
			return id;
		}

	/**
	 * update
	 * @param account
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update (Account account)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("update " + TABLE_NAME + " set");
		StringBuilder paramBuilder = new StringBuilder();
		Map<String, Map<String, Object>> dataList = new LinkedHashMap<String, Map<String, Object>>();

		if (!account.getName().isEmpty()) {
			paramBuilder.append(", name=?");
			dataList.put("name", new HashMap<String, Object>());
			dataList.get("name").put("type", "String");
			dataList.get("name").put("value", account.getName());
		}
		if (!account.getNameKana().isEmpty()) {
			paramBuilder.append(", name_kana=?");
			dataList.put("name_kana", new HashMap<String, Object>());
			dataList.get("name_kana").put("type", "String");
			dataList.get("name_kana").put("value", account.getNameKana());
		}
		if (!account.getMail().isEmpty()) {
			paramBuilder.append(", mail=?");
			dataList.put("mail", new HashMap<String, Object>());
			dataList.get("mail").put("type", "String");
			dataList.get("mail").put("value", account.getMail());
		}
		if (!account.getPassWord().isEmpty()) {
			paramBuilder.append(", password=?");
			dataList.put("password", new HashMap<String, Object>());
			dataList.get("password").put("type", "String");
			dataList.get("password").put("value", account.getPassWord());
		}
		if (!account.getZipCode().isEmpty()) {
			paramBuilder.append(", zip_code=?");
			dataList.put("zip_code", new HashMap<String, Object>());
			dataList.get("zip_code").put("type", "String");
			dataList.get("zip_code").put("value", account.getZipCode());
		}
		if (!account.getAddress().isEmpty()) {
			paramBuilder.append(", address=?");
			dataList.put("address", new HashMap<String, Object>());
			dataList.get("address").put("type", "String");
			dataList.get("address").put("value", account.getAddress());
		}
		if (!account.getPhoneNumber().isEmpty()) {
			paramBuilder.append(", phone_number=?");
			dataList.put("phone_number", new HashMap<String, Object>());
			dataList.get("phone_number").put("type", "String");
			dataList.get("phone_number").put("value", account.getPhoneNumber());
		}
		if (account.getDelFlg()) {
			paramBuilder.append(", del_flg=?");
			dataList.put("del_flg", new HashMap<String, Object>());
			dataList.get("del_flg").put("type", "boolean");
			dataList.get("del_flg").put("value", account.getDelFlg());
		}
		if (!account.getToken().isEmpty()) {
			paramBuilder.append(", token=?");
			dataList.put("token", new HashMap<String, Object>());
			dataList.get("token").put("type", "String");
			dataList.get("token").put("value", account.getToken());
		}
		boolean isDate = true;
		try {
			account.getTokenExpireTime().getTime();
		} catch (Exception e) {
			isDate = false;
		}
 		if (isDate) {
			paramBuilder.append(", token_expire_time=?");
			dataList.put("token_expire_time", new HashMap<String, Object>());
			dataList.get("token_expire_time").put("type", "DateTime");
			dataList.get("token_expire_time").put("value", account.getTokenExpireTime());
		}
		if (!account.getSalt().isEmpty()) {
			paramBuilder.append(", salt=?");
			dataList.put("salt", new HashMap<String, Object>());
			dataList.get("salt").put("type", "String");
			dataList.get("salt").put("value", account.getSalt());
		}
		if (account.getId() != ConfigConstants.NULL_INT) {
			paramBuilder.append(" where id=?");
			dataList.put("id", new HashMap<String, Object>());
			dataList.get("id").put("type", "int");
			dataList.get("id").put("value", account.getId());
		}

		// 不要な,の除去、where句の追加
		if (paramBuilder.length() != 0) {
			String param = paramBuilder.toString();
			int deleteIndex = param.indexOf(",");
			param = param.substring(deleteIndex+",".length());
			sqlBuilder.append(param);
		}
		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		Iterator iterator = dataList.keySet().iterator();
		int count = 1;
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			String type = (String)dataList.get(key).get("type");
			if ("int".equals(type)) {
				statement.setInt(count, (Integer)dataList.get(key).get("value"));
			} else if ("String".equals(type)) {
				statement.setString(count, (String)dataList.get(key).get("value"));
			} else if ("boolean".equals(type)) {
				statement.setBoolean(count, (Boolean)dataList.get(key).get("value"));
			} else if ("DateTime".equals(type)) {
				java.util.Date d = (Date)dataList.get(key).get("value");
				java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
				statement.setTimestamp(count, ts);
			}
			count++;
		}
		int updateCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();
		return account.getId();
	}
}