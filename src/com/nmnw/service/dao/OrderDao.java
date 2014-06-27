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

import com.nmnw.service.dao.Order;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.DdConnector;

public class OrderDao {
	private static final String TABLE_NAME_ORDER = "sales_order";
	private static final String TABLE_NAME_ORDER_DETAIL = "sales_order_detail";

	/**
	 * select(order_id�w��)
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
	 * ��������(orderPeriod�w��)
	 * @param accountId
	 * @param orderPeriod
	 * @return List<Order>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Order> selectByAccountIdAndOrderPeriod(int accountId, String orderPeriod)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		// �������ԏ����L�����f�t���O
		Boolean hasOrderPeriod = false;
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME_ORDER + " where account_id = ?");
		// �������ԏ���������ꍇ
		if (orderPeriod != null && orderPeriod.length() > 0) {
			sqlBuilder.append(" and order_time > ?");
			hasOrderPeriod = true;
		}
		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		// �������ԏ���������ꍇ
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
	 * ��������
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
	 * �����L�����Z��
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