package com.nmnw.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.DdConnector;

public class OrderDetailDao {
	private static final String TABLE_NAME_ORDER_DETAIL = "sales_order_detail";

	/**
	 * select(order_idéwíË)
	 * @param orderId
	 * @return List
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OrderDetail> selectByOrderId(int orderId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "select * from " + TABLE_NAME_ORDER_DETAIL + " where order_id = ?";
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

	/**
	 * íçï∂è⁄ç◊ÇÃinsert
	 * @param orderId
	 * @param itemList
	 * @return successFlg
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insertItemList (int orderId, List<Cart.CartItem> itemList)
			throws ClassNotFoundException, SQLException {
		boolean successFlg = true;
		try {
			for (int i = 0; i < itemList.size(); i++) {
				insert(orderId, i+1, itemList.get(i));
			}
		} catch (Exception e) {
			successFlg = false;
		}
		return successFlg;
	}

	/**
	 * insert
	 * @param orderId
	 * @param orderDetailId
	 * @param item
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void insert (int orderId, int orderDetailId, Cart.CartItem item)
		throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String sql = "insert into " + TABLE_NAME_ORDER_DETAIL
				+ " (order_id, order_detail_id, item_id, "
				+ "item_name, item_price, item_count) values "
				+ "(?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		statement.setInt(2, orderDetailId);
		statement.setInt(3, item.getItemId());
		statement.setString(4, item.getItemName());
		statement.setInt(5, item.getItemPrice());
		statement.setInt(6, item.getItemCount());
		int insertCount = statement.executeUpdate();
		statement.close();
		connection.commit();
	}
}