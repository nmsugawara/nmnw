package nmnw.service.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.nmnw.service.dao.Cart;

public class CartTest {

	@Test
	public void cartTest() {
	}

	@Test
	public void addItemTest() throws Exception{
		// ������
		Cart cart = new Cart();
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		// ���s
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// ����
		assertThat("addItem:itemId", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount", cart.getItemList().get(0).getItemCount(), is(itemCount));
		// �㏈��
	}

	@Test
	public void deleteItemTest() throws Exception{
		/**
		 * ���݂��鏤�iID�̏ꍇ
		 */
		// ������
		Cart cart = new Cart();
		int expected = 0;
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// ���s
		cart.deleteItem(itemId);
		// ����
		assertThat("deleteItem�F���݂��鏤�iID�̏ꍇ", cart.getItemList().size(), is(expected));
		// �㏈��

		/**
		 * ���݂��Ȃ����iID�̏ꍇ
		 */
		// ������
		int itemIdNotEist = 2;
		int expectedNotEist = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// ���s
		cart.deleteItem(itemIdNotEist);
		// ����
		assertThat("deleteItem�F���݂��Ȃ����iID�̏ꍇ", cart.getItemList().size(), is(expectedNotEist));
		// �㏈��
	}

	@Test
	public void modifyItemCountTest() throws Exception{
		/**
		 * ���݂��鏤�iID�̏ꍇ
		 */
		// ������
		Cart cart = new Cart();
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		int changeItemCount = 5;
		// ���s
		cart.modifyItemCount(itemId, changeItemCount);
		// ����
		assertThat("addItem:itemId", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount", cart.getItemList().get(0).getItemCount(), is(changeItemCount));
		// �㏈��

		/**
		 * ���݂��Ȃ����iID�̏ꍇ
		 */
		// ������
		int itemIdNotEist = 2;
		// ���s
		cart.modifyItemCount(itemIdNotEist, itemCount);
		// ����
		assertThat("addItem:itemId:���݂��Ȃ����iID�̏ꍇ", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName:���݂��Ȃ����iID�̏ꍇ", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice:���݂��Ȃ����iID�̏ꍇ", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount:���݂��Ȃ����iID�̏ꍇ", cart.getItemList().get(0).getItemCount(), is(changeItemCount));
		// �㏈��
	}
}
