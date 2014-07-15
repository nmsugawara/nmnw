package com.nmnw.admin.dao;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nmnw.admin.utility.DbConnector;

public class MonthlySummaryReportDao {
	private static final String TABLE_NAME_ORDER = "sales_order";
	private static final String TABLE_NAME_ORDER_DETAIL = "sales_order_detail";

	public Map<String, String> selectSummaryDataByOrderPeriod(String from, String to)
			throws ClassNotFoundException, SQLException {
		Connection connection = DbConnector.getConnection();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select count(order_id) as order_count,"
				+ " sum(case when shipping_flg = false and cancel_flg = true then 1 else 0 end) as cancel_count,"
				+ " sum(case when shipping_flg = true and cancel_flg = false then 1 else 0 end) as shipping_count,"
				+ " sum(case when shipping_flg = false and cancel_flg = true then 1 else 0 end) / count(order_id) * 100 as cancel_rate,"
				+ " sum(case when shipping_flg = true and cancel_flg = false then total_price else 0 end) as sales,"
				+ " sum(case when shipping_flg = true and cancel_flg = false then (select sum(sod.item_count) from " + TABLE_NAME_ORDER_DETAIL + " as sod where sod.order_id = so.order_id) else 0 end) as sales_item_count,"
				+ " sum(case when shipping_flg = true and cancel_flg = false then 1 else 0 end) / count(order_id) * 100 as shipping_rate,"
				+ " sum(case when shipping_flg = true and cancel_flg = true then 1 else 0 end) as cancel_count_after_shipping,"
				+ " sum(case when shipping_flg = true and cancel_flg = true then 1 else 0 end) / sum(case when shipping_flg = true and cancel_flg = false then 1 else 0 end) * 100 as cancel_rate_after_shipping"
				+ " from " + TABLE_NAME_ORDER + " as so");
		if (from != null && from.length() > 0 && to != null && to.length() > 0) {
			sqlBuilder.append(" where order_time between ? and ?");
		}
		String sql = sqlBuilder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		if (from != null && from.length() > 0 && to != null && to.length() > 0) {
			statement.setString(1, from);
			statement.setString(2, to);
		}
		ResultSet result = statement.executeQuery();
		Map<String, String> resultList = new HashMap<String, String>();
		while (result.next()) {
			resultList.put("order_count", String.valueOf(result.getInt("order_count")));
			resultList.put("cancel_count", String.valueOf(result.getInt("cancel_count")));
			resultList.put("shipping_count", String.valueOf(result.getInt("shipping_count")));
			resultList.put("cancel_rate", String.format("%.2f", result.getFloat("cancel_rate")));
			resultList.put("sales", String.valueOf(result.getInt("sales")));
			resultList.put("sales_item_count", String.valueOf(result.getInt("sales_item_count")));
			resultList.put("shipping_rate", String.format("%.2f", result.getFloat("shipping_rate")));
			resultList.put("cancel_count_after_shipping", String.valueOf(result.getInt("cancel_count_after_shipping")));
			resultList.put("cancel_rate_after_shipping", String.format("%.2f", result.getFloat("cancel_rate_after_shipping")));
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}
}