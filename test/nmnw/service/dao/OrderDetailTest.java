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
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		orderDetail.setOrderId(expected);
		// 検証
		assertEquals("setOrderId:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getOrderIdTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_orderId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getOrderId:正常系エラー", orderDetail.getOrderId(), expected);
		// 後処理
	}

	@Test
	public void setOrderDetailIdTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_orderDetailId");
		field.setAccessible(true);
		orderDetail.setOrderDetailId(expected);
		// 検証
		assertEquals("setOrderDetailId:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getOrderDetailIdTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_orderDetailId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getOrderDetailId:正常系エラー", orderDetail.getOrderDetailId(), expected);
		// 後処理
	}

	@Test
	public void setItemIdTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemId");
		field.setAccessible(true);
		orderDetail.setItemId(expected);
		// 検証
		assertEquals("setItemId:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getItemIdTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemId");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getItemId:正常系エラー", orderDetail.getItemId(), expected);
		// 後処理
	}

	@Test
	public void setItemNameTest() throws Exception{
		// 初期化
		String expected = "test";
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		orderDetail.setItemName(expected);
		// 検証
		assertEquals("setItemName:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getItemNameTest() throws Exception{
		// 初期化
		String expected = "test";
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getItemName:正常系エラー", orderDetail.getItemName(), expected);
		// 後処理
	}

	@Test
	public void getItemNameConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemName");
		field.setAccessible(true);
		field.set(orderDetail, value);
		// 検証
		assertEquals("getItemNameConvertedHtml:正常系エラー", orderDetail.getItemNameConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setItemPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemPrice");
		field.setAccessible(true);
		orderDetail.setItemPrice(expected);
		// 検証
		assertEquals("setItemPrice:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getItemPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemPrice");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getItemPrice:正常系エラー", orderDetail.getItemPrice(), expected);
		// 後処理
	}

	@Test
	public void setItemCountTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemCount");
		field.setAccessible(true);
		orderDetail.setItemCount(expected);
		// 検証
		assertEquals("setItemCount:正常系エラー", field.get(orderDetail), expected);
		// 後処理
	}

	@Test
	public void getItemCountTest() throws Exception{
		// 初期化
		int expected = 1;
		OrderDetail orderDetail = new OrderDetail();
		// 実行
		Field field = orderDetail.getClass().getDeclaredField("_itemCount");
		field.setAccessible(true);
		field.set(orderDetail, expected);
		// 検証
		assertEquals("getItemCount:正常系エラー", orderDetail.getItemCount(), expected);
		// 後処理
	}
}
