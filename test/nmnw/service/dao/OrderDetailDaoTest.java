package nmnw.service.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.service.dao.Cart;
import com.nmnw.service.dao.OrderDetail;
import com.nmnw.service.dao.OrderDetailDao;
import com.nmnw.service.utility.DbConnector;

@RunWith(Theories.class)
public class OrderDetailDaoTest {
	public static final int SELECT_ORDER_ID = 10;
	public static final int INSERT_ORDER_ID = 5;
	public static final int ORDER_ID_NOT_EXIST = 9999;

	public static final int ORDER_DETAIL_ID = 1;
	public static final int ITEM_ID = 1;
	public static final String ITEM_NAME = "test";
	public static final int ITEM_PRICE = 1;
	public static final int ITEM_COUNT = 1;

	public static final int ORDER_DETAIL_ID_2 = 2;
	public static final int ITEM_ID_2 = 2;
	public static final String ITEM_NAME_2 = "test2";
	public static final int ITEM_PRICE_2 = 2;
	public static final int ITEM_COUNT_2 = 2;
	
	@Test
	public void selectByOrderIdTest() throws Exception{
		/**
		 * ë∂ç›Ç∑ÇÈíçï∂ID
		 */
		// èâä˙âª
		int orderIdExist = SELECT_ORDER_ID;
		OrderDetailDao orderDetailDao = new OrderDetailDao();

		Connection connection = DbConnector.getConnection();
		String sql = "select * from sales_order_detail where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, orderIdExist);
		ResultSet result = statement.executeQuery();
		List<OrderDetail> expected = new ArrayList<OrderDetail>();
		while (result.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(result.getInt("order_id"));
			orderDetail.setOrderDetailId(result.getInt("order_detail_id"));
			orderDetail.setItemId(result.getInt("item_id"));
			orderDetail.setItemName(result.getString("item_name"));
			orderDetail.setItemPrice(result.getInt("item_price"));
			orderDetail.setItemCount(result.getInt("item_count"));
			expected.add(orderDetail);
		}
		// é¿çs
		List<OrderDetail> actual = orderDetailDao.selectByOrderId(orderIdExist);
		// åüèÿ
		for(int i = 0; i < actual.size(); i++) {
			assertThat("selectByOrderId:orderId", actual.get(i).getOrderId(), is(expected.get(i).getOrderId()));
			assertThat("selectByOrderId:orderDetailId", actual.get(i).getOrderDetailId(), is(expected.get(i).getOrderDetailId()));
			assertThat("selectByOrderId:itemId", actual.get(i).getItemId(), is(expected.get(i).getItemId()));
			assertThat("selectByOrderId:itemName", actual.get(i).getItemName(), is(expected.get(i).getItemName()));
			assertThat("selectByOrderId:itemPrice", actual.get(i).getItemPrice(), is(expected.get(i).getItemPrice()));
			assertThat("selectByOrderId:itemCount", actual.get(i).getItemCount(), is(expected.get(i).getItemCount()));
		}
		// å„èàóù
		
		/**
		 * ë∂ç›ÇµÇ»Ç¢è§ïiID
		 */
		// èâä˙âª
		int orderIdNotExist = ORDER_ID_NOT_EXIST;
		// é¿çs
		List<OrderDetail> actualNotExist = orderDetailDao.selectByOrderId(orderIdNotExist);
		// åüèÿ
		assertThat("selectByOrderId:NotExist", actualNotExist.size(), is(0));
		// å„èàóù
	}

	@Test
	public void insertTest() throws Exception{
		/**
		 * ê≥èÌån
		 */
		// èâä˙âª
		Cart expected = new Cart();
		int itemId = ITEM_ID;
		String itemName = ITEM_NAME;
		int itemPrice = ITEM_PRICE;
		int itemCount = ITEM_COUNT;
		expected.addItem(itemId, itemName, itemPrice, itemCount);
		// é¿çs
		Class<OrderDetailDao> orderDetailDaoClass = OrderDetailDao.class;
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		Method method = orderDetailDaoClass.getDeclaredMethod("insert", int.class, int.class, Cart.CartItem.class);
		method.setAccessible(true);
		method.invoke(orderDetailDao, INSERT_ORDER_ID, ORDER_DETAIL_ID, expected.getItemList().get(0));

		Connection connection = DbConnector.getConnection();
		String sql = "select * from sales_order_detail where order_id = ? and order_detail_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, INSERT_ORDER_ID);
		statement.setInt(2, ORDER_DETAIL_ID);
		ResultSet result = statement.executeQuery();
		List<OrderDetail> actual = new ArrayList<OrderDetail>();
		while (result.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(result.getInt("order_id"));
			orderDetail.setOrderDetailId(result.getInt("order_detail_id"));
			orderDetail.setItemId(result.getInt("item_id"));
			orderDetail.setItemName(result.getString("item_name"));
			orderDetail.setItemPrice(result.getInt("item_price"));
			orderDetail.setItemCount(result.getInt("item_count"));
			actual.add(orderDetail);
		}
		statement.close();
		connection.commit();
		// åüèÿ
		assertThat("insert:orderId", actual.get(0).getOrderId(), is(INSERT_ORDER_ID));
		assertThat("insert:orderDetailId", actual.get(0).getOrderDetailId(), is(ORDER_DETAIL_ID));
		assertThat("insert:itemId", actual.get(0).getItemId(), is(itemId));
		assertThat("insert:itemName", actual.get(0).getItemName(), is(itemName));
		assertThat("insert:itemPrice", actual.get(0).getItemPrice(), is(itemPrice));
		assertThat("insert:itemCount", actual.get(0).getItemCount(), is(itemCount));
		// å„èàóù
		sql = "delete from sales_order_detail where order_id = ? and order_detail_id = ?";
		PreparedStatement statementDelete = connection.prepareStatement(sql);
		statementDelete.setInt(1, INSERT_ORDER_ID);
		statementDelete.setInt(2, ORDER_DETAIL_ID);
		int daleteCount = statementDelete.executeUpdate();
		statementDelete.close();
		connection.commit();
		connection.close();
	}

	@Test
	public void insertItemListTest() throws Exception{
		/**
		 * ê≥èÌån
		 */
		// èâä˙âª
		Cart cart = new Cart();
		cart.addItem(ITEM_ID, ITEM_NAME, ITEM_COUNT, ITEM_COUNT);
		cart.addItem(ITEM_ID_2, ITEM_NAME_2, ITEM_COUNT_2, ITEM_COUNT_2);
		// é¿çs
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		boolean successFlg = orderDetailDao.insertItemList(INSERT_ORDER_ID, cart.getItemList());

		List<OrderDetail> actual = orderDetailDao.selectByOrderId(INSERT_ORDER_ID);
		// åüèÿ
		assertTrue("insertItemList:ê≥èÌån", successFlg);

		assertThat("insertItemList:orderId1", actual.get(0).getOrderId(), is(INSERT_ORDER_ID));
		assertThat("insertItemList:orderDetailId1", actual.get(0).getOrderDetailId(), is(1));
		assertThat("insertItemList:itemId1", actual.get(0).getItemId(), is(ITEM_ID));
		assertThat("insertItemList:itemName1", actual.get(0).getItemName(), is(ITEM_NAME));
		assertThat("insertItemList:itemPrice1", actual.get(0).getItemPrice(), is(ITEM_PRICE));
		assertThat("insertItemList:itemCount1", actual.get(0).getItemCount(), is(ITEM_COUNT));

		assertThat("insertItemList:orderId2", actual.get(1).getOrderId(), is(INSERT_ORDER_ID));
		assertThat("insertItemList:orderDetailId2", actual.get(1).getOrderDetailId(), is(2));
		assertThat("insertItemList:itemId2", actual.get(1).getItemId(), is(ITEM_ID_2));
		assertThat("insertItemList:itemName2", actual.get(1).getItemName(), is(ITEM_NAME_2));
		assertThat("insertItemList:itemPrice2", actual.get(1).getItemPrice(), is(ITEM_PRICE_2));
		assertThat("insertItemList:itemCount2", actual.get(1).getItemCount(), is(ITEM_COUNT_2));
		// å„èàóù
		Connection connection = DbConnector.getConnection();
		String sql = "delete from sales_order_detail where order_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, INSERT_ORDER_ID);
		int daleteCount = statement.executeUpdate();
		statement.close();
		connection.commit();
		connection.close();
	}
}
