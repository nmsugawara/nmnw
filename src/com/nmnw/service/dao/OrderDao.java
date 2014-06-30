package com.nmnw.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nmnw.admin.dao.Item;
import com.nmnw.service.dao.Order;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.DdConnector;

public class OrderDao {
	private static final String TABLE_NAME_ORDER = "sales_order";
	private static final String TABLE_NAME_ORDER_DETAIL = "sales_order_detail";

	/**
	 * select(order_id指定)
	 * @param orderId
	 * @return Order
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Order selectByOrderId(int orderId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME_ORDER + " where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		ResultSet result = statement.executeQuery();
		Order order = new Order();
		while (result.next()) {
			order.setOrderId(result.getInt("order_id"));
			order.setOrderTime(result.getTimestamp("order_time"));
			order.setAccountId(result.getInt("account_id"));
			order.setAccountName(result.getString("account_name"));
			order.setAccountNameKana(result.getString("account_name_kana"));
			order.setAccountMail(result.getString("account_mail"));
			order.setAccountZipCode(result.getString("account_zip_code"));
			order.setAccountAddress(result.getString("account_address"));
			order.setAccountPhoneNumber(result.getString("account_phone_number"));
			order.setTotalPrice(result.getInt("total_price"));
			order.setCancelFlg(result.getBoolean("cancel_flg"));
			order.setCancelTime(result.getTimestamp("cancel_time"));
			order.setShippingFlg(result.getBoolean("shipping_flg"));
			order.setShippingTime(result.getTimestamp("shipping_time"));
		}
		result.close();
		statement.close();
		connection.close();
		return order;
	}

	/**
	 * 注文検索(orderPeriod指定)
	 * @param accountId
	 * @param orderPeriod
	 * @return List<Order>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Order> selectByAccountIdAndOrderPeriod(int accountId, String orderPeriod)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		// 注文期間条件有無判断フラグ
		Boolean hasOrderPeriod = false;
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME_ORDER + " where account_id = ?");
		// 注文期間条件がある場合
		if (orderPeriod != null && orderPeriod.length() > 0) {
			sqlBuilder.append(" and order_time > ?");
			hasOrderPeriod = true;
		}
		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		// 注文期間条件がある場合
		if (hasOrderPeriod) {
			statement.setString(2, orderPeriod);
		}
		ResultSet result = statement.executeQuery();
		List<Order> resultList = new ArrayList<Order>();
		while (result.next()) {
			Order order = new Order();
			order.setOrderId(result.getInt("order_id"));
			order.setOrderTime(DateConversionUtility.timestampToDate(result.getTimestamp("order_time")));
			order.setAccountId(result.getInt("account_id"));
			order.setAccountName(result.getString("account_name"));
			order.setAccountNameKana(result.getString("account_name_kana"));
			order.setAccountMail(result.getString("account_mail"));
			order.setAccountZipCode(result.getString("account_zip_code"));
			order.setAccountAddress(result.getString("account_address"));
			order.setAccountPhoneNumber(result.getString("account_phone_number"));
			order.setTotalPrice(result.getInt("total_price"));
			order.setCancelFlg(result.getBoolean("cancel_flg"));
			order.setCancelTime(DateConversionUtility.timestampToDate(result.getTimestamp("cancel_time")));
			order.setShippingFlg(result.getBoolean("shipping_flg"));
			order.setShippingTime(DateConversionUtility.timestampToDate(result.getTimestamp("shipping_time")));
 			resultList.add(order);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}

	/**
	 * 注文検索
	 * @param orderId
	 * @param accountId
	 * @return List<Order>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Order selectByOrderIdAndAccountId(int orderId, int accountId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME_ORDER + " where order_id = ? and account_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		statement.setInt(2, accountId);
		ResultSet result = statement.executeQuery();
		Order order = new Order();
		while (result.next()) {
			order.setOrderId(result.getInt("order_id"));
			order.setOrderTime(DateConversionUtility.timestampToDate(result.getTimestamp("order_time")));
			order.setAccountId(result.getInt("account_id"));
			order.setAccountName(result.getString("account_name"));
			order.setAccountNameKana(result.getString("account_name_kana"));
			order.setAccountMail(result.getString("account_mail"));
			order.setAccountZipCode(result.getString("account_zip_code"));
			order.setAccountAddress(result.getString("account_address"));
			order.setAccountPhoneNumber(result.getString("account_phone_number"));
			order.setTotalPrice(result.getInt("total_price"));
			order.setCancelFlg(result.getBoolean("cancel_flg"));
			order.setCancelTime(DateConversionUtility.timestampToDate(result.getTimestamp("cancel_time")));
			order.setShippingFlg(result.getBoolean("shipping_flg"));
			order.setShippingTime(DateConversionUtility.timestampToDate(result.getTimestamp("shipping_time")));
		}
		result.close();
		statement.close();
		connection.close();
		return order;
	}

	/**
	 * insert
	 * @param accout
	 * @param totalPrice
	 * @return auto_increment_id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insert (Account account, int totalPrice)
		throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "insert into " + TABLE_NAME_ORDER
				+ " (order_time, account_id, account_name, "
				+ "account_name_kana, account_mail, account_zip_code, "
				+ "account_address, account_phone_number, total_price) values "
				+ "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		String orderTime = DateConversionUtility.getCurrentDateString();
		statement.setTimestamp(1, java.sql.Timestamp.valueOf(orderTime));
		statement.setInt(2, account.getId());
		statement.setString(3, account.getName());
		statement.setString(4, account.getNameKana());
		statement.setString(5, account.getMail());	
		statement.setString(6, account.getZipCode());
		statement.setString(7, account.getAddress());
		statement.setString(8, account.getPhoneNumber());
		statement.setInt(9, totalPrice);
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
	 * 注文キャンセル
	 * @param orderId
	 * @return id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateCancelStatus (int OrderId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "update " + TABLE_NAME_ORDER + " set"
					+ " cancel_flg=?"
					+ ", cancel_time=?"
					+ " where order_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		String cancelTime = DateConversionUtility.getCurrentDateString();
		statement.setInt(1, 1);
		statement.setTimestamp(2, java.sql.Timestamp.valueOf(cancelTime));
		statement.setInt(3, OrderId);
		int updateCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();
		return updateCount;
	}
}