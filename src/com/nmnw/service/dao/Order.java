package com.nmnw.service.dao;
import java.sql.Timestamp;
import java.util.Date;

import com.nmnw.service.utility.HtmlHelper;

public class Order {

	private int _orderId;
	private Date _orderTime;
	private int _accountId;
	private String _accountName = "";
	private String _accountNameKana = "";
	private String _accountMail = "";
	private String _accountZipCode = "";
	private String _accountAddress = "";
	private String _accountPhoneNumber = "";
	private int _totalPrice;
	private boolean _cancelFlg;
	private Date _cancelTime;
	private boolean _shippingFlg;
	private Date _shippingTime;

	/**
	 * Construct
	 */
	public Order() {
	} 

	public void setOrderId(int orderId) {
		_orderId = orderId;
	}

	public int getOrderId() {
		return _orderId;
	}

	public void setOrderTime(Date orderTime) {
		_orderTime = orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		java.util.Date d = new java.util.Date(orderTime.getTime());
		_orderTime = d;
	}

	public Date getOrderTime() {
		return _orderTime;
	}

	public void setAccountId(int accountId) {
		_accountId = accountId;
	}

	public int getAccountId() {
		return _accountId;
	}

	public void setAccountName(String accountName) {
		_accountName = accountName;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getAccountNameConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountName);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAccountNameKana(String accountNameKana) {
		_accountNameKana = accountNameKana;
	}

	public String getAccountNameKana() {
		return _accountNameKana;
	}

	public String getAccountNameKanaConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountNameKana);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAccountMail(String accountMail) {
		_accountMail = accountMail;
	}

	public String getAccountMail() {
		return _accountMail;
	}

	public String getAccountMailConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountMail);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAccountZipCode(String accountZipCode) {
		_accountZipCode = accountZipCode;
	}

	public String getAccountZipCode() {
		return _accountZipCode;
	}

	public String getAccountZipCodeConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountZipCode);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAccountAddress(String accountAddress) {
		_accountAddress = accountAddress;
	}

	public String getAccountAddress() {
		return _accountAddress;
	}

	public String getAccountAddressConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountAddress);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAccountPhoneNumber(String accountPhoneNumber) {
		_accountPhoneNumber = accountPhoneNumber;
	}

	public String getAccountPhoneNumber() {
		return _accountPhoneNumber;
	}

	public String getAccountPhoneNumberConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_accountPhoneNumber);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setTotalPrice(int totalPrice) {
		_totalPrice = totalPrice;
	}

	public int getTotalPrice() {
		return _totalPrice;
	}

	public void setCancelFlg(boolean cancelFlg) {
		_cancelFlg = cancelFlg;
	}

	public boolean getCancelFlg() {
		return _cancelFlg;
	}

	public void setCancelTime(Date cancelTime) {
		_cancelTime = cancelTime;
	}

	public void setCancelTime(Timestamp cancelTime) {
		java.util.Date d = null;
		if (cancelTime != null) {
			d = new java.util.Date(cancelTime.getTime());
		}
		_cancelTime = d;
	}

	public Date getCancelTime() {
		return _cancelTime;
	}

	public void setShippingFlg(boolean shippingFlg) {
		_shippingFlg = shippingFlg;
	}

	public boolean getShippingFlg() {
		return _shippingFlg;
	}

	public void setShippingTime(Date shippingTime) {
		_shippingTime = shippingTime;
	}

	public void setShippingTime(Timestamp shippingTime) {
		java.util.Date d = null;
		if (shippingTime != null) {
			d = new java.util.Date(shippingTime.getTime());
		}
		_shippingTime = d;
	}

	public Date getShippingTime() {
		return _shippingTime;
	}
}