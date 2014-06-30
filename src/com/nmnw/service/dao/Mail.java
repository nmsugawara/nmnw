package com.nmnw.service.dao;

import com.nmnw.admin.utility.HtmlHelper;

public class Mail {

	private String _code = "";
	private String _subject = "";
	private String _message = "";

	/**
	 * Construct
	 */
	public Mail() {
	} 

	public void setCode(String code) {
		_code = code;
	}

	public String getCode() {
		return _code;
	}

	public String getCodeConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_code);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getSubject() {
		return _subject;
	}

	public String getSubjectConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_subject);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}

	public String getMessageConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_message);
		str = HtmlHelper.nl2br(str);
		return str;
	}
}