package com.nmnw.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.utility.DdConnector;

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
			account.setDelFlg(result.getInt("del_flg"));
			account.setToken(result.getString("token"));
		}
		result.close();
		statement.close();
		connection.close();
		return account;
	}

	/**
	 * select（検索機能）
	 * @param id
	 * @param name
	 * @param nameKana
	 * @param mail
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Account> selectBySearch(int id, String name, String nameKana, String mail)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME);

		StringBuilder whereBuilder = new StringBuilder();
		Map<String, List<Object>> likeList = new LinkedHashMap<String, List<Object>>();
		if (id != ConfigConstants.NULL_INT) {
			whereBuilder.append(" and id = ?");
			likeList.put("id", new ArrayList<Object>());
			likeList.get("id").add("int");
			likeList.get("id").add(id);
		}
		if (name != null && name.length() > 0) {
			whereBuilder.append(" and name like ?");
			likeList.put("name", new ArrayList<Object>());
			likeList.get("name").add("String");
			likeList.get("name").add("%" + name + "%");
		}
		if (nameKana != null && nameKana.length() > 0) {
			whereBuilder.append(" and name_kana like ?");
			likeList.put("nameKana", new ArrayList<Object>());
			likeList.get("nameKana").add("String");
			likeList.get("nameKana").add("%" + nameKana + "%");
		}
		if (mail != null && mail.length() > 0) {
			whereBuilder.append(" and mail like ?");
			likeList.put("mail", new ArrayList<Object>());
			likeList.get("mail").add("String");
			likeList.get("mail").add("%" + mail + "%");
		}
		// 不要なandの除去、where句の追加
		if (whereBuilder.length() != 0) {
			String where = whereBuilder.toString();
			int deleteIndex = where.indexOf("and");
			where = where.substring(deleteIndex+"and".length());
			sqlBuilder.append(" where" + where);
		}

		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		Iterator iterator = likeList.keySet().iterator();
		int count = 1;
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			String type = (String)likeList.get(key).get(0);
			if ("int".equals(type)) {
				statement.setInt(count, (Integer)likeList.get(key).get(1));
			} else if ("String".equals(type)) {
				statement.setString(count, (String)likeList.get(key).get(1));
			}
			count++;
		}
		ResultSet result = statement.executeQuery();
		List<Account> resultList = new ArrayList<Account>();
		while (result.next()) {
			Account account = new Account();
			account.setId(result.getInt("id"));
			account.setName(result.getString("name"));
			account.setNameKana(result.getString("name_kana"));
			account.setMail(result.getString("mail"));
			account.setPassWord(result.getString("password"));
			account.setZipCode(result.getString("zip_code"));
			account.setAddress(result.getString("address"));
			account.setPhoneNumber(result.getString("phone_number"));
			account.setDelFlg(result.getInt("del_flg"));
			account.setToken(result.getString("token"));
			resultList.add(account);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}
}