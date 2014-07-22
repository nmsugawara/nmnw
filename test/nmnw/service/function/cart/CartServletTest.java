package nmnw.service.function.cart;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Cart;
import com.nmnw.service.function.cart.CartServlet;

public class CartServletTest {
	public static final long serialVersionUID = 1L;
	public static final String FIELD_ITEM_ID = "商品ID";
	public static final String FIELD_ITEM_COUNT = "商品個数";
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_NULL = null;
	public static final String VALUE_ACTION_NOT_EXIST = "test";
	public static final String VALUE_ACTION_ADD = "add";
	public static final String VALUE_ACTION_DELETE = "delete";
	public static final String VALUE_ACTION_MODIFY = "modify";
	public static final String KEY_CART = "cart";
	public static final String KEY_CART_NOT_EXIST = "test";
	public static final String REQUEST_KEY_REFURL = "ref_url";
	public static final String KEY_REFURL = "refUrl";
	public final String REQUEST_KEY_MESSAGE = "message_code";
	public static final String KEY_MESSAGE = "messageCode";
	public static final String KEY_ITEM_STOCK = "stockList";
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String KEY_LOGIN_ID = "id";
	public static final String KEY_ITEM_ID = "item_id";
	public static final String KEY_ITEM_COUNT = "item_count";
	public static final String VALUE_MESSAGE = "test";
	public static final String VALUE_MESSAGE_NULL = null;
	public static final String VALUE_MESSAGE_ITEM_FORMAT_ERROR = "商品IDは数値で入力してください。";
	public static final String VALUE_REFURL = "test";
	public static final String VALUE_LOGIN_ID = "2";
	public static final String VALUE_LOGIN_ID_NULL = null;
	public static final int CART_ITEM_ID = 1;
	public static final String CART_ITEM_NAME = "test";
	public static final int CART_ITEM_PRICE = 1;
	public static final int CART_ITEM_COUNT = 1;
	public static final int CART_ITEM_STOCK = 10;
	public static final String CART_ITEM_ID_WRONG = "test";
	public static final String CART_ITEM_ID_NOT_EXIST = "9999";
	public static final String CART_ITEM_ID_STRING = "1";
	public static final String CART_ITEM_COUNT_STRING = "2";
	public static final String URL = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_CART + "?ref_url=" + VALUE_REFURL + "&message_code=";
	public static final String URL_ADD = URL + VALUE_ACTION_ADD;
	public static final String URL_DELETE = URL + VALUE_ACTION_DELETE;
	public static final String URL_MODIFY = URL + VALUE_ACTION_MODIFY;
	@Test
	public void CartServletTest() {
	}

	@Test
	public void doGetTest() {
		/**
		 * 異常系：ログインしていない
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
			HttpServletRequest requestNotLogin = createMock(HttpServletRequest.class);
			HttpServletResponse responseNotLogin = createMock(HttpServletResponse.class);
			RequestDispatcher rdNotLogin = createMock(RequestDispatcher.class);
			HttpSession sessionNotLogin = createMock(HttpSession.class);

			requestNotLogin.setCharacterEncoding("UTF-8");
			responseNotLogin.setContentType("text/html; charset=UTF-8");
			expect(requestNotLogin.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE_NULL);
			expect(requestNotLogin.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestNotLogin.getSession()).andReturn(sessionNotLogin);
			expect(sessionNotLogin.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID_NULL);
			requestNotLogin.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			expect(requestNotLogin.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			requestNotLogin.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestNotLogin.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdNotLogin);
			rdNotLogin.forward(requestNotLogin, responseNotLogin);
			replay(requestNotLogin, responseNotLogin, rdNotLogin, sessionNotLogin);
			Method methodNotLogin = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotLogin.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodNotLogin.invoke(cartServlet, requestNotLogin, responseNotLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：actionパラメータエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListNoActionParam = new ArrayList<String>();
			errorMessageListNoActionParam.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			HttpServletRequest requestNoActionParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseNoActionParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdNoActionParam = createMock(RequestDispatcher.class);
			HttpSession sessionNoActionParam = createMock(HttpSession.class);

			requestNoActionParam.setCharacterEncoding("UTF-8");
			responseNoActionParam.setContentType("text/html; charset=UTF-8");
			expect(requestNoActionParam.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestNoActionParam.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestNoActionParam.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestNoActionParam.getSession()).andReturn(sessionNoActionParam);
			expect(sessionNoActionParam.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestNoActionParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NOT_EXIST);
			expect(requestNoActionParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NOT_EXIST);
			requestNoActionParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageListNoActionParam);
			expect(requestNoActionParam.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			requestNoActionParam.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestNoActionParam.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdNoActionParam);
			rdNoActionParam.forward(requestNoActionParam, responseNoActionParam);
			replay(requestNoActionParam, responseNoActionParam, rdNoActionParam, sessionNoActionParam);
			Method methodNoActionParam = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNoActionParam.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodNoActionParam.invoke(cartServlet, requestNoActionParam, responseNoActionParam);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：カート画面表示（カート内に商品無）
		 */
		try {
			// 初期化
			Map<Integer, Integer> stockListNoCart = new LinkedHashMap<Integer, Integer>();
			HttpServletRequest requestNoCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseNoCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdNoCart = createMock(RequestDispatcher.class);
			HttpSession sessionNoCart = createMock(HttpSession.class);
			Enumeration<String> enumrationNoCart = createMock(Enumeration.class);

			requestNoCart.setCharacterEncoding("UTF-8");
			responseNoCart.setContentType("text/html; charset=UTF-8");
			expect(requestNoCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestNoCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestNoCart.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestNoCart.getSession()).andReturn(sessionNoCart);
			expect(sessionNoCart.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestNoCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			expect(requestNoCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			expect(sessionNoCart.getAttributeNames()).andReturn(enumrationNoCart);
			expect(enumrationNoCart.hasMoreElements()).andReturn(true);
			expect((String)enumrationNoCart.nextElement()).andReturn(KEY_CART_NOT_EXIST);
			expect(enumrationNoCart.hasMoreElements()).andReturn(false);
			requestNoCart.setAttribute(KEY_ITEM_STOCK, stockListNoCart);
			expect(requestNoCart.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdNoCart);
			rdNoCart.forward(requestNoCart, responseNoCart);
			requestNoCart.setAttribute(KEY_REFURL, VALUE_REFURL);
			requestNoCart.setAttribute(KEY_MESSAGE, VALUE_MESSAGE);
			replay(requestNoCart, responseNoCart, rdNoCart, sessionNoCart, enumrationNoCart);
			Method methodNoCart = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNoCart.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodNoCart.invoke(cartServlet, requestNoCart, responseNoCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：カート画面表示（カート内に商品有）
		 */
		try {
			// 初期化
			Map<Integer, Integer> stockListCart = new LinkedHashMap<Integer, Integer>();
			stockListCart.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdCart = createMock(RequestDispatcher.class);
			HttpSession sessionCart = createMock(HttpSession.class);
			Enumeration<String> enumrationCart = createMock(Enumeration.class);
			Cart cartCart = new Cart();
			cartCart.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestCart.setCharacterEncoding("UTF-8");
			responseCart.setContentType("text/html; charset=UTF-8");
			expect(requestCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestCart.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestCart.getSession()).andReturn(sessionCart);
			expect(sessionCart.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			expect(requestCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			expect(sessionCart.getAttributeNames()).andReturn(enumrationCart);
			expect(enumrationCart.hasMoreElements()).andReturn(true);
			expect((String)enumrationCart.nextElement()).andReturn(KEY_CART);
			expect(enumrationCart.hasMoreElements()).andReturn(false);
			expect((Cart)sessionCart.getAttribute(KEY_CART)).andReturn(cartCart);
			requestCart.setAttribute(KEY_ITEM_STOCK, stockListCart);
			requestCart.setAttribute(KEY_REFURL, VALUE_REFURL);
			requestCart.setAttribute(KEY_MESSAGE, VALUE_MESSAGE);
			expect(requestCart.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdCart);
			rdCart.forward(requestCart, responseCart);
			replay(requestCart, responseCart, rdCart, sessionCart, enumrationCart);
			Method methodCart = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodCart.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodCart.invoke(cartServlet, requestCart, responseCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品追加:Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListAddCartFormatError = new ArrayList<String>();
			errorMessageListAddCartFormatError.add(VALUE_MESSAGE_ITEM_FORMAT_ERROR);
			Map<Integer, Integer> stockListAddCartFormatError = new LinkedHashMap<Integer, Integer>();
			stockListAddCartFormatError.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestAddCartFormatError = createMock(HttpServletRequest.class);
			HttpServletResponse responseAddCartFormatError = createMock(HttpServletResponse.class);
			RequestDispatcher rdAddCartFormatError = createMock(RequestDispatcher.class);
			HttpSession sessionAddCartFormatError = createMock(HttpSession.class);
			Enumeration<String> enumrationAddCartFormatError = createMock(Enumeration.class);
			Cart addCartFormatError = new Cart();
			addCartFormatError.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestAddCartFormatError.setCharacterEncoding("UTF-8");
			responseAddCartFormatError.setContentType("text/html; charset=UTF-8");
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestAddCartFormatError.getSession()).andReturn(sessionAddCartFormatError);
			expect(sessionAddCartFormatError.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			expect(requestAddCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			expect(requestAddCartFormatError.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestAddCartFormatError.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			requestAddCartFormatError.setAttribute(KEY_ERROR_MESSAGE, errorMessageListAddCartFormatError);
			requestAddCartFormatError.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestAddCartFormatError.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdAddCartFormatError);
			rdAddCartFormatError.forward(requestAddCartFormatError, responseAddCartFormatError);
			replay(requestAddCartFormatError, responseAddCartFormatError, rdAddCartFormatError, sessionAddCartFormatError, enumrationAddCartFormatError);
			Method methodAddCartFormatError = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodAddCartFormatError.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodAddCartFormatError.invoke(cartServlet, requestAddCartFormatError, responseAddCartFormatError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品追加:存在しない商品IDエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListAddCartNotExist = new ArrayList<String>();
			errorMessageListAddCartNotExist.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			Map<Integer, Integer> stockListAddCartNotExist = new LinkedHashMap<Integer, Integer>();
			stockListAddCartNotExist.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestAddCartNotExist = createMock(HttpServletRequest.class);
			HttpServletResponse responseAddCartNotExist = createMock(HttpServletResponse.class);
			RequestDispatcher rdAddCartNotExist = createMock(RequestDispatcher.class);
			HttpSession sessionAddCartNotExist = createMock(HttpSession.class);
			Enumeration<String> enumrationAddCartNotExist = createMock(Enumeration.class);
			Cart addCartNotExist = new Cart();
			addCartNotExist.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestAddCartNotExist.setCharacterEncoding("UTF-8");
			responseAddCartNotExist.setContentType("text/html; charset=UTF-8");
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestAddCartNotExist.getSession()).andReturn(sessionAddCartNotExist);
			expect(sessionAddCartNotExist.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			expect(requestAddCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			expect(requestAddCartNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestAddCartNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestAddCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			
			requestAddCartNotExist.setAttribute(KEY_ERROR_MESSAGE, errorMessageListAddCartNotExist);
			requestAddCartNotExist.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestAddCartNotExist.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdAddCartNotExist);
			rdAddCartNotExist.forward(requestAddCartNotExist, responseAddCartNotExist);
			replay(requestAddCartNotExist, responseAddCartNotExist, rdAddCartNotExist, sessionAddCartNotExist, enumrationAddCartNotExist);
			Method methodAddCartNotExist = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodAddCartNotExist.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodAddCartNotExist.invoke(cartServlet, requestAddCartNotExist, responseAddCartNotExist);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：商品追加
		 */
		try {
			// 初期化
			List<String> errorMessageListAddCart = new ArrayList<String>();
			Map<Integer, Integer> stockListAddCart = new LinkedHashMap<Integer, Integer>();
			stockListAddCart.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestAddCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseAddCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdAddCart = createMock(RequestDispatcher.class);
			HttpSession sessionAddCart = createMock(HttpSession.class);
			Enumeration<String> enumrationAddCart = createMock(Enumeration.class);
			Cart addCart = new Cart();
			addCart.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestAddCart.setCharacterEncoding("UTF-8");
			responseAddCart.setContentType("text/html; charset=UTF-8");
			expect(requestAddCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestAddCart.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestAddCart.getSession()).andReturn(sessionAddCart);
			expect(sessionAddCart.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestAddCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_ADD);
			expect(requestAddCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestAddCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestAddCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestAddCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestAddCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(sessionAddCart.getAttributeNames()).andReturn(enumrationAddCart);
			expect(enumrationAddCart.hasMoreElements()).andReturn(true);
			expect((String)enumrationAddCart.nextElement()).andReturn(KEY_CART);
			expect(enumrationAddCart.hasMoreElements()).andReturn(false);
			expect((Cart)sessionAddCart.getAttribute(KEY_CART)).andReturn(addCart);
			expect(requestAddCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			sessionAddCart.setAttribute(KEY_CART, addCart);
			responseAddCart.sendRedirect(URL_ADD);
			replay(requestAddCart, responseAddCart, rdAddCart, sessionAddCart, enumrationAddCart);
			Method methodAddCart = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodAddCart.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodAddCart.invoke(cartServlet, requestAddCart, responseAddCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品削除:Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListDeleteCartFormatError = new ArrayList<String>();
			errorMessageListDeleteCartFormatError.add(VALUE_MESSAGE_ITEM_FORMAT_ERROR);
			Map<Integer, Integer> stockListDeleteCartFormatError = new LinkedHashMap<Integer, Integer>();
			stockListDeleteCartFormatError.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestDeleteCartFormatError = createMock(HttpServletRequest.class);
			HttpServletResponse responseDeleteCartFormatError = createMock(HttpServletResponse.class);
			RequestDispatcher rdDeleteCartFormatError = createMock(RequestDispatcher.class);
			HttpSession sessionDeleteCartFormatError = createMock(HttpSession.class);
			Enumeration<String> enumrationDeleteCartFormatError = createMock(Enumeration.class);
			Cart addCartFormatError = new Cart();
			addCartFormatError.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestDeleteCartFormatError.setCharacterEncoding("UTF-8");
			responseDeleteCartFormatError.setContentType("text/html; charset=UTF-8");
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestDeleteCartFormatError.getSession()).andReturn(sessionDeleteCartFormatError);
			expect(sessionDeleteCartFormatError.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			expect(requestDeleteCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			requestDeleteCartFormatError.setAttribute(KEY_ERROR_MESSAGE, errorMessageListDeleteCartFormatError);
			requestDeleteCartFormatError.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestDeleteCartFormatError.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdDeleteCartFormatError);
			rdDeleteCartFormatError.forward(requestDeleteCartFormatError, responseDeleteCartFormatError);
			replay(requestDeleteCartFormatError, responseDeleteCartFormatError, rdDeleteCartFormatError, sessionDeleteCartFormatError, enumrationDeleteCartFormatError);
			Method methodDeleteCartFormatError = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodDeleteCartFormatError.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodDeleteCartFormatError.invoke(cartServlet, requestDeleteCartFormatError, responseDeleteCartFormatError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品削除:カート情報無
		 */
		try {
			// 初期化
			List<String> errorMessageListDeleteCartNotExist = new ArrayList<String>();
			errorMessageListDeleteCartNotExist.add(MessageConstants.MESSAGE_NO_DATA);
			Map<Integer, Integer> stockListDeleteCartNotExist = new LinkedHashMap<Integer, Integer>();
			stockListDeleteCartNotExist.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestDeleteCartNotExist = createMock(HttpServletRequest.class);
			HttpServletResponse responseDeleteCartNotExist = createMock(HttpServletResponse.class);
			RequestDispatcher rdDeleteCartNotExist = createMock(RequestDispatcher.class);
			HttpSession sessionDeleteCartNotExist = createMock(HttpSession.class);
			Enumeration<String> enumrationDeleteCartNotExist = createMock(Enumeration.class);

			requestDeleteCartNotExist.setCharacterEncoding("UTF-8");
			responseDeleteCartNotExist.setContentType("text/html; charset=UTF-8");
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestDeleteCartNotExist.getSession()).andReturn(sessionDeleteCartNotExist);
			expect(sessionDeleteCartNotExist.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestDeleteCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(sessionDeleteCartNotExist.getAttributeNames()).andReturn(enumrationDeleteCartNotExist);
			expect(enumrationDeleteCartNotExist.hasMoreElements()).andReturn(false);
			requestDeleteCartNotExist.setAttribute(KEY_ERROR_MESSAGE, errorMessageListDeleteCartNotExist);
			requestDeleteCartNotExist.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestDeleteCartNotExist.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdDeleteCartNotExist);
			rdDeleteCartNotExist.forward(requestDeleteCartNotExist, responseDeleteCartNotExist);
			replay(requestDeleteCartNotExist, responseDeleteCartNotExist, rdDeleteCartNotExist, sessionDeleteCartNotExist, enumrationDeleteCartNotExist);
			Method methodDeleteCartNotExist = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodDeleteCartNotExist.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodDeleteCartNotExist.invoke(cartServlet, requestDeleteCartNotExist, responseDeleteCartNotExist);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：商品削除
		 */
		try {
			// 初期化
			Map<Integer, Integer> stockListDeleteCart = new LinkedHashMap<Integer, Integer>();
			stockListDeleteCart.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestDeleteCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseDeleteCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdDeleteCart = createMock(RequestDispatcher.class);
			HttpSession sessionDeleteCart = createMock(HttpSession.class);
			Enumeration<String> enumrationDeleteCart = createMock(Enumeration.class);
			Cart addCart = new Cart();
			addCart.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestDeleteCart.setCharacterEncoding("UTF-8");
			responseDeleteCart.setContentType("text/html; charset=UTF-8");
			expect(requestDeleteCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestDeleteCart.getSession()).andReturn(sessionDeleteCart);
			expect(sessionDeleteCart.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_DELETE);
			expect(requestDeleteCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestDeleteCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(sessionDeleteCart.getAttributeNames()).andReturn(enumrationDeleteCart);
			expect(enumrationDeleteCart.hasMoreElements()).andReturn(true);
			expect((String)enumrationDeleteCart.nextElement()).andReturn(KEY_CART);
			expect(enumrationDeleteCart.hasMoreElements()).andReturn(false);
			expect((Cart)sessionDeleteCart.getAttribute(KEY_CART)).andReturn(addCart);
			expect(requestDeleteCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			sessionDeleteCart.setAttribute(KEY_CART, addCart);
			responseDeleteCart.sendRedirect(URL_DELETE);
			replay(requestDeleteCart, responseDeleteCart, rdDeleteCart, sessionDeleteCart, enumrationDeleteCart);
			Method methodDeleteCart = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodDeleteCart.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodDeleteCart.invoke(cartServlet, requestDeleteCart, responseDeleteCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品数変更:Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListModifyCartFormatError = new ArrayList<String>();
			errorMessageListModifyCartFormatError.add(VALUE_MESSAGE_ITEM_FORMAT_ERROR);
			Map<Integer, Integer> stockListModifyCartFormatError = new LinkedHashMap<Integer, Integer>();
			stockListModifyCartFormatError.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestModifyCartFormatError = createMock(HttpServletRequest.class);
			HttpServletResponse responseModifyCartFormatError = createMock(HttpServletResponse.class);
			RequestDispatcher rdModifyCartFormatError = createMock(RequestDispatcher.class);
			HttpSession sessionModifyCartFormatError = createMock(HttpSession.class);
			Enumeration<String> enumrationModifyCartFormatError = createMock(Enumeration.class);
			Cart addCartFormatError = new Cart();
			addCartFormatError.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestModifyCartFormatError.setCharacterEncoding("UTF-8");
			responseModifyCartFormatError.setContentType("text/html; charset=UTF-8");
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestModifyCartFormatError.getSession()).andReturn(sessionModifyCartFormatError);
			expect(sessionModifyCartFormatError.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			expect(requestModifyCartFormatError.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_WRONG);
			expect(requestModifyCartFormatError.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCartFormatError.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			requestModifyCartFormatError.setAttribute(KEY_ERROR_MESSAGE, errorMessageListModifyCartFormatError);
			requestModifyCartFormatError.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestModifyCartFormatError.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdModifyCartFormatError);
			rdModifyCartFormatError.forward(requestModifyCartFormatError, responseModifyCartFormatError);
			replay(requestModifyCartFormatError, responseModifyCartFormatError, rdModifyCartFormatError, sessionModifyCartFormatError, enumrationModifyCartFormatError);
			Method methodModifyCartFormatError = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodModifyCartFormatError.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodModifyCartFormatError.invoke(cartServlet, requestModifyCartFormatError, responseModifyCartFormatError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品数変更:存在しない商品IDエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListModifyCartItemIdNotExist = new ArrayList<String>();
			errorMessageListModifyCartItemIdNotExist.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			Map<Integer, Integer> stockListModifyCartItemIdNotExist = new LinkedHashMap<Integer, Integer>();
			stockListModifyCartItemIdNotExist.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestModifyCartItemIdNotExist = createMock(HttpServletRequest.class);
			HttpServletResponse responseModifyCartItemIdNotExist = createMock(HttpServletResponse.class);
			RequestDispatcher rdModifyCartItemIdNotExist = createMock(RequestDispatcher.class);
			HttpSession sessionModifyCartItemIdNotExist = createMock(HttpSession.class);
			Enumeration<String> enumrationModifyCartItemIdNotExist = createMock(Enumeration.class);
			Cart addCartNotExist = new Cart();
			addCartNotExist.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestModifyCartItemIdNotExist.setCharacterEncoding("UTF-8");
			responseModifyCartItemIdNotExist.setContentType("text/html; charset=UTF-8");
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestModifyCartItemIdNotExist.getSession()).andReturn(sessionModifyCartItemIdNotExist);
			expect(sessionModifyCartItemIdNotExist.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartItemIdNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			expect(requestModifyCartItemIdNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			expect(requestModifyCartItemIdNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCartItemIdNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCartItemIdNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_NOT_EXIST);
			
			requestModifyCartItemIdNotExist.setAttribute(KEY_ERROR_MESSAGE, errorMessageListModifyCartItemIdNotExist);
			requestModifyCartItemIdNotExist.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestModifyCartItemIdNotExist.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdModifyCartItemIdNotExist);
			rdModifyCartItemIdNotExist.forward(requestModifyCartItemIdNotExist, responseModifyCartItemIdNotExist);
			replay(requestModifyCartItemIdNotExist, responseModifyCartItemIdNotExist, rdModifyCartItemIdNotExist, sessionModifyCartItemIdNotExist, enumrationModifyCartItemIdNotExist);
			Method methodModifyCartItemIdNotExist = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodModifyCartItemIdNotExist.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodModifyCartItemIdNotExist.invoke(cartServlet, requestModifyCartItemIdNotExist, responseModifyCartItemIdNotExist);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：商品数変更:カート情報無
		 */
		try {
			// 初期化
			List<String> errorMessageListModifyCartNotExist = new ArrayList<String>();
			errorMessageListModifyCartNotExist.add(MessageConstants.MESSAGE_NO_DATA);
			Map<Integer, Integer> stockListModifyCartNotExist = new LinkedHashMap<Integer, Integer>();
			stockListModifyCartNotExist.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestModifyCartNotExist = createMock(HttpServletRequest.class);
			HttpServletResponse responseModifyCartNotExist = createMock(HttpServletResponse.class);
			RequestDispatcher rdModifyCartNotExist = createMock(RequestDispatcher.class);
			HttpSession sessionModifyCartNotExist = createMock(HttpSession.class);
			Enumeration<String> enumrationModifyCartNotExist = createMock(Enumeration.class);

			requestModifyCartNotExist.setCharacterEncoding("UTF-8");
			responseModifyCartNotExist.setContentType("text/html; charset=UTF-8");
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestModifyCartNotExist.getSession()).andReturn(sessionModifyCartNotExist);
			expect(sessionModifyCartNotExist.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestModifyCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestModifyCartNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCartNotExist.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCartNotExist.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(sessionModifyCartNotExist.getAttributeNames()).andReturn(enumrationModifyCartNotExist);
			expect(enumrationModifyCartNotExist.hasMoreElements()).andReturn(false);
			requestModifyCartNotExist.setAttribute(KEY_ERROR_MESSAGE, errorMessageListModifyCartNotExist);
			requestModifyCartNotExist.setAttribute(KEY_REFURL, VALUE_REFURL);
			expect(requestModifyCartNotExist.getRequestDispatcher("/WEB-INF/service/function/cart/Cart.jsp")).andReturn(rdModifyCartNotExist);
			rdModifyCartNotExist.forward(requestModifyCartNotExist, responseModifyCartNotExist);
			replay(requestModifyCartNotExist, responseModifyCartNotExist, rdModifyCartNotExist, sessionModifyCartNotExist, enumrationModifyCartNotExist);
			Method methodModifyCartNotExist = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodModifyCartNotExist.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodModifyCartNotExist.invoke(cartServlet, requestModifyCartNotExist, responseModifyCartNotExist);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：商品数変更
		 */
		try {
			// 初期化
			List<String> errorMessageListModifyCart = new ArrayList<String>();
			Map<Integer, Integer> stockListModifyCart = new LinkedHashMap<Integer, Integer>();
			stockListModifyCart.put(CART_ITEM_ID, CART_ITEM_STOCK);
			HttpServletRequest requestModifyCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseModifyCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdModifyCart = createMock(RequestDispatcher.class);
			HttpSession sessionModifyCart = createMock(HttpSession.class);
			Enumeration<String> enumrationModifyCart = createMock(Enumeration.class);
			Cart addCart = new Cart();
			addCart.addItem(CART_ITEM_ID, CART_ITEM_NAME, CART_ITEM_PRICE, CART_ITEM_COUNT);

			requestModifyCart.setCharacterEncoding("UTF-8");
			responseModifyCart.setContentType("text/html; charset=UTF-8");
			expect(requestModifyCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCart.getParameter(REQUEST_KEY_MESSAGE)).andReturn(VALUE_MESSAGE);
			expect(requestModifyCart.getParameter(REQUEST_KEY_REFURL)).andReturn(VALUE_REFURL);
			expect(requestModifyCart.getSession()).andReturn(sessionModifyCart);
			expect(sessionModifyCart.getAttribute(KEY_LOGIN_ID)).andReturn(VALUE_LOGIN_ID);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_MODIFY);
			expect(requestModifyCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestModifyCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(requestModifyCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			expect(requestModifyCart.getParameter(KEY_ITEM_ID)).andReturn(CART_ITEM_ID_STRING);
			expect(sessionModifyCart.getAttributeNames()).andReturn(enumrationModifyCart);
			expect(enumrationModifyCart.hasMoreElements()).andReturn(true);
			expect((String)enumrationModifyCart.nextElement()).andReturn(KEY_CART);
			expect(enumrationModifyCart.hasMoreElements()).andReturn(false);
			expect((Cart)sessionModifyCart.getAttribute(KEY_CART)).andReturn(addCart);
			expect(requestModifyCart.getParameter(KEY_ITEM_COUNT)).andReturn(CART_ITEM_COUNT_STRING);
			sessionModifyCart.setAttribute(KEY_CART, addCart);
			responseModifyCart.sendRedirect(URL_MODIFY);
			replay(requestModifyCart, responseModifyCart, rdModifyCart, sessionModifyCart, enumrationModifyCart);
			Method methodModifyCart = CartServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodModifyCart.setAccessible(true);
			// 実行
			CartServlet cartServlet = new CartServlet();
			methodModifyCart.invoke(cartServlet, requestModifyCart, responseModifyCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {
	}

}
