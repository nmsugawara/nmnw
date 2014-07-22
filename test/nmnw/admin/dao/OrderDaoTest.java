package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.nmnw.admin.dao.Account;
import com.nmnw.admin.dao.AccountDao;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class OrderDaoTest {

	@Test
	public void selectByOrderIdTest() throws Exception{
		/**
		 * 存在する注文ID
		 */
		// 初期化
		int orderIdExist = 10;
		OrderDao orderDao = new OrderDao();

		Connection connection = DbConnector.getConnection();
		String sql = "select * from sales_order where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderIdExist);
		ResultSet result = statement.executeQuery();
		Order expected = new Order();
		while (result.next()) {
			expected.setOrderId(result.getInt("order_id"));
			expected.setOrderTime(result.getTimestamp("order_time"));
			expected.setAccountId(result.getInt("account_id"));
			expected.setAccountName(result.getString("account_name"));
			expected.setAccountNameKana(result.getString("account_name_kana"));
			expected.setAccountMail(result.getString("account_mail"));
			expected.setAccountZipCode(result.getString("account_zip_code"));
			expected.setAccountAddress(result.getString("account_address"));
			expected.setAccountPhoneNumber(result.getString("account_phone_number"));
			expected.setTotalPrice(result.getInt("total_price"));
			expected.setCancelFlg(result.getBoolean("cancel_flg"));
			expected.setCancelTime(result.getTimestamp("cancel_time"));
			expected.setShippingFlg(result.getBoolean("shipping_flg"));
			expected.setShippingTime(result.getTimestamp("shipping_time"));
		}
		// 実行
		Order actual = orderDao.selectByOrderId(orderIdExist);
		// 検証
		assertThat("selectByOrderId:orderId", actual.getOrderId(), is(expected.getOrderId()));
		assertThat("selectByOrderId:rderTime", actual.getOrderTime(), is(expected.getOrderTime()));
		assertThat("selectByOrderId:accountId", actual.getAccountId(), is(expected.getAccountId()));
		assertThat("selectByOrderId:accountName", actual.getAccountName(), is(expected.getAccountName()));
		assertThat("selectByOrderId:accountNamekana", actual.getAccountNameKana(), is(expected.getAccountNameKana()));
		assertThat("selectByOrderId:accountMail", actual.getAccountMail(), is(expected.getAccountMail()));
		assertThat("selectByOrderId:accountZipCode", actual.getAccountZipCode(), is(expected.getAccountZipCode()));
		assertThat("selectByOrderId:accountAddress", actual.getAccountAddress(), is(expected.getAccountAddress()));
		assertThat("selectByOrderId:accountPhoneNumber", actual.getAccountPhoneNumber(), is(expected.getAccountPhoneNumber()));
		assertThat("selectByOrderId:totalPrice", actual.getTotalPrice(), is(expected.getTotalPrice()));
		assertThat("selectByOrderId:cancelFlg", actual.getCancelFlg(), is(expected.getCancelFlg()));
		assertThat("selectByOrderId:cancelTime", actual.getCancelTime(), is(expected.getCancelTime()));
		assertThat("selectByOrderId:shippingFlg", actual.getShippingFlg(), is(expected.getShippingFlg()));
		assertThat("selectByOrderId:shippingTime", actual.getShippingTime(), is(expected.getShippingTime()));
		// 後処理
		
		/**
		 * 存在しない商品ID
		 */
		// 初期化
		int orderIdNotExist = 9999;
		// 実行
		Order actualNotExist = orderDao.selectByOrderId(orderIdNotExist);
		// 検証
		assertThat("selectByOrderId:orderId", actualNotExist.getOrderId(), is(0));
		assertNull("selectByOrderId:rderTime", actualNotExist.getOrderTime());
		assertThat("selectByOrderId:accountId", actualNotExist.getAccountId(), is(0));
		assertThat("selectByOrderId:accountName", actualNotExist.getAccountName(), is(""));
		assertThat("selectByOrderId:accountNamekana", actualNotExist.getAccountNameKana(), is(""));
		assertThat("selectByOrderId:accountMail", actualNotExist.getAccountMail(), is(""));
		assertThat("selectByOrderId:accountZipCode", actualNotExist.getAccountZipCode(), is(""));
		assertThat("selectByOrderId:accountAddress", actualNotExist.getAccountAddress(), is(""));
		assertThat("selectByOrderId:accountPhoneNumber", actualNotExist.getAccountPhoneNumber(), is(""));
		assertThat("selectByOrderId:totalPrice", actualNotExist.getTotalPrice(), is(0));
		assertThat("selectByOrderId:cancelFlg", actualNotExist.getCancelFlg(), is(false));
		assertNull("selectByOrderId:cancelTime", actualNotExist.getCancelTime());
		assertThat("selectByOrderId:shippingFlg", actualNotExist.getShippingFlg(), is(false));
		assertNull("selectByOrderId:shippingTime", actualNotExist.getShippingTime());
		// 後処理
	}

	@Test
	public void selectBySearchTest() throws Exception{
		/**
		 * 検索条件有
		 */
		// 初期化
		int orderIdExist = 10;
		String orderDateFromExist = "2014-06-01";
		String orderDateToExist = "2014-07-01";
		String accountNameExist = "鈴木";
		String accountNameKanaExist = "スズキ";
		String accountMailExist = "ssugawa";
		String accountPhoneNumberExist = "080-5444-3220";
		String orderCancelDateExist = "2014-07-03";
		String orderShippingDateExist = "2014-07-02";

		Map<String, Map<String, String>> searchParameterList = new LinkedHashMap<String, Map<String, String>>();
		searchParameterList.put("order_id", new HashMap<String, String>());
		searchParameterList.get("order_id").put("type", "int");
		searchParameterList.get("order_id").put("value", String.valueOf(orderIdExist));

		searchParameterList.put("order_date_from", new HashMap<String, String>());
		searchParameterList.get("order_date_from").put("type", "String");
		searchParameterList.get("order_date_from").put("value", orderDateFromExist);

		searchParameterList.put("order_date_to", new HashMap<String, String>());
		searchParameterList.get("order_date_to").put("type", "String");
		searchParameterList.get("order_date_to").put("value", orderDateToExist);

		searchParameterList.put("account_name", new HashMap<String, String>());
		searchParameterList.get("account_name").put("type", "String");
		searchParameterList.get("account_name").put("value", accountNameExist);

		searchParameterList.put("account_name_kana", new HashMap<String, String>());
		searchParameterList.get("account_name_kana").put("type", "String");
		searchParameterList.get("account_name_kana").put("value", accountNameKanaExist);

		searchParameterList.put("account_mail", new HashMap<String, String>());
		searchParameterList.get("account_mail").put("type", "String");
		searchParameterList.get("account_mail").put("value", accountMailExist);

		searchParameterList.put("account_phone_number", new HashMap<String, String>());
		searchParameterList.get("account_phone_number").put("type", "String");
		searchParameterList.get("account_phone_number").put("value", accountPhoneNumberExist);

		searchParameterList.put("cancel_date", new HashMap<String, String>());
		searchParameterList.get("cancel_date").put("type", "String");
		searchParameterList.get("cancel_date").put("value", orderCancelDateExist);

		searchParameterList.put("shipping_date", new HashMap<String, String>());
		searchParameterList.get("shipping_date").put("type", "String");
		searchParameterList.get("shipping_date").put("value", orderShippingDateExist);
		
		OrderDao orderDao = new OrderDao();
		Connection connection = DbConnector.getConnection();
		String sql = "select * from sales_order where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderIdExist);
		ResultSet result = statement.executeQuery();
		Order expected = new Order();
		while (result.next()) {
			expected.setOrderId(result.getInt("order_id"));
			expected.setOrderTime(result.getTimestamp("order_time"));
			expected.setAccountId(result.getInt("account_id"));
			expected.setAccountName(result.getString("account_name"));
			expected.setAccountNameKana(result.getString("account_name_kana"));
			expected.setAccountMail(result.getString("account_mail"));
			expected.setAccountZipCode(result.getString("account_zip_code"));
			expected.setAccountAddress(result.getString("account_address"));
			expected.setAccountPhoneNumber(result.getString("account_phone_number"));
			expected.setTotalPrice(result.getInt("total_price"));
			expected.setCancelFlg(result.getBoolean("cancel_flg"));
			expected.setCancelTime(result.getTimestamp("cancel_time"));
			expected.setShippingFlg(result.getBoolean("shipping_flg"));
			expected.setShippingTime(result.getTimestamp("shipping_time"));
		}

		// 実行
		List<Order> actual = orderDao.selectBySearch(searchParameterList);
		// 検証
		assertThat("selectBySearch:orderId", actual.get(0).getOrderId(), is(expected.getOrderId()));
		assertThat("selectBySearch:orderTime", actual.get(0).getOrderTime(), is(expected.getOrderTime()));
		assertThat("selectBySearch:accountId", actual.get(0).getAccountId(), is(expected.getAccountId()));
		assertThat("selectBySearch:accountName", actual.get(0).getAccountName(), is(expected.getAccountName()));
		assertThat("selectBySearch:accountNamekana", actual.get(0).getAccountNameKana(), is(expected.getAccountNameKana()));
		assertThat("selectBySearch:accountMail", actual.get(0).getAccountMail(), is(expected.getAccountMail()));
		assertThat("selectBySearch:accountZipCode", actual.get(0).getAccountZipCode(), is(expected.getAccountZipCode()));
		assertThat("selectBySearch:accountAddress", actual.get(0).getAccountAddress(), is(expected.getAccountAddress()));
		assertThat("selectBySearch:accountPhoneNumber", actual.get(0).getAccountPhoneNumber(), is(expected.getAccountPhoneNumber()));
		assertThat("selectBySearch:totalPrice", actual.get(0).getTotalPrice(), is(expected.getTotalPrice()));
		assertThat("selectBySearch:cancelFlg", actual.get(0).getCancelFlg(), is(expected.getCancelFlg()));
		assertThat("selectBySearch:cancelTime", actual.get(0).getCancelTime(), is(expected.getCancelTime()));
		assertThat("selectBySearch:shippingFlg", actual.get(0).getShippingFlg(), is(expected.getShippingFlg()));
		assertThat("selectBySearch:shippingTime", actual.get(0).getShippingTime(), is(expected.getShippingTime()));
		// 後処理

		/**
		 * 検索条件無:空文字
		 */
		// 初期化
		Map<String, Map<String, String>> searchParameterListNotExist = new LinkedHashMap<String, Map<String, String>>();

		String sqlNotExist = "select count(*) as count from sales_order";
		PreparedStatement statementNotExist = connection.prepareStatement(sqlNotExist);
		ResultSet resultNotExist = statementNotExist.executeQuery();
		int expectedListCount = 0;
		while (resultNotExist.next()) {
			expectedListCount = resultNotExist.getInt("count");
		}

		// 実行
		List<Order> actualNotExsist = orderDao.selectBySearch(searchParameterListNotExist);
		// 検証
		assertThat("selectBySearch:条件無", actualNotExsist.size(), is(expectedListCount));

		/**
		 * 検索条件有：Fromのみ
		 */
		// 初期化
		String orderDateFromRuleFrom = "2014-06-01";
		
		Map<String, Map<String, String>> searchParameterListRuleFrom = new LinkedHashMap<String, Map<String, String>>();
		searchParameterListRuleFrom.put("order_date_from", new HashMap<String, String>());
		searchParameterListRuleFrom.get("order_date_from").put("type", "String");
		searchParameterListRuleFrom.get("order_date_from").put("value", orderDateFromRuleFrom);

		String sqlRuleFrom = "select count(*) as count from sales_order where Date(order_time) >= ?";
		PreparedStatement statementRuleFrom = connection.prepareStatement(sqlRuleFrom);
		statementRuleFrom.setString(1, orderDateFromRuleFrom);
		ResultSet resultRuleFrom = statementRuleFrom.executeQuery();
		int expectedListCountRuleFrom = 0;
		while (resultRuleFrom.next()) {
			expectedListCountRuleFrom = resultRuleFrom.getInt("count");
		}

		// 実行
		List<Order> actualRuleFrom = orderDao.selectBySearch(searchParameterListRuleFrom);
		// 検証
		assertThat("selectBySearch:条件有:Fromのみ", actualRuleFrom.size(), is(expectedListCountRuleFrom));

		/**
		 * 検索条件有：Toのみ
		 */
		// 初期化
		String orderDateFromRuleTo = "2014-06-01";
		
		Map<String, Map<String, String>> searchParameterListRuleTo = new LinkedHashMap<String, Map<String, String>>();
		searchParameterListRuleTo.put("order_date_to", new HashMap<String, String>());
		searchParameterListRuleTo.get("order_date_to").put("type", "String");
		searchParameterListRuleTo.get("order_date_to").put("value", orderDateFromRuleTo);

		String sqlRuleTo = "select count(*) as count from sales_order where Date(order_time) <= ?";
		PreparedStatement statementRuleTo = connection.prepareStatement(sqlRuleTo);
		statementRuleTo.setString(1, orderDateFromRuleTo);
		ResultSet resultRuleTo = statementRuleTo.executeQuery();
		int expectedListCountRuleTo = 0;
		while (resultRuleTo.next()) {
			expectedListCountRuleTo = resultRuleTo.getInt("count");
		}

		// 実行
		List<Order> actualRuleTo = orderDao.selectBySearch(searchParameterListRuleTo);
		// 検証
		assertThat("selectBySearch:条件有:Toのみ", actualRuleTo.size(), is(expectedListCountRuleTo));

	}

	@Test
	public void updateShippingStatusTest() throws Exception{
		// 初期化
		int orderId = 11;
		OrderDao orderDao = new OrderDao();
		Connection connection = DbConnector.getConnection();
		String sql = "update sales_order set shipping_flg = false, shipping_time = NULL where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		int UpdateCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();

		// 実行
		int resultOrderId = orderDao.updateShippingStatus(orderId);
		Order actual = orderDao.selectByOrderId(orderId);
		// 検証
		assertTrue("updateShippingStatus:shippingFlg", actual.getShippingFlg());
		assertNotNull("updateShippingStatus:shippingTime", actual.getShippingTime());
		// 後処理
	}
}
