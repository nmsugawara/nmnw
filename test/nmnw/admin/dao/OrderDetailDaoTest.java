package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.admin.dao.OrderDetail;
import com.nmnw.admin.dao.OrderDetailDao;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class OrderDetailDaoTest {

	@Test
	public void selectByOrderIdTest() throws Exception{
		/**
		 * ë∂ç›Ç∑ÇÈíçï∂ID
		 */
		// èâä˙âª
		int orderIdExist = 10;
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
		int orderIdNotExist = 9999;
		// é¿çs
		List<OrderDetail> actualNotExist = orderDetailDao.selectByOrderId(orderIdNotExist);
		// åüèÿ
		assertThat("selectByOrderId:NotExist", actualNotExist.size(), is(0));
		// å„èàóù
	}
}
