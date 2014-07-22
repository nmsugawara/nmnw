package nmnw.service.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import com.nmnw.service.dao.OrderDetail;

public class OrderDetailTest {

	@Test
	public void orderDetailDetailTest() {
	}

	@Test
	public void setOrderIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		orderDetail.setOrderId(expected);
		// ����
		assertEquals("setOrderId:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getOrderIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getOrderId:����n�G���[", orderDetail.getOrderId(), expected);
		// �㏈��
	}

	@Test
	public void setOrderDetailIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_orderDetailId");
		field.setAccessible(true);
		orderDetail.setOrderDetailId(expected);
		// ����
		assertEquals("setOrderDetailId:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getOrderDetailIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_orderDetailId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getOrderDetailId:����n�G���[", orderDetail.getOrderDetailId(), expected);
		// �㏈��
	}

	@Test
	public void setItemIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemId");
		field.setAccessible(true);
		orderDetail.setItemId(expected);
		// ����
		assertEquals("setItemId:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getItemIdTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getItemId:����n�G���[", orderDetail.getItemId(), expected);
		// �㏈��
	}

	@Test
	public void setItemNameTest() throws Exception{
		// ������
		String expected = "test";
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		orderDetail.setItemName(expected);
		// ����
		assertEquals("setItemName:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getItemNameTest() throws Exception{
		// ������
		String expected = "test";
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getItemName:����n�G���[", orderDetail.getItemName(), expected);
		// �㏈��
	}

	@Test
	public void getItemNameConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		field.set(orderDetail, value);
		// ����
		assertEquals("getItemNameConvertedHtml:����n�G���[", orderDetail.getItemNameConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setItemPriceTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemPrice");
		field.setAccessible(true);
		orderDetail.setItemPrice(expected);
		// ����
		assertEquals("setItemPrice:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getItemPriceTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemPrice");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getItemPrice:����n�G���[", orderDetail.getItemPrice(), expected);
		// �㏈��
	}

	@Test
	public void setItemCountTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemCount");
		field.setAccessible(true);
		orderDetail.setItemCount(expected);
		// ����
		assertEquals("setItemCount:����n�G���[", field.get(orderDetail), expected);
		// �㏈��
	}

	@Test
	public void getItemCountTest() throws Exception{
		// ������
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// ���s
		Field field = orderDetail.getClass().getDeclaredField("_itemCount");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// ����
		assertEquals("getItemCount:����n�G���[", orderDetail.getItemCount(), expected);
		// �㏈��
	}
}
