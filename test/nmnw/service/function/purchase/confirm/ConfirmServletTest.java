package nmnw.service.function.purchase.confirm;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
import static org.easymock.EasyMock.*;
import static org.junit.Assume.*;

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
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.Cart;
import com.nmnw.service.function.purchase.confirm.ConfirmServlet;

public class ConfirmServletTest {
	public static final String KEY_CART = "cart";
	public static final String KEY_ACCOUNT = "account";
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String URL = ConfigConstants.JSP_DIR_PURCHASE_CONFIRM + "Confirm.jsp";
	public static final String KEY_ID = "id";
	public static final String VALUE_ID = "7";
	public static final String VALUE_ID_NULL = null;
	public static final String VALUE_CART_NULL = null;

	@Test
	public void doGetTest() {
		/**
		 * 異常系：ログインしていない
		 */
		try {
			// 初期化
			List<String> errorMessageListNotLogin = new ArrayList<String>();
			errorMessageListNotLogin.add(MessageConstants.MESSAGE_NOT_LOGIN);
			HttpServletRequest requestNotLogin = createMock(HttpServletRequest.class);
			HttpServletResponse responseNotLogin = createMock(HttpServletResponse.class);
			RequestDispatcher rdNotLogin = createMock(RequestDispatcher.class);
			HttpSession sessionNotLogin = createMock(HttpSession.class);

			responseNotLogin.setContentType("text/html; charset=UTF-8");
			requestNotLogin.setCharacterEncoding("UTF-8");
			expect(requestNotLogin.getSession()).andReturn(sessionNotLogin);
			expect(sessionNotLogin.getAttribute(KEY_ID)).andReturn(VALUE_ID_NULL);
			requestNotLogin.setAttribute(KEY_ERROR_MESSAGE, errorMessageListNotLogin);
			expect(requestNotLogin.getRequestDispatcher(URL)).andReturn(rdNotLogin);
			rdNotLogin.forward(requestNotLogin, responseNotLogin);
			replay(requestNotLogin, responseNotLogin, rdNotLogin, sessionNotLogin);
			Method methodNotLogin = ConfirmServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotLogin.setAccessible(true);
			// 実行
			ConfirmServlet confirmServlet = new ConfirmServlet();
			methodNotLogin.invoke(confirmServlet, requestNotLogin, responseNotLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：カートオブジェクト無
		 */
		try {
			// 初期化
			List<String> errorMessageListNoCart = new ArrayList<String>();
			errorMessageListNoCart.add(MessageConstants.MESSAGE_NO_CART_DATA);
			HttpServletRequest requestNoCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseNoCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdNoCart = createMock(RequestDispatcher.class);
			HttpSession sessionNoCart = createMock(HttpSession.class);

			responseNoCart.setContentType("text/html; charset=UTF-8");
			requestNoCart.setCharacterEncoding("UTF-8");
			expect(requestNoCart.getSession()).andReturn(sessionNoCart);
			expect(sessionNoCart.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			expect((Integer)sessionNoCart.getAttribute(KEY_ID)).andReturn(Integer.valueOf(VALUE_ID));
			expect(sessionNoCart.getAttribute(KEY_CART)).andReturn(VALUE_CART_NULL);
			requestNoCart.setAttribute(KEY_ERROR_MESSAGE, errorMessageListNoCart);
			expect(requestNoCart.getRequestDispatcher(URL)).andReturn(rdNoCart);
			rdNoCart.forward(requestNoCart, responseNoCart);
			replay(requestNoCart, responseNoCart, rdNoCart, sessionNoCart);
			Method methodNoCart = ConfirmServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNoCart.setAccessible(true);
			// 実行
			ConfirmServlet confirmServlet = new ConfirmServlet();
			methodNoCart.invoke(confirmServlet, requestNoCart, responseNoCart);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系
		 */
		try {
			// 初期化
			HttpServletRequest requestCart = createMock(HttpServletRequest.class);
			HttpServletResponse responseCart = createMock(HttpServletResponse.class);
			RequestDispatcher rdCart = createMock(RequestDispatcher.class);
			HttpSession sessionCart = createMock(HttpSession.class);
			Cart cart = new Cart();

			responseCart.setContentType("text/html; charset=UTF-8");
			requestCart.setCharacterEncoding("UTF-8");
			expect(requestCart.getSession()).andReturn(sessionCart);
			expect(sessionCart.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			expect((Integer)sessionCart.getAttribute(KEY_ID)).andReturn(Integer.valueOf(VALUE_ID));
			expect(sessionCart.getAttribute(KEY_CART)).andReturn(cart);
			requestCart.setAttribute(KEY_ACCOUNT, Account.class);
			expect(requestCart.getRequestDispatcher(URL)).andReturn(rdCart);
			rdCart.forward(requestCart, responseCart);
			replay(requestCart, responseCart, rdCart, sessionCart);
			Method methodCart = ConfirmServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodCart.setAccessible(true);
			// 実行
			ConfirmServlet confirmServlet = new ConfirmServlet();
			methodCart.invoke(confirmServlet, requestCart, responseCart);
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
