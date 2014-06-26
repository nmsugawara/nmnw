package com.nmnw.service.dao;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	/**
	 * Construct
	 */
	public Cart() {
	} 

	private List<CartItem> _cartItemList = new ArrayList<CartItem>();

	/**
	 * 商品追加
	 * @param itemId
	 * @param itemName
	 * @param itemPrice
	 * @param itemCount
	 */
	public void addItem(int itemId, String itemName, int itemPrice, int itemCount) {
		CartItem cartItem = new CartItem();
		cartItem.setItemId(itemId);
		cartItem.setItemName(itemName);
		cartItem.setItemPrice(itemPrice);
		cartItem.setItemCount(itemCount);
		_cartItemList.add(cartItem);
	}

	/**
	 * 商品削除
	 * @param itemId
	 */
	public void deleteItem(int itemId) {
		for (int i = 0; i < _cartItemList.size(); i++) {
			if (itemId == _cartItemList.get(i).getItemId()) {
				_cartItemList.remove(i);
			}
		}
	}

	/**
	 * 商品個数変更
	 * @param itemId
	 * @param itemCount
	 */
	public void modifyItemCount(int itemId, int itemCount) {
		for (int i = 0; i < _cartItemList.size(); i++) {
			if (itemId == _cartItemList.get(i).getItemId()) {
				_cartItemList.get(i).setItemCount(itemCount);
			}
		}
	}

	/**
	 * 商品リスト取得
	 * @return
	 */
	public List<CartItem> getItemList() {
		return _cartItemList;
	}

	/**
	 * 商品データクラス
	 * @author ssugawara
	 *
	 */
	public class CartItem {
		private int _itemId;
		private String _itemName = "";
		private int _itemPrice;
		private int _itemCount;

		public void setItemId(int itemId) {
			_itemId = itemId;
		}

		public int getItemId() {
			return _itemId;
		}

		public void setItemName(String itemName) {
			_itemName = itemName;
		}

		public String getItemName() {
			return _itemName;
		}

		public void setItemPrice(int itemPrice) {
			_itemPrice = itemPrice;
		}

		public Integer getItemPrice() {
			return _itemPrice;
		}

		public void setItemCount(int itemCount) {
			_itemCount = itemCount;
		}

		public int getItemCount() {
			return _itemCount;
		}
	}
}