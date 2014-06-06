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

public class ItemDao {
	private static final String TABLE_NAME = "item";

	/**
	 * select(item_id指定)
	 * @param id
	 * @return Item
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Item selectByItemId(int id)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		Item item = new Item();
		while (result.next()) {
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getString("category"));
			item.setImageUrl(result.getString("image_url"));
			item.setExplanation(result.getString("explanation"));
			item.setSalesPeriodFrom(result.getString("sales_period_from"));
			item.setSalesPeriodTo(result.getString("sales_period_to"));
			item.setStock(result.getInt("stock"));
		}
		result.close();
		statement.close();
		connection.close();
		return item;
	}

	/**
	 * select（検索機能）
	 * @param id
	 * @param name
	 * @param category
	 * @param from
	 * @param to
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Item> selectBySearch(int id, String name, String category, String from, String to)
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
		if (category != null && category.length() > 0) {
			// not "default"
			if (!("0".equals(category))) {
				whereBuilder.append(" and category = ?");
				likeList.put("category", new ArrayList<Object>());
				likeList.get("category").add("String");
				likeList.get("category").add(category);
			}
		}
		if (from != null && from.length() > 0) {
			if (to != null && to.length() > 0) {
				// from & to
				whereBuilder.append(" and sales_period_from <= ? and sales_period_to >= ?");
				likeList.put("to", new ArrayList<Object>());
				likeList.get("to").add("String");
				likeList.get("to").add(to);
				likeList.put("from", new ArrayList<Object>());
				likeList.get("from").add("String");
				likeList.get("from").add(from);
			} else {
				// from
				whereBuilder.append(" and sales_period_to >= ?");
				likeList.put("from", new ArrayList<Object>());
				likeList.get("from").add("String");
				likeList.get("from").add(from);
			}
		} else {
			if (to != null && to.length() > 0) {
				// to
				whereBuilder.append(" and sales_period_from <= ?");
				likeList.put("to", new ArrayList<Object>());
				likeList.get("to").add("String");
				likeList.get("to").add(to);
			} else {
				// none
			}
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
		List<Item> resultList = new ArrayList<Item>();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getString("category"));
			item.setImageUrl(result.getString("image_url"));
			item.setSalesPeriodFrom(result.getString("sales_period_from"));
			item.setSalesPeriodTo(result.getString("sales_period_to"));
			item.setStock(result.getInt("stock"));
			resultList.add(item);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}

	/**
	 * insert
	 * @param item
	 * @return auto_increment_id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insert (Item item)
		throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "insert into " + TABLE_NAME + " (name, price, category, image_url, explanation, sales_period_from, sales_period_to, stock) values (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, item.getName());
		statement.setInt(2, item.getPrice());
		statement.setString(3, item.getCategory());
		statement.setString(4, item.getImageUrl());
		statement.setString(5, item.getExplanation());
		statement.setString(6, item.getSalesPeriodFrom());
		statement.setString(7, item.getSalesPeriodTo());
		statement.setInt(8, item.getStock());
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
	 * @param item
	 * @return id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update (Item item)
			throws ClassNotFoundException, SQLException {
			Connection connection = DdConnector.getConnection();
			String sql = "update " + TABLE_NAME + " set"
						+ " name=?"
						+ ", price=?"
						+ ", category=?"
						+ ", image_url=?"
						+ ", explanation=?"
						+ ", sales_period_from=?"
						+ ", sales_period_to=?"
						+ ", stock=?"
						+ " where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getPrice());
			statement.setString(3, item.getCategory());
			statement.setString(4, item.getImageUrl());
			statement.setString(5, item.getExplanation());
			statement.setString(6, item.getSalesPeriodFrom());
			statement.setString(7, item.getSalesPeriodTo());
			statement.setInt(8, item.getStock());
			statement.setInt(9, item.getId());
			int updateCount = statement.executeUpdate();
			statement.close();
			connection.commit();
			connection.close();
			return item.getId();
	}
}