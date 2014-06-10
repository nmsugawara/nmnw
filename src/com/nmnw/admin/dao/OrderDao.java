package com.nmnw.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DdConnector;

public class OrderDao {
	private static final String TABLE_NAME = "sales_order";

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
		String sql = "select * from " + TABLE_NAME + " where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		ResultSet result = statement.executeQuery();
		Order order = new Order();
		while (result.next()) {
			order.setOrderId(result.getInt("order_id"));
			order.setOrderTime(result.getString("order_time"));
			order.setAccountId(result.getInt("account_id"));
			order.setAccountName(result.getString("account_name"));
			order.setAccountNameKana(result.getString("account_name_kana"));
			order.setAccountMail(result.getString("account_mail"));
			order.setAccountZipCode(result.getString("account_zip_code"));
			order.setAccountAddress(result.getString("account_address"));
			order.setAccountPhoneNumber(result.getString("account_phone_number"));
			order.setTotalPrice(result.getInt("total_price"));
			order.setCancelFlg(result.getInt("cancel_flg"));
			order.setCancelTime(result.getString("cancel_time"));
			order.setShippingFlg(result.getInt("shipping_flg"));
			order.setShippingTime(result.getString("shipping_time"));
		}
		result.close();
		statement.close();
		connection.close();
		return order;
	}

	/**
	 * select（検索機能）
	 * @param searchParamList
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Order> selectBySearch(Map<String, Map<String, String>> searchParameterList)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from " + TABLE_NAME);
		StringBuilder whereBuilder = new StringBuilder();
		if (searchParameterList.get("order_id") != null) {
			whereBuilder.append(" and order_id = ?");
		}
		if (searchParameterList.get("order_date_from") != null) {
			if (searchParameterList.get("order_date_to") != null) {
				// from & to
				whereBuilder.append(" and Date(order_time) <= ? and Date(order_time) >= ?");
			} else {
				// from
				whereBuilder.append(" and Date(order_time) >= ?");
			}
		} else {
			if (searchParameterList.get("order_date_to") != null) {
				// to
				whereBuilder.append(" and Date(order_time) <= ?");
			} else {
				// none
			}
		}
		if (searchParameterList.get("account_name") != null) {
			whereBuilder.append(" and account_name like ?");
			searchParameterList.get("account_name").put("value", "%" + searchParameterList.get("account_name").get("value") + "%");
		}
		if (searchParameterList.get("account_name_kana") != null) {
			whereBuilder.append(" and account_name_kana like ?");
			searchParameterList.get("account_name_kana").put("value", "%" + searchParameterList.get("account_name_kana").get("value") + "%");
		}
		if (searchParameterList.get("account_mail") != null) {
			whereBuilder.append(" and account_mail like ?");
			searchParameterList.get("account_mail").put("value", "%" + searchParameterList.get("account_mail").get("value") + "%");
		}
		if (searchParameterList.get("account_phone_number") != null) {
			whereBuilder.append(" and account_phone_number like ?");
			searchParameterList.get("account_phone_number").put("value", "%" + searchParameterList.get("account_phone_number").get("value") + "%");
		}
		if (searchParameterList.get("cancel_flg") != null) {
			whereBuilder.append(" and cancel_flg = ?");
		}
		if (searchParameterList.get("cancel_date") != null) {
			whereBuilder.append(" and DATE(cancel_time) = ?");
		}
		if (searchParameterList.get("shipping_flg") != null) {
			whereBuilder.append(" and shipping_flg = ?");
		}
		if (searchParameterList.get("shipping_date") != null) {
			whereBuilder.append(" and DATE(shipping_time) = ?");
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
		Iterator iterator = searchParameterList.keySet().iterator();
		int count = 1;
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			String type = (String)searchParameterList.get(key).get("type");
			if ("int".equals(type)) {
				statement.setInt(count, Integer.parseInt(searchParameterList.get(key).get("value")));
			} else if ("String".equals(type)) {
				statement.setString(count, searchParameterList.get(key).get("value"));
			}
			count++;
		}
		ResultSet result = statement.executeQuery();
		List<Order> resultList = new ArrayList<Order>();
		while (result.next()) {
			Order order = new Order();
			order.setOrderId(result.getInt("order_id"));
			order.setOrderTime(result.getString("order_time"));
			order.setAccountId(result.getInt("account_id"));
			order.setAccountName(result.getString("account_name"));
			order.setAccountNameKana(result.getString("account_name_kana"));
			order.setAccountMail(result.getString("account_mail"));
			order.setAccountZipCode(result.getString("account_zip_code"));
			order.setAccountAddress(result.getString("account_address"));
			order.setAccountPhoneNumber(result.getString("account_phone_number"));
			order.setTotalPrice(result.getInt("total_price"));
			order.setCancelFlg(result.getInt("cancel_flg"));
			order.setCancelTime(result.getString("cancel_time"));
			order.setShippingFlg(result.getInt("shipping_flg"));
			order.setShippingTime(result.getString("shipping_time"));
			resultList.add(order);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}

	/**
	 * update(出荷情報)
	 * @param orderId
	 * @return id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateShippingStatus (int OrderId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "update " + TABLE_NAME + " set"
					+ " shipping_flg=?"
					+ ", shipping_time=?"
					+ " where order_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		String shippingTime = DateConversionUtility.getCurrentDateString();
		statement.setInt(1, 1);
		statement.setString(2, shippingTime);
		statement.setInt(3, OrderId);
		int updateCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();
		return OrderId;
	}
}