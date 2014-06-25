package com.nmnw.service.dao;
import com.nmnw.service.utility.HtmlHelper;

public class OrderDetail {

	private int _orderId;
	private int _orderDetailId;
	private int _itemId;
	private String _itemName = "";
	private int _itemPrice;
	private int _itemCount;

	/**
	 * Construct
	 */
	public OrderDetail() {
	} 

	public void setOrderId(int orderId) {
		_orderId = orderId;
	}

	public int getOrderId() {
		return _orderId;
	}

	public void setOrderDetailId(int orderDetailId) {
		_orderDetailId = orderDetailId;
	}

	public int getOrderDetailId() {
		return _orderDetailId;
	}

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

	public String getItemNameConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_itemName);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setItemPrice(int itemPrice) {
		_itemPrice = itemPrice;
	}

	public int getItemPrice() {
		return _itemPrice;
	}

	public void setItemCount(int itemCount) {
		_itemCount = itemCount;
	}

	public int getItemCount() {
		return _itemCount;
	}
}