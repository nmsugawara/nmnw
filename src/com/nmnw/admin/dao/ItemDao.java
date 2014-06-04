package com.nmnw.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.utility.DdConnector;

public class ItemDao {
	private static final String TABLE_NAME = "item";

	public List<Item> selectAll()
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " order by id";
		PreparedStatement statement = connection.prepareStatement(sql);
		List<Item> resultList = new ArrayList<Item>();
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getString("category"));
			item.setImageUrl(result.getString("image_url"));
			item.setExplanation(result.getString("explanation"));
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
			item.setImageUrl(ConfigConstants.IMAGE_DIR_ITEM + result.getString("image_url"));
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

	public List<Item> selectBySearch(int id, String name, String category, String from, String to)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME + " where 1");

		Map likeList = new HashMap();
		int count = 1;
		if (id != ConfigConstants.NULL_INT) {
			sqlBuilder.append(" and id = ?");
			likeList.put(count, id);
		}
		if (name != null && name.length() > 0) {
			sqlBuilder.append(" and name like ?");
			likeList.put(count, name);
		}
		if (category != null && category.length() > 0) {
			// not "default"
			if (!("0".equals(category))) {
				sqlBuilder.append(" and category = ?");
				likeList.put(count, category);
				count++;
			}
		}
		if (from != null && from.length() > 0) {
			if (to != null && to.length() > 0) {
				// from & to
				sqlBuilder.append(" and sales_period_from <= ? and sales_period_to >= ?");
				statement.setString(count, to);
				count++;
				statement.setString(count, from);
				count++;
			} else {
				// from
				sqlBuilder.append(" and sales_period_to >= ?");
				statement.setString(count, from);
				count++;
			}
		} else {
			if (to != null && to.length() > 0) {
				// to
				sqlBuilder.append(" and sales_period_from <= ?");
				statement.setString(count, to);
				count++;
			} else {
				// none
			}
		}
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, likeSql.toString());

		ResultSet result = statement.executeQuery();
		List<Item> resultList = new ArrayList<Item>();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getString("category"));
			item.setImageUrl(result.getString("image_url"));
			item.setExplanation(result.getString("explanation"));
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