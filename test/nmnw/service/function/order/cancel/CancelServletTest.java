package nmnw.service.function.order.cancel;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
import static org.easymock.EasyMock.*;
import static org.junit.Assume.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Order;
import com.nmnw.service.function.order.cancel.CancelServlet;

public class CancelServletTest {
	public static final String FIELD_ORDER_ID = "注文ID";
	public static final String SESSION_KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String SESSION_KEY_ORDER_ID = "orderId";
	public static final String KEY_ORDER_ID = "order_id";
	public static final String KEY_ID = "id";

	public static final String KEY_ORDER_PERIOD = "order_period";
	public static final String KEY_ORDER = "order";
	public static final String KEY_ORDER_DETAIL = "orderDetail";
	public static final String KEY_ORDER_STATUS = "orderStatus";
	public static final String KEY_RESULT = "result";
	public static final String KEY_INPUT_DATA_LIST = "inputDataList";
	public static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";
	public static final String ORDER_STATUS_ORDER = "注文済";
	public static final String ORDER_STATUS_ORDER_CANCEL = "キャンセル";
	public static final String ORDER_STATUS_SHIPPING = "発送済";
	public static final String ORDER_STATUS_SHIPPING_CANCEL = "発送後キャンセル";
	public static final String URL = ConfigConstants.JSP_DIR_ORDER_CANCEL + "Cancel.jsp";
	public static final String VALUE_ID = "7";
	public static final String VALUE_ID_NULL = null;
	public static final String VALUE_ORDER_ID = "10";
	public static final String VALUE_ORDER_ID_WRONG = "aaa";
	public static final String VALUE_ORDER_ID_NOT_EXIST = "9999";
	public static final String VALUE_ERROR_MESSAGE = "注文IDは数値で入力してください。";
	
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
			expect(requestNotLogin.getParameter(KEY_ORDER_ID)).andReturn(VALUE_ORDER_ID);
			expect(requestNotLogin.getSession()).andReturn(sessionNotLogin);
			expect(sessionNotLogin.getAttribute(KEY_ID)).andReturn(VALUE_ID_NULL);
			requestNotLogin.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageListNotLogin);
			expect(requestNotLogin.getRequestDispatcher(URL)).andReturn(rdNotLogin);
			rdNotLogin.forward(requestNotLogin, responseNotLogin);
			replay(requestNotLogin, responseNotLogin, rdNotLogin, sessionNotLogin);
			Method methodNotLogin = CancelServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotLogin.setAccessible(true);
			// 実行
			CancelServlet cancelServlet = new CancelServlet();
			methodNotLogin.invoke(cancelServlet, requestNotLogin, responseNotLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListParameterFormatError = new ArrayList<String>();
			errorMessageListParameterFormatError.add(VALUE_ERROR_MESSAGE);
			HttpServletRequest requestParameterFormatError = createMock(HttpServletRequest.class);
			HttpServletResponse responseParameterFormatError = createMock(HttpServletResponse.class);
			RequestDispatcher rdParameterFormatError = createMock(RequestDispatcher.class);
			HttpSession sessionParameterFormatError = createMock(HttpSession.class);

			responseParameterFormatError.setContentType("text/html; charset=UTF-8");
			requestParameterFormatError.setCharacterEncoding("UTF-8");
			expect(requestParameterFormatError.getParameter(KEY_ORDER_ID)).andReturn(VALUE_ORDER_ID_WRONG);
			expect(requestParameterFormatError.getSession()).andReturn(sessionParameterFormatError);
			expect(sessionParameterFormatError.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			requestParameterFormatError.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageListParameterFormatError);
			expect(requestParameterFormatError.getRequestDispatcher(URL)).andReturn(rdParameterFormatError);
			rdParameterFormatError.forward(requestParameterFormatError, responseParameterFormatError);
			replay(requestParameterFormatError, responseParameterFormatError, rdParameterFormatError, sessionParameterFormatError);
			Method methodParameterFormatError = CancelServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodParameterFormatError.setAccessible(true);
			// 実行
			CancelServlet cancelServlet = new CancelServlet();
			methodParameterFormatError.invoke(cancelServlet, requestParameterFormatError, responseParameterFormatError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	

		/**
		 * 異常系：存在しない注文ID
		 */
		try {
			// 初期化
			List<String> errorMessageListNotExist = new ArrayList<String>();
			errorMessageListNotExist.add(MessageConstants.MESSAGE_NO_DATA);
			HttpServletRequest requestNotExist = createMock(HttpServletRequest.class);
			HttpServletResponse responseNotExist = createMock(HttpServletResponse.class);
			RequestDispatcher rdNotExist = createMock(RequestDispatcher.class);
			HttpSession sessionNotExist = createMock(HttpSession.class);

			responseNotExist.setContentType("text/html; charset=UTF-8");
			requestNotExist.setCharacterEncoding("UTF-8");
			expect(requestNotExist.getParameter(KEY_ORDER_ID)).andReturn(VALUE_ORDER_ID_NOT_EXIST);
			expect(requestNotExist.getSession()).andReturn(sessionNotExist);
			expect(sessionNotExist.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			requestNotExist.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageListNotExist);
			expect(requestNotExist.getRequestDispatcher(URL)).andReturn(rdNotExist);
			rdNotExist.forward(requestNotExist, responseNotExist);
			replay(requestNotExist, responseNotExist, rdNotExist, sessionNotExist);
			Method methodNotExist = CancelServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotExist.setAccessible(true);
			// 実行
			CancelServlet cancelServlet = new CancelServlet();
			methodNotExist.invoke(cancelServlet, requestNotExist, responseNotExist);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：キャンセル
		 */
		try {
			// 初期化
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameter(KEY_ORDER_ID)).andReturn(VALUE_ORDER_ID);
			expect(request.getSession()).andReturn(session);
			expect(session.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			request.setAttribute(SESSION_KEY_ORDER_ID, VALUE_ORDER_ID);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd, session);
			Method method = CancelServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			CancelServlet cancelServlet = new CancelServlet();
			method.invoke(cancelServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {
		/**
		 * 正常系：キャンセル
		 */
		try {
			// 初期化
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameter(KEY_ORDER_ID)).andReturn(VALUE_ORDER_ID);
			expect(request.getSession()).andReturn(session);
			expect(session.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			request.setAttribute(SESSION_KEY_ORDER_ID, VALUE_ORDER_ID);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd, session);
			Method method = CancelServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			CancelServlet cancelServlet = new CancelServlet();
			method.invoke(cancelServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}
}
