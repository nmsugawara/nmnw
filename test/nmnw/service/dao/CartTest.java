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
		// 初期化
		Cart cart = new Cart();
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		// 実行
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// 検証
		assertThat("addItem:itemId", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount", cart.getItemList().get(0).getItemCount(), is(itemCount));
		// 後処理
	}

	@Test
	public void deleteItemTest() throws Exception{
		/**
		 * 存在する商品IDの場合
		 */
		// 初期化
		Cart cart = new Cart();
		int expected = 0;
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// 実行
		cart.deleteItem(itemId);
		// 検証
		assertThat("deleteItem：存在する商品IDの場合", cart.getItemList().size(), is(expected));
		// 後処理

		/**
		 * 存在しない商品IDの場合
		 */
		// 初期化
		int itemIdNotEist = 2;
		int expectedNotEist = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		// 実行
		cart.deleteItem(itemIdNotEist);
		// 検証
		assertThat("deleteItem：存在しない商品IDの場合", cart.getItemList().size(), is(expectedNotEist));
		// 後処理
	}

	@Test
	public void modifyItemCountTest() throws Exception{
		/**
		 * 存在する商品IDの場合
		 */
		// 初期化
		Cart cart = new Cart();
		int itemId = 1;
		String itemName = "test";
		int itemPrice = 1;
		int itemCount = 1;
		cart.addItem(itemId, itemName, itemPrice, itemCount);
		int changeItemCount = 5;
		// 実行
		cart.modifyItemCount(itemId, changeItemCount);
		// 検証
		assertThat("addItem:itemId", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount", cart.getItemList().get(0).getItemCount(), is(changeItemCount));
		// 後処理

		/**
		 * 存在しない商品IDの場合
		 */
		// 初期化
		int itemIdNotEist = 2;
		// 実行
		cart.modifyItemCount(itemIdNotEist, itemCount);
		// 検証
		assertThat("addItem:itemId:存在しない商品IDの場合", cart.getItemList().get(0).getItemId(), is(itemId));
		assertThat("addItem:itemName:存在しない商品IDの場合", cart.getItemList().get(0).getItemName(), is(itemName));
		assertThat("addItem:itemPrice:存在しない商品IDの場合", cart.getItemList().get(0).getItemPrice(), is(itemPrice));
		assertThat("addItem:itemCount:存在しない商品IDの場合", cart.getItemList().get(0).getItemCount(), is(changeItemCount));
		// 後処理
	}
}
