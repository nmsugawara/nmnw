package com.nmnw.service.constant;

public class ConfigConstants {

	public static final int NULL_INT = -1;

	// DB
	public static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String DB_SERVER_NAME = "localhost";
	public static final String DB_NAME = "nmnw";
	public static final String DB_USER_NAME = "root";
	public static final String DB_PASSWORD = "";
	public static final String JDBC_URL = "jdbc:mysql://" + DB_SERVER_NAME + "/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";

	public static final String DOMAIN = "localhost:8080";

	/**
	 *  mail
	 */
	// character-set
	public static final String CHARACTER_CODE = "iso-2022-jp";
	// smtp
	public static final String SMTP_HOST = "203.138.167.48";
	public static final String SMTP_PORT = "587";
	public static final String SMTP_AUTH = "true";
	public static final String SMTP_USER = "ssugawara";
	public static final String SMTP_PASSWORD = "ssnm4414";
/*
	/// –{”ÔŠÂ‹«—p
	public static final String SMTP_HOST = "127.0.0.1";
	public static final String SMTP_PORT = "587";
	public static final String SMTP_AUTH = "true";
	public static final String SMTP_USER = "c3VnYXdhcmFAaXAtMTcyLTMxLTQtMzQuYXAtbm9ydGhlYXN0LTEuY29tcHV0ZS5pbnRlcm5hbA==";
	public static final String SMTP_PASSWORD = "bmV0bWFya2V0aW5n";
*/
	// from
	public static final String MAIL_FROM = "ssugawara@net-marketing.co.jp";

	// token—LŒøŠúŠÔ
	public static final int TOKEN_EXPIRE_DAYS = 1;

	/**
	 *  Servlet
	 */
	// index
	public static final String SERVLET_DIR_INDEX = "/nmnw/index";
	// login
	public static final String SERVLET_DIR_LOGIN = "/nmnw/login";
	// logout
	public static final String SERVLET_DIR_LOGOUT = "/nmnw/logout";
	// cart
	public static final String SERVLET_DIR_CART = "/nmnw/cart";
	// error
	public static final String SERVLET_DIR_ERROR = "/nmnw/error/error";
	// item
	public static final String SERVLET_DIR_ITEM = "/nmnw/item/";
	public static final String SERVLET_DIR_ITEM_SEARCH = SERVLET_DIR_ITEM + "search";
	public static final String SERVLET_DIR_ITEM_DETAIL = SERVLET_DIR_ITEM + "detail";
	// account
	public static final String SERVLET_DIR_ACCOUNT = "/nmnw/account/";
	public static final String SERVLET_DIR_ACCOUNT_REGISTER = SERVLET_DIR_ACCOUNT + "register";
	public static final String SERVLET_DIR_ACCOUNT_DETAIL = SERVLET_DIR_ACCOUNT + "detail";
	public static final String SERVLET_DIR_ACCOUNT_EDIT = SERVLET_DIR_ACCOUNT + "edit";
	public static final String SERVLET_DIR_ACCOUNT_RESET_PASSWORD = SERVLET_DIR_ACCOUNT + "resetPassword";
	// order
	public static final String SERVLET_DIR_ORDER = "/nmnw/order/";
	public static final String SERVLET_DIR_ORDER_SEARCH = SERVLET_DIR_ORDER + "search";
	public static final String SERVLET_DIR_ORDER_CANCEL = SERVLET_DIR_ORDER + "cancel";
	// purchase
	public static final String SERVLET_DIR_PURCHASE = "/nmnw/purchase/";
	public static final String SERVLET_DIR_PURCHASE_CONFIRM = SERVLET_DIR_PURCHASE + "confirm";
	public static final String SERVLET_DIR_PURCHASE_COMPLETE = SERVLET_DIR_PURCHASE + "complete";

	/**
	 * JSP
	 */
	// index
	public static final String JSP_DIR_INDEX = "/WEB-INF/service/function/";
	// login
	public static final String JSP_DIR_LOGIN = "/WEB-INF/service/function/login/";
	// logout
	public static final String JSP_DIR_LOGOUT = "/WEB-INF/service/function/logout/";
	// cart
	public static final String JSP_DIR_CART = "/WEB-INF/service/function/cart/";
	// error
	public static final String JSP_DIR_ERROR = "/WEB-INF/service/function/error/";
	// item
	public static final String JSP_DIR_ITEM = "/WEB-INF/service/function/item/";
	public static final String JSP_DIR_ITEM_SEARCH = JSP_DIR_ITEM + "search/";
	public static final String JSP_DIR_ITEM_DETAIL = JSP_DIR_ITEM + "detail/";
	// account
	public static final String JSP_DIR_ACCOUNT = "/WEB-INF/service/function/account/";
	public static final String JSP_DIR_ACCOUNT_REGISTER = JSP_DIR_ACCOUNT + "register/";
	public static final String JSP_DIR_ACCOUNT_DETAIL = JSP_DIR_ACCOUNT + "detail/";
	public static final String JSP_DIR_ACCOUNT_EDIT = JSP_DIR_ACCOUNT + "edit/";
	public static final String JSP_DIR_ACCOUNT_RESET_PASSWORD = JSP_DIR_ACCOUNT + "resetPassword/";
	public static final String JSP_DIR_ACCOUNT_CHANGE_PASSWORD = JSP_DIR_ACCOUNT + "changePassword/";
	// order
	public static final String JSP_DIR_ORDER = "/WEB-INF/service/function/order/";
	public static final String JSP_DIR_ORDER_SEARCH = JSP_DIR_ORDER + "search/";
	public static final String JSP_DIR_ORDER_CANCEL = JSP_DIR_ORDER + "cancel/";
	// purchase
	public static final String JSP_DIR_PURCHASE = "/WEB-INF/service/function/purchase/";
	public static final String JSP_DIR_PURCHASE_CONFIRM = JSP_DIR_PURCHASE + "confirm/";
	public static final String JSP_DIR_PURCHASE_COMPLETE = JSP_DIR_PURCHASE + "complete/";

	/**
	 *  image
	 */
	public static final String IMAGE_DIR = "/nmnw/commons/images/";
	public static final String IMAGE_DIR_ITEM = IMAGE_DIR + "item/";
}