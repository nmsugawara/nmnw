package com.nmnw.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nmnw.admin.utility.DbConnector;

public class OrderDetailDao {
	private static final String TABLE_NAME = "sales_order_detail";

	/**
	 * select(order_idŽw’è)
	 * @param orderId
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OrderDetail> selectByOrderId(int orderId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DbConnector.getConnection();
		String sql = "select * from " + TABLE_NAME + " where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		ResultSet result = statement.executeQuery();
		List<OrderDetail> resultList = new ArrayList<OrderDetail>();
		while (result.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(result.getInt("order_id"));
			orderDetail.setOrderDetailId(result.getInt("order_detail_id"));
			orderDetail.setItemId(result.getInt("item_id"));
			orderDetail.setItemName(result.getString("item_name"));
			orderDetail.setItemPrice(result.getInt("item_price"));
			orderDetail.setItemCount(result.getInt("item_count"));
			resultList.add(orderDetail);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}
}