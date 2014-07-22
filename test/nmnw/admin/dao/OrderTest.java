package nmnw.admin.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.nmnw.admin.dao.Order;

public class OrderTest {

	@Test
	public void orderTest() {
	}

	@Test
	public void setOrderIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		order.setOrderId(expected);
		// 検証
		assertEquals("setOrderId:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getOrderIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getOrderId:正常系エラー", order.getOrderId(), expected);
		// 後処理
	}

	@Test
	public void setOrderTimeDateTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_orderTime");
		field.setAccessible(true);
		order.setOrderTime(expected);
		// 検証
		assertEquals("setOrderTime:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void setOrderTimeTimeStampTest() throws Exception{
		/**
		 * 引数：Timestamp型
		 */
		// 初期化
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_orderTime");
		fieldTrue.setAccessible(true);
		orderTrue.setOrderTime(expectedTrue);
		// 検証
		assertEquals("setOrderTime:正常系エラー", fieldTrue.get(orderTrue), expectedTrue);
		// 後処理
	}

	@Test
	public void getOrderTimeTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_orderTime");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getOrderTime:正常系エラー", order.getOrderTime(), expected);
		// 後処理
	}

	@Test
	public void setAccountIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountId");
		field.setAccessible(true);
		order.setAccountId(expected);
		// 検証
		assertEquals("setAccountId:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountId");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountId:正常系エラー", order.getAccountId(), expected);
		// 後処理
	}

	@Test
	public void setAccountNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		order.setAccountName(expected);
		// 検証
		assertEquals("setAccountName:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountName:正常系エラー", order.getAccountName(), expected);
		// 後処理
	}

	@Test
	public void getAccountNameConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountNameConvertedHtml:正常系エラー", order.getAccountNameConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAccountNameKanaTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		order.setAccountNameKana(expected);
		// 検証
		assertEquals("setAccountNameKana:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountNameKanaTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountNameKana:正常系エラー", order.getAccountNameKana(), expected);
		// 後処理
	}

	@Test
	public void getAccountNameKanaConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountNameKanaConvertedHtml:正常系エラー", order.getAccountNameKanaConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAccountMailTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		order.setAccountMail(expected);
		// 検証
		assertEquals("setAccountMail:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountMailTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountMail:正常系エラー", order.getAccountMail(), expected);
		// 後処理
	}

	@Test
	public void getAccountMailConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountMailConvertedHtml:正常系エラー", order.getAccountMailConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAccountZipCodeTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		order.setAccountZipCode(expected);
		// 検証
		assertEquals("setAccountZipCode:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountZipCodeTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountZipCode:正常系エラー", order.getAccountZipCode(), expected);
		// 後処理
	}

	@Test
	public void getAccountZipCodeConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountZipCodeConvertedHtml:正常系エラー", order.getAccountZipCodeConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAccountAddressTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		order.setAccountAddress(expected);
		// 検証
		assertEquals("setAccountAddress:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountAddressTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountAddress:正常系エラー", order.getAccountAddress(), expected);
		// 後処理
	}

	@Test
	public void getAccountAddressConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountAddressConvertedHtml:正常系エラー", order.getAccountAddressConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAccountPhoneNumberTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		order.setAccountPhoneNumber(expected);
		// 検証
		assertEquals("setAccountPhoneNumber:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getAccountPhoneNumberTest() throws Exception{
		// 初期化
		String expected = "test";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getAccountPhoneNumber:正常系エラー", order.getAccountPhoneNumber(), expected);
		// 後処理
	}

	@Test
	public void getAccountPhoneNumberConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		field.set(order, value);
		// 検証
		assertEquals("getAccountPhoneNumberConvertedHtml:正常系エラー", order.getAccountPhoneNumberConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setTotalPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_totalPrice");
		field.setAccessible(true);
		order.setTotalPrice(expected);
		// 検証
		assertEquals("setTotalPrice:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void getTotalPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_totalPrice");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getTotalPrice:正常系エラー", order.getTotalPrice(), expected);
		// 後処理
	}

	@Test
	public void setCancelFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelFlg");
		fieldTrue.setAccessible(true);
		orderTrue.setCancelFlg(expectedTrue);
		// 検証
		assertTrue("setCancelFlg:True", (boolean)fieldTrue.get(orderTrue));
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// 実行
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_cancelFlg");
		fieldFalse.setAccessible(true);
		orderFalse.setCancelFlg(expectedFalse);
		// 検証
		assertFalse("setCancelFlg:False", (boolean)fieldFalse.get(orderFalse));
		// 後処理
	}

	@Test
	public void getCancelFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(orderTrue, expectedTrue);
		// 検証
		assertTrue("getCancelFlg:True", orderTrue.getCancelFlg());
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// 実行
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_cancelFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(orderTrue, expectedFalse);
		// 検証
		assertFalse("setCancelFlg:False", orderTrue.getCancelFlg());
		// 後処理
	}

	@Test
	public void setCancelTimeDateTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_cancelTime");
		field.setAccessible(true);
		order.setCancelTime(expected);
		// 検証
		assertEquals("setCancelTime:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void setCancelTimeTimeStampTest() throws Exception{
		/**
		 * 引数：Timestamp型
		 */
		// 初期化
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelTime");
		fieldTrue.setAccessible(true);
		orderTrue.setCancelTime(expectedTrue);
		// 検証
		assertEquals("setCancelTime:正常系エラー", fieldTrue.get(orderTrue), expectedTrue);
		// 後処理
	}

	@Test
	public void getCancelTimeTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_cancelTime");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getCancelTime:正常系エラー", order.getCancelTime(), expected);
		// 後処理
	}

	@Test
	public void setShippingFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingFlg");
		fieldTrue.setAccessible(true);
		orderTrue.setShippingFlg(expectedTrue);
		// 検証
		assertTrue("setShippingFlg:True", (boolean)fieldTrue.get(orderTrue));
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// 実行
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_shippingFlg");
		fieldFalse.setAccessible(true);
		orderFalse.setShippingFlg(expectedFalse);
		// 検証
		assertFalse("setShippingFlg:False", (boolean)fieldFalse.get(orderFalse));
		// 後処理
	}

	@Test
	public void getShippingFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(orderTrue, expectedTrue);
		// 検証
		assertTrue("getShippingFlg:True", orderTrue.getShippingFlg());
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// 実行
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_shippingFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(orderTrue, expectedFalse);
		// 検証
		assertFalse("setShippingFlg:False", orderTrue.getShippingFlg());
		// 後処理
	}

	@Test
	public void setShippingTimeDateTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_shippingTime");
		field.setAccessible(true);
		order.setShippingTime(expected);
		// 検証
		assertEquals("setShippingTime:正常系エラー", field.get(order), expected);
		// 後処理
	}

	@Test
	public void setShippingTimeTimeStampTest() throws Exception{
		/**
		 * 引数：Timestamp型
		 */
		// 初期化
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// 実行
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingTime");
		fieldTrue.setAccessible(true);
		orderTrue.setShippingTime(expectedTrue);
		// 検証
		assertEquals("setShippingTime:正常系エラー", fieldTrue.get(orderTrue), expectedTrue);
		// 後処理
	}

	@Test
	public void getShippingTimeTest() throws Exception{
		// 初期化
		Date expected = new Date();
		Order order = new Order();
		// 実行
		Field field = order.getClass().getDeclaredField("_shippingTime");
		field.setAccessible(true);
		field.set(order, expected);
		// 検証
		assertEquals("getShippingTime:正常系エラー", order.getShippingTime(), expected);
		// 後処理
	}
}
