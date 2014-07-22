package nmnw.service.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.Order;
import com.nmnw.service.dao.OrderDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.DbConnector;

@RunWith(Theories.class)
public class OrderDaoTest {
	public static final int ORDER_ID = 10;
	public static final int ORDER_ID_NOT_EXIST = 9999;
	public static final int ACCOUNT_ID = 7;
	public static final int ACCOUNT_ID_NOT_EXIST = 9999;
	public static final String ORDER_PERIOD = "2014-07-01";
	public static final String ORDER_PERIOD_NOT_EXIST = "";
	public static final String ORDER_PERIOD_NULL = null;
	public static final String NEW_ORDER_TIME = DateConversionUtility.getCurrentDateString();
	public static final int NEW_ACCOUNT_ID = 11;
	public static final String NEW_ACCOUNT_NAME = "test";
	public static final String NEW_ACCOUNT_NAME_KANA = "test";
	public static final String NEW_ACCOUNT_MAIL = "test";
	public static final String NEW_ACCOUNT_ZIP_CODE = "test";
	public static final String NEW_ACCOUNT_ADDRESS = "test";
	public static final String NEW_ACCOUNT_PHONE_NUMBER = "test";
	public static final int NEW_TOTAL_PRICE = 1;

	@Test
	public void selectByOrderIdTest() throws Exception{
		/**
		 * 存在する注文ID
		 */
		// 初期化
		int orderIdExist = ORDER_ID;
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
		 * 存在しない注文ID
		 */
		// 初期化
		int orderIdNotExist = ORDER_ID_NOT_EXIST;
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
	public void selectByAccountIdAndOrderPeriodTest() throws Exception{
		/**
		 * 期間条件有
		 */
		// 初期化
		int accountId = ACCOUNT_ID;
		String orderPeriod = ORDER_PERIOD;

		Connection connection = DbConnector.getConnection();
		String sql = "select count(*) as count from sales_order where account_id = ? and order_time > ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		statement.setString(2, orderPeriod);
		ResultSet result = statement.executeQuery();
		int expected = 0;
		while (result.next()) {
			expected = result.getInt("count");
		}
		OrderDao orderDao = new OrderDao();
		// 実行
		List<Order> actual = orderDao.selectByAccountIdAndOrderPeriod(accountId, orderPeriod);
		// 検証
		assertThat("selectByAccountIdAndOrderPeriod", actual.size(), is(expected));
		// 後処理

		/**
		 * 期間条件無
		 */
		// 初期化
		String orderPeriodNoPeriod = ORDER_PERIOD_NOT_EXIST;
		String sqlNoPeriod = "select count(*) as count from sales_order where account_id = ?";
		PreparedStatement statementNoPeriod = connection.prepareStatement(sqlNoPeriod);
		statementNoPeriod.setInt(1, accountId);
		ResultSet resultNoPeriod = statementNoPeriod.executeQuery();
		int expectedNoPeriod = 0;
		while (resultNoPeriod.next()) {
			expectedNoPeriod = resultNoPeriod.getInt("count");
		}
	
		// 実行
		List<Order> actualNoPeriod = orderDao.selectByAccountIdAndOrderPeriod(accountId, orderPeriodNoPeriod);
		// 検証
		assertThat("selectByAccountIdAndOrderPeriod:条件無", actualNoPeriod.size(), is(expectedNoPeriod));

		/**
		 * 条件無：null
		 */
		// 初期化
		
		String orderPeriodNull = ORDER_PERIOD_NULL;
		int exceptedNull = expectedNoPeriod;

		// 実行
		List<Order> actualNull = orderDao.selectByAccountIdAndOrderPeriod(accountId, orderPeriodNull);
		// 検証
		assertThat("selectByAccountIdAndOrderPeriod:null", actualNull.size(), is(exceptedNull));
	}

	@Test
	public void selectByOrderIdAndAccountIdTest() throws Exception{
		/**
		 * 存在する注文ID、会員ID
		 */
		// 初期化
		int orderId = ORDER_ID;
		int accountId = ACCOUNT_ID;
		OrderDao orderDao = new OrderDao();

		Connection connection = DbConnector.getConnection();
		String sql = "select * from sales_order where order_id = ? and account_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		statement.setInt(2, accountId);
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
		Order actual = orderDao.selectByOrderIdAndAccountId(orderId, accountId);
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
		 * 存在しない注文ID、会員ID
		 */
		// 初期化
		int orderIdNotExist = ORDER_ID_NOT_EXIST;
		int accountIdNotExist = ACCOUNT_ID_NOT_EXIST;
		// 実行
		Order actualNotExist = orderDao.selectByOrderIdAndAccountId(orderIdNotExist, accountIdNotExist);
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
	public void insertTest() throws Exception{
		/**
		 * 正常系
		 */
		// 初期化
		Account account = new Account();
		account.setId(NEW_ACCOUNT_ID);
		account.setName(NEW_ACCOUNT_NAME);
		account.setNameKana(NEW_ACCOUNT_NAME_KANA);
		account.setMail(NEW_ACCOUNT_MAIL);
		account.setZipCode(NEW_ACCOUNT_ZIP_CODE);
		account.setAddress(NEW_ACCOUNT_ADDRESS);
		account.setPhoneNumber(NEW_ACCOUNT_PHONE_NUMBER);
		int totalPrice = NEW_TOTAL_PRICE;

		OrderDao orderDao = new OrderDao();
		// 実行
		int orderId = orderDao.insert(account, totalPrice);
		Order actual = orderDao.selectByOrderId(orderId);
		// 検証
		assertThat("insert:accountId", actual.getAccountId(), is(NEW_ACCOUNT_ID));
		assertThat("insert:accountName", actual.getAccountName(), is(NEW_ACCOUNT_NAME));
		assertThat("insert:NameKana", actual.getAccountNameKana(), is(NEW_ACCOUNT_NAME_KANA));
		assertThat("insert:Mail", actual.getAccountMail(), is(NEW_ACCOUNT_MAIL));
		assertThat("insert:ZipCode", actual.getAccountZipCode(), is(NEW_ACCOUNT_ZIP_CODE));
		assertThat("insert:Address", actual.getAccountAddress(), is(NEW_ACCOUNT_ADDRESS));
		assertThat("insert:PhoneNumber", actual.getAccountPhoneNumber(), is(NEW_ACCOUNT_PHONE_NUMBER));
		assertThat("insert:TotalPrice", actual.getTotalPrice(), is(totalPrice));
		// 後処理
		Connection connection = DbConnector.getConnection();
		String sql = "delete from sales_order where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		int daleteCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();

		/**
		 * 異常系
		 */
		// 初期化
		Account expectedNoData = new Account();
		expectedNoData.setId(NEW_ACCOUNT_ID);
		// 実行
		int orderIdNoData = orderDao.insert(expectedNoData, totalPrice);
		Order actualNoData = orderDao.selectByOrderId(orderIdNoData);
		// 検証
		assertThat("insert:Nodata:Name", actualNoData.getAccountName(), is(expectedNoData.getName()));
		assertThat("insert:Nodata:NameKana", actualNoData.getAccountNameKana(), is(expectedNoData.getNameKana()));
		assertThat("insert:Nodata:Mail", actualNoData.getAccountMail(), is(expectedNoData.getMail()));
		assertThat("insert:Nodata:ZipCode", actualNoData.getAccountZipCode(), is(expectedNoData.getZipCode()));
		assertThat("insert:Nodata:Address", actualNoData.getAccountAddress(), is(expectedNoData.getAddress()));
		assertThat("insert:Nodata:PhoneNumber", actualNoData.getAccountPhoneNumber(), is(expectedNoData.getPhoneNumber()));
		assertThat("insert:TotalPrice", actual.getTotalPrice(), is(totalPrice));
		// 後処理
		Connection connectionNoData = DbConnector.getConnection();
		String sqlNoData = "delete from sales_order where order_id = ?";
		PreparedStatement statementNoData = connectionNoData.prepareStatement(sqlNoData);
		statementNoData.setInt(1, orderIdNoData);
		int daleteCountNoData = statementNoData.executeUpdate();
		statementNoData.close();
		connectionNoData.commit();
		connectionNoData.close();
	}

	@Test
	public void updateCancelStatusTest() throws Exception{
		/**
		 * 存在する注文ID
		 */
		// 初期化
		int orderId = ORDER_ID;
		OrderDao orderDao = new OrderDao();
		// 実行
		int updateCount = orderDao.updateCancelStatus(orderId);
		Order actual = orderDao.selectByOrderId(orderId);
		// 検証
		assertTrue("updateCancelStatus:cancelFlg", actual.getCancelFlg());
		assertNotNull("updateCancelStatus:cancelTime", actual.getCancelTime());
		// 後処理
		Connection connection = DbConnector.getConnection();
		String sql = "update sales_order set cancel_flg = false, cancel_time = NULL where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderId);
		int updateCountAfter = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();
	}
}
