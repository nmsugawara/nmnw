package nmnw.service.function.order.search;

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
import com.nmnw.service.function.order.search.SearchServlet;

public class SearchServletTest {
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
	public static final String URL = ConfigConstants.JSP_DIR_ORDER_SEARCH + "Search.jsp";
	public static final String VALUE_ID = "7";
	public static final String VALUE_ID_NULL = null;
	public static final String VALUE_ORDER_PERIOD = "-1";
	public static final String VALUE_ORDER_PERIOD_NO_DATA = "";

	@Test
	public void doGetTest() {
		/**
		 * 異常系：ログインしていない
		 */
		try {
			// 初期化
			List<String> errorMessageListNotLogin = new ArrayList<String>();
			errorMessageListNotLogin.add(MessageConstants.MESSAGE_NOT_LOGIN);
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest requestNotLogin = createMock(HttpServletRequest.class);
			HttpServletResponse responseNotLogin = createMock(HttpServletResponse.class);
			RequestDispatcher rdNotLogin = createMock(RequestDispatcher.class);
			HttpSession sessionNotLogin = createMock(HttpSession.class);

			responseNotLogin.setContentType("text/html; charset=UTF-8");
			requestNotLogin.setCharacterEncoding("UTF-8");
			expect(requestNotLogin.getParameterMap()).andReturn(inputDataList);
			expect(requestNotLogin.getSession()).andReturn(sessionNotLogin);
			expect(sessionNotLogin.getAttribute(KEY_ID)).andReturn(VALUE_ID_NULL);
			requestNotLogin.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageListNotLogin);
			expect(requestNotLogin.getRequestDispatcher(URL)).andReturn(rdNotLogin);
			rdNotLogin.forward(requestNotLogin, responseNotLogin);
			replay(requestNotLogin, responseNotLogin, rdNotLogin, sessionNotLogin);
			Method methodNotLogin = SearchServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodNotLogin.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			methodNotLogin.invoke(searchServlet, requestNotLogin, responseNotLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：対象データ無
		 */
		try {
			// 初期化
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getSession()).andReturn(session);
			expect(session.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			expect((Integer)session.getAttribute(KEY_ID)).andReturn(Integer.valueOf(VALUE_ID));
			expect(request.getParameter(KEY_ORDER_PERIOD)).andReturn(VALUE_ORDER_PERIOD);
			expect(request.getParameter(KEY_ORDER_PERIOD)).andReturn(VALUE_ORDER_PERIOD);
			request.setAttribute(KEY_RESULT, resultList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd, session);
			Method method = SearchServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			method.invoke(searchServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {

		/**
		 * 正常系：対象データ無
		 */
		try {
			// 初期化
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getSession()).andReturn(session);
			expect(session.getAttribute(KEY_ID)).andReturn(VALUE_ID);
			expect((Integer)session.getAttribute(KEY_ID)).andReturn(Integer.valueOf(VALUE_ID));
			expect(request.getParameter(KEY_ORDER_PERIOD)).andReturn(VALUE_ORDER_PERIOD);
			expect(request.getParameter(KEY_ORDER_PERIOD)).andReturn(VALUE_ORDER_PERIOD);
			request.setAttribute(KEY_RESULT, resultList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd, session);
			Method method = SearchServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			method.invoke(searchServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}
}
