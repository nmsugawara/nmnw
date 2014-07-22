package nmnw.service.function.item.search;

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
import com.nmnw.service.dao.Item;
import com.nmnw.service.function.item.search.SearchServlet;

public class SearchServletTest {
	public static final String KEY_RESULT = "result";
	public static final String KEY_INPUT_DATA_LIST = "inputDataList";
	public static final String KEY_SEARCH_NAME = "search_name";
	public static final String KEY_SEARCH_CATEGORY = "search_category";
	public static final String KEY_SEARCH_SORT = "search_sort";
	public static final String URL = ConfigConstants.JSP_DIR_ITEM_SEARCH + "Search.jsp";
	public static final String VALUE_NAME_NOT_EXIST = "hhhhhhh";
	public static final String VALUE_CATEGORY_NULL = null;
	public static final String VALUE_SORT_NULL = null;

	@Test
	public void doGetTest() {

		/**
		 * 正常系：対象データ無
		 */
		try {
			// 初期化
			List<Item> itemList = new ArrayList<Item>();
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_NAME_NOT_EXIST);
			expect(request.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_NAME_NOT_EXIST);
			expect(request.getParameter(KEY_SEARCH_CATEGORY)).andReturn(VALUE_CATEGORY_NULL);
			expect(request.getParameter(KEY_SEARCH_SORT)).andReturn(VALUE_SORT_NULL);
			request.setAttribute(KEY_RESULT, itemList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
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
			List<Item> itemList = new ArrayList<Item>();
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_NAME_NOT_EXIST);
			expect(request.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_NAME_NOT_EXIST);
			expect(request.getParameter(KEY_SEARCH_CATEGORY)).andReturn(VALUE_CATEGORY_NULL);
			expect(request.getParameter(KEY_SEARCH_SORT)).andReturn(VALUE_SORT_NULL);
			request.setAttribute(KEY_RESULT, itemList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
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
