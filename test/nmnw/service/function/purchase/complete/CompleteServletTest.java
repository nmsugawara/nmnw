package nmnw.service.function.purchase.complete;

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
import com.nmnw.service.dao.Order;
import com.nmnw.service.dao.OrderDetail;
import com.nmnw.service.function.purchase.complete.CompleteServlet;

public class CompleteServletTest {
	public static final String KEY_ACCOUNT_ID = "id";
	public static final String KEY_CART = "cart";
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION = "complete";
	public static final String KEY_ORDER = "order";
	public static final String KEY_ORDER_DETAIL = "orderDetail";
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String MAIL_CODE = "purchase_complete";
	public static final String URL = ConfigConstants.JSP_DIR_PURCHASE_COMPLETE + "Complete.jsp";
	public static final String VALUE_ID = "7";
	public static final String VALUE_ID_NULL = null;
	public static final String VALUE_CART_NULL = null;
	public static final String VALUE_ACTION_NULL = null;
	
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
			expect(sessionNotLogin.getAttribute(KEY_ACCOUNT_ID)).andReturn(VALUE_ID_NULL);
			requestNotLogin.setAttribute(KEY_ERROR_MESSAGE, errorMessageListNotLogin);
			expect(requestNotLogin.getRequestDispatcher(URL)).andReturn(rdNotLogin);
			rdNotLogin.forward(requestNotLogin, responseNotLogin);
			replay(requestNotLogin, responseNotLogin, rdNotLogin, sessionNotLogin);
			Method methodNotLogin = CompleteServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotLogin.setAccessible(true);
			// 実行
			CompleteServlet completeServlet = new CompleteServlet();
			methodNotLogin.invoke(completeServlet, requestNotLogin, responseNotLogin);
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
			expect(sessionNoCart.getAttribute(KEY_ACCOUNT_ID)).andReturn(VALUE_ID);
			expect(sessionNoCart.getAttribute(KEY_CART)).andReturn(VALUE_CART_NULL);
			requestNoCart.setAttribute(KEY_ERROR_MESSAGE, errorMessageListNoCart);
			expect(requestNoCart.getRequestDispatcher(URL)).andReturn(rdNoCart);
			rdNoCart.forward(requestNoCart, responseNoCart);
			replay(requestNoCart, responseNoCart, rdNoCart, sessionNoCart);
			Method methodNoCart = CompleteServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNoCart.setAccessible(true);
			// 実行
			CompleteServlet completeServlet = new CompleteServlet();
			methodNoCart.invoke(completeServlet, requestNoCart, responseNoCart);
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
			List<String> errorMessageListActionParamError = new ArrayList<String>();
			errorMessageListActionParamError.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			HttpServletRequest requestActionParamError = createMock(HttpServletRequest.class);
			HttpServletResponse responseActionParamError = createMock(HttpServletResponse.class);
			RequestDispatcher rdActionParamError = createMock(RequestDispatcher.class);
			HttpSession sessionActionParamError = createMock(HttpSession.class);
			Cart cartActionParamError = new Cart();

			responseActionParamError.setContentType("text/html; charset=UTF-8");
			requestActionParamError.setCharacterEncoding("UTF-8");
			expect(requestActionParamError.getSession()).andReturn(sessionActionParamError);
			expect(sessionActionParamError.getAttribute(KEY_ACCOUNT_ID)).andReturn(VALUE_ID);
			expect(sessionActionParamError.getAttribute(KEY_CART)).andReturn(cartActionParamError);
			expect(requestActionParamError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			requestActionParamError.setAttribute(KEY_ERROR_MESSAGE, errorMessageListActionParamError);
			expect(requestActionParamError.getRequestDispatcher(URL)).andReturn(rdActionParamError);
			rdActionParamError.forward(requestActionParamError, responseActionParamError);
			replay(requestActionParamError, responseActionParamError, rdActionParamError, sessionActionParamError);
			Method methodActionParamError = CompleteServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodActionParamError.setAccessible(true);
			// 実行
			CompleteServlet completeServlet = new CompleteServlet();
			methodActionParamError.invoke(completeServlet, requestActionParamError, responseActionParamError);
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
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_NO_CART_DATA);
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);
			Cart cart = new Cart();

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getSession()).andReturn(session);
			expect(session.getAttribute(KEY_ACCOUNT_ID)).andReturn(VALUE_ID);
			expect(session.getAttribute(KEY_CART)).andReturn(cart);
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION);
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION);
			expect((Integer)session.getAttribute(KEY_ACCOUNT_ID)).andReturn(Integer.valueOf(VALUE_ID));
			expect((Cart)session.getAttribute(KEY_CART)).andReturn(cart);
			session.removeAttribute(KEY_CART);
			request.setAttribute(KEY_ORDER, Order.class);
			request.setAttribute(KEY_ORDER_DETAIL, OrderDetail.class);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd, session);
			Method method = CompleteServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			CompleteServlet completeServlet = new CompleteServlet();
			method.invoke(completeServlet, request, response);
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
