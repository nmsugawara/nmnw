package com.nmnw.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DdConnector;

public class PossibilityOfCancelReportDao {
	private static final String TABLE_NAME_ORDER = "sales_order";

	/**
	 * 未発送or発送後（引数）日間以内の注文情報、過去「発送後キャンセル有無」を取得
	 * @param withinDays
	 * @return List<Map<String, String>>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, String>> selectUnShippingOrShippingWithinRecentDays(int withinDays)
			throws ClassNotFoundException, SQLException {
		Connection connection = DdConnector.getConnection();
		String withinDate = DateConversionUtility.getdaysAgoString(withinDays);
		String sql = "select so1.order_id, so1.order_time, so1.total_price, so1.account_id,"
				+ " exists (select * from " + TABLE_NAME_ORDER + " as so2 where so1.account_id = so2.account_id and so2.cancel_flg = true and so2.shipping_flg = true) as cancel_experience"
				+ " from " + TABLE_NAME_ORDER + " as so1"
				+ " where so1.cancel_flg = false"
				+ " and (so1.shipping_flg = false or (so1.shipping_flg = true and so1.shipping_time >= ?))"
				+ " order by so1.order_id desc";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, withinDate);
		ResultSet result = statement.executeQuery();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		while (result.next()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("order_id", String.valueOf(result.getInt("order_id")));
			map.put("order_time", DateConversionUtility.dateTimeToString(result.getTimestamp("order_time")));
			map.put("total_price", String.valueOf(result.getInt("total_price")));
			map.put("account_id", String.valueOf(result.getInt("account_id")));
			map.put("cancel_experience", result.getString("cancel_experience"));
			resultList.add(map);
		}
		result.close();
		statement.close();
		connection.close();
		return resultList;
	}
}