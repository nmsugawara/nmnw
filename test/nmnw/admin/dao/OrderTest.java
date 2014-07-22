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
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		order.setOrderId(expected);
		// ����
		assertEquals("setOrderId:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getOrderIdTest() throws Exception{
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getOrderId:����n�G���[", order.getOrderId(), expected);
		// �㏈��
	}

	@Test
	public void setOrderTimeDateTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_orderTime");
		field.setAccessible(true);
		order.setOrderTime(expected);
		// ����
		assertEquals("setOrderTime:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void setOrderTimeTimeStampTest() throws Exception{
		/**
		 * �����FTimestamp�^
		 */
		// ������
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_orderTime");
		fieldTrue.setAccessible(true);
		orderTrue.setOrderTime(expectedTrue);
		// ����
		assertEquals("setOrderTime:����n�G���[", fieldTrue.get(orderTrue), expectedTrue);
		// �㏈��
	}

	@Test
	public void getOrderTimeTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_orderTime");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getOrderTime:����n�G���[", order.getOrderTime(), expected);
		// �㏈��
	}

	@Test
	public void setAccountIdTest() throws Exception{
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountId");
		field.setAccessible(true);
		order.setAccountId(expected);
		// ����
		assertEquals("setAccountId:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountIdTest() throws Exception{
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountId");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountId:����n�G���[", order.getAccountId(), expected);
		// �㏈��
	}

	@Test
	public void setAccountNameTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		order.setAccountName(expected);
		// ����
		assertEquals("setAccountName:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountNameTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountName:����n�G���[", order.getAccountName(), expected);
		// �㏈��
	}

	@Test
	public void getAccountNameConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountName");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountNameConvertedHtml:����n�G���[", order.getAccountNameConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAccountNameKanaTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		order.setAccountNameKana(expected);
		// ����
		assertEquals("setAccountNameKana:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountNameKanaTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountNameKana:����n�G���[", order.getAccountNameKana(), expected);
		// �㏈��
	}

	@Test
	public void getAccountNameKanaConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountNameKana");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountNameKanaConvertedHtml:����n�G���[", order.getAccountNameKanaConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAccountMailTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		order.setAccountMail(expected);
		// ����
		assertEquals("setAccountMail:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountMailTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountMail:����n�G���[", order.getAccountMail(), expected);
		// �㏈��
	}

	@Test
	public void getAccountMailConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountMail");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountMailConvertedHtml:����n�G���[", order.getAccountMailConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAccountZipCodeTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		order.setAccountZipCode(expected);
		// ����
		assertEquals("setAccountZipCode:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountZipCodeTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountZipCode:����n�G���[", order.getAccountZipCode(), expected);
		// �㏈��
	}

	@Test
	public void getAccountZipCodeConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountZipCode");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountZipCodeConvertedHtml:����n�G���[", order.getAccountZipCodeConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAccountAddressTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		order.setAccountAddress(expected);
		// ����
		assertEquals("setAccountAddress:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountAddressTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountAddress:����n�G���[", order.getAccountAddress(), expected);
		// �㏈��
	}

	@Test
	public void getAccountAddressConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountAddress");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountAddressConvertedHtml:����n�G���[", order.getAccountAddressConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAccountPhoneNumberTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		order.setAccountPhoneNumber(expected);
		// ����
		assertEquals("setAccountPhoneNumber:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getAccountPhoneNumberTest() throws Exception{
		// ������
		String expected = "test";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getAccountPhoneNumber:����n�G���[", order.getAccountPhoneNumber(), expected);
		// �㏈��
	}

	@Test
	public void getAccountPhoneNumberConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_accountPhoneNumber");
		field.setAccessible(true);
		field.set(order, value);
		// ����
		assertEquals("getAccountPhoneNumberConvertedHtml:����n�G���[", order.getAccountPhoneNumberConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setTotalPriceTest() throws Exception{
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_totalPrice");
		field.setAccessible(true);
		order.setTotalPrice(expected);
		// ����
		assertEquals("setTotalPrice:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void getTotalPriceTest() throws Exception{
		// ������
		int expected = 1;
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_totalPrice");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getTotalPrice:����n�G���[", order.getTotalPrice(), expected);
		// �㏈��
	}

	@Test
	public void setCancelFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelFlg");
		fieldTrue.setAccessible(true);
		orderTrue.setCancelFlg(expectedTrue);
		// ����
		assertTrue("setCancelFlg:True", (boolean)fieldTrue.get(orderTrue));
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// ���s
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_cancelFlg");
		fieldFalse.setAccessible(true);
		orderFalse.setCancelFlg(expectedFalse);
		// ����
		assertFalse("setCancelFlg:False", (boolean)fieldFalse.get(orderFalse));
		// �㏈��
	}

	@Test
	public void getCancelFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(orderTrue, expectedTrue);
		// ����
		assertTrue("getCancelFlg:True", orderTrue.getCancelFlg());
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// ���s
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_cancelFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(orderTrue, expectedFalse);
		// ����
		assertFalse("setCancelFlg:False", orderTrue.getCancelFlg());
		// �㏈��
	}

	@Test
	public void setCancelTimeDateTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_cancelTime");
		field.setAccessible(true);
		order.setCancelTime(expected);
		// ����
		assertEquals("setCancelTime:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void setCancelTimeTimeStampTest() throws Exception{
		/**
		 * �����FTimestamp�^
		 */
		// ������
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_cancelTime");
		fieldTrue.setAccessible(true);
		orderTrue.setCancelTime(expectedTrue);
		// ����
		assertEquals("setCancelTime:����n�G���[", fieldTrue.get(orderTrue), expectedTrue);
		// �㏈��
	}

	@Test
	public void getCancelTimeTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_cancelTime");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getCancelTime:����n�G���[", order.getCancelTime(), expected);
		// �㏈��
	}

	@Test
	public void setShippingFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingFlg");
		fieldTrue.setAccessible(true);
		orderTrue.setShippingFlg(expectedTrue);
		// ����
		assertTrue("setShippingFlg:True", (boolean)fieldTrue.get(orderTrue));
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// ���s
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_shippingFlg");
		fieldFalse.setAccessible(true);
		orderFalse.setShippingFlg(expectedFalse);
		// ����
		assertFalse("setShippingFlg:False", (boolean)fieldFalse.get(orderFalse));
		// �㏈��
	}

	@Test
	public void getShippingFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(orderTrue, expectedTrue);
		// ����
		assertTrue("getShippingFlg:True", orderTrue.getShippingFlg());
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Order orderFalse = new Order();
		// ���s
		Field fieldFalse = orderFalse.getClass().getDeclaredField("_shippingFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(orderTrue, expectedFalse);
		// ����
		assertFalse("setShippingFlg:False", orderTrue.getShippingFlg());
		// �㏈��
	}

	@Test
	public void setShippingTimeDateTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_shippingTime");
		field.setAccessible(true);
		order.setShippingTime(expected);
		// ����
		assertEquals("setShippingTime:����n�G���[", field.get(order), expected);
		// �㏈��
	}

	@Test
	public void setShippingTimeTimeStampTest() throws Exception{
		/**
		 * �����FTimestamp�^
		 */
		// ������
		Calendar cal = Calendar.getInstance();
		Timestamp expectedTrue = new Timestamp(cal.getTimeInMillis());
		Order orderTrue = new Order();
		// ���s
		Field fieldTrue = orderTrue.getClass().getDeclaredField("_shippingTime");
		fieldTrue.setAccessible(true);
		orderTrue.setShippingTime(expectedTrue);
		// ����
		assertEquals("setShippingTime:����n�G���[", fieldTrue.get(orderTrue), expectedTrue);
		// �㏈��
	}

	@Test
	public void getShippingTimeTest() throws Exception{
		// ������
		Date expected = new Date();
		Order order = new Order();
		// ���s
		Field field = order.getClass().getDeclaredField("_shippingTime");
		field.setAccessible(true);
		field.set(order, expected);
		// ����
		assertEquals("getShippingTime:����n�G���[", order.getShippingTime(), expected);
		// �㏈��
	}
}
