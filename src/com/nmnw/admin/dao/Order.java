package com.nmnw.admin.dao;
import com.nmnw.admin.utility.HtmlHelper;

public class Order {

	private int _orderId;
	private String _orderTime = "";
	private int _accountId;
	private String _accountName = "";
	private String _accountNameKana = "";
	private String _accountMail = "";
	private String _accountZipCode = "";
	private String _accountAddress = "";
	private String _accountPhoneNumber = "";
	private int _totalPrice;
	private int _cancelFlg;
	private String _cancelTime = "";
	private int _shippingFlg;
	private String _shippingTime = "";

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

	public void setOrderTime(String orderTime) {
		_orderTime = orderTime;
	}

	public String getOrderTime() {
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

	public void setCancelFlg(int cancelFlg) {
		_cancelFlg = cancelFlg;
	}

	public int getCancelFlg() {
		return _cancelFlg;
	}

	public void setCancelTime(String cancelTime) {
		_cancelTime = cancelTime;
	}

	public String getCancelTime() {
		return _cancelTime;
	}

	public void setShippingFlg(int shippingFlg) {
		_shippingFlg = shippingFlg;
	}

	public int getShippingFlg() {
		return _shippingFlg;
	}

	public void setShippingTime(String shippingTime) {
		_shippingTime = shippingTime;
	}

	public String getShippingTime() {
		return _shippingTime;
	}
}