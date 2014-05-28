package com.nmnw.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
	private static final String TABLE_NAME = "item";

	private static Connection getConnection()
			throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName (driver);

		String server   = "localhost";
		String dbname   = "nmnw";
		String url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String password = "";
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		return connection;
	}

	public List<Item> selectAll()
			throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String sql = "select * from " + TABLE_NAME + " order by id";
		PreparedStatement statement = connection.prepareStatement(sql);
		List<Item> resultList = new ArrayList<Item>();
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getInt("category"));
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

	public List<Item> selectByItemId(int id)
			throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		List<Item> resultList = new ArrayList<Item>();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getInt("category"));
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

	public List<Item> selectBySearchInfo(Item searchInfo)
			throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		String like = "";
		
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();
		List<Item> resultList = new ArrayList<Item>();
		while (result.next()) {
			Item item = new Item();
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getInt("price"));
			item.setCategory(result.getInt("category"));
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
		Connection connection = getConnection();
		String sql = "insert into " + TABLE_NAME + " (name, price, category, image_url, explanation, sales_period_from, sales_period_to, stock) values (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, item.getName());
		statement.setInt(2, item.getPrice());
		statement.setInt(3, item.getCategory());
		statement.setString(4, "");
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
			Connection connection = getConnection();
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
			statement.setInt(3, item.getCategory());
			statement.setString(4, "");
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