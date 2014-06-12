package com.nmnw.admin.dao;
import com.nmnw.admin.utility.HtmlHelper;

public class Account {

	private int _id;
	private String _name = "";
	private String _nameKana = "";
	private String _mail = "";
	private String _passWord = "";
	private String _zipCode = "";
	private String _address = "";
	private String _phoneNumber = "";
	private boolean _delFlg;
	private String _token = "";

	/**
	 * Construct
	 */
	public Account() {
	} 

	public void setId(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public String getNameConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_name);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setNameKana(String nameKana) {
		_nameKana = nameKana;
	}

	public String getNameKana() {
		return _nameKana;
	}

	public String getNameKanaConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_nameKana);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setMail(String mail) {
		_mail = mail;
	}

	public String getMail() {
		return _mail;
	}

	public String getMailConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_mail);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setPassWord(String passWord) {
		_passWord = passWord;
	}

	public String getPassWord() {
		return _passWord;
	}

	public String getPassWordConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_passWord);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setZipCode(String zipCode) {
		_zipCode = zipCode;
	}

	public String getZipCode() {
		return _zipCode;
	}

	public String getZipCodeConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_zipCode);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getAddress() {
		return _address;
	}

	public String getAddressConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_address);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public String getPhoneNumberConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_phoneNumber);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setDelFlg(boolean delFlg) {
		_delFlg = delFlg;
	}

	public boolean getDelFlg() {
		return _delFlg;
	}

	public void setToken(String token) {
		_token = token;
	}

	public String getToken() {
		return _token;
	}
}