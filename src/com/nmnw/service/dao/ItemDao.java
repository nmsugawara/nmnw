package com.nmnw.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nmnw.service.dao.Item;
import com.nmnw.service.utility.DbConnector;

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
		Connection connection = DbConnector.getConnection();
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
			item.setSalesPeriodFrom(result.getDate("sales_period_from"));
			item.setSalesPeriodTo(result.getDate("sales_period_to"));
			item.setStock(result.getInt("stock"));
		}
		result.close();
		statement.close();
		connection.close();
		return item;
	}

	/**
	 * select（検索機能）
	 * @param name
	 * @param category
	 * @param from
	 * @param to
	 * @param sort
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Item> selectBySearch(String name, String category, String from, String to, String sort)
			throws ClassNotFoundException, SQLException {
		Connection connection = DbConnector.getConnection();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME);

		StringBuilder whereBuilder = new StringBuilder();
		Map<String, List<Object>> likeList = new LinkedHashMap<String, List<Object>>();
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
		if (sort != null && sort.length() > 0) {
			// not "default"
			if (!("0".equals(sort))) {
				sqlBuilder.append(" order by " + sort);
			}
		}

		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		Iterator iterator = likeList.keySet().iterator();
		int count = 1;
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			String type = (String)likeList.get(key).get(0);
			if ("String".equals(type)) {
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
			item.setSalesPeriodFrom(result.getDate("sales_period_from"));
			item.setSalesPeriodTo(result.getDate("sales_period_to"));
			item.setStock(result.getInt("stock"));
			resultList.add(item);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}
}