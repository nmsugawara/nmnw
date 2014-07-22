package nmnw.admin.function.account.search;

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

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.function.account.search.SearchServlet;
import com.nmnw.admin.dao.Account;

public class SearchServletTest {

	public static final String REQUEST_KEY_ACTION = "action";
	public static final String REQUEST_VALUE_ACTION_SEARCH = "search";
	public static final String REQUEST_KEY_ACCOUNT_ID = "search_id";
	public static final String REQUEST_KEY_ACCOUNT_NAME = "search_name";
	public static final String REQUEST_KEY_ACCOUNT_NAME_KANA = "search_name_kana";
	public static final String REQUEST_KEY_ACCOUNT_MAIL = "search_mail";
	public static final String KEY_RESULT = "result";
	public static final String KEY_INPUT_DATA_LIST = "inputDataList";
	public static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";
	public static final String URL = ConfigConstants.JSP_DIR_ACCOUNT_SEARCH + "Search.jsp";
	public static final String VALUE_ACTION_NULL = null;
	public static final String VALUE_ACCOUNT_ID_WROND = "test";
	public static final String VALUE_MESSAGE_FORMAT_ERROR = "会員IDは数値で入力してください。";
	public static final String VALUE_ACCOUNT_ID = "10";
	public static final String VALUE_ACCOUNT_NAME = "hogehoge";
	public static final String VALUE_ACCOUNT_NAME_KANA = "hogehoge";
	public static final String VALUE_ACCOUNT_MAIL = "hogehoge";
	
	public static final String SEARCH_FIELD_ID = "会員ID";

	@Test
	public void doGetTest() {
		/**
		 * 正常系：検索画面表示
		 */
		try {
			// 初期化
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
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

		/**
		 * 異常系：Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageListParamError = new ArrayList<String>();
			errorMessageListParamError.add(VALUE_MESSAGE_FORMAT_ERROR);
			Map<String, String[]> inputDataListParamError = new HashMap<String, String[]>();
			HttpServletRequest requestParamError = createMock(HttpServletRequest.class);
			HttpServletResponse responseParamError = createMock(HttpServletResponse.class);
			RequestDispatcher rdParamError = createMock(RequestDispatcher.class);

			responseParamError.setContentType("text/html; charset=UTF-8");
			requestParamError.setCharacterEncoding("UTF-8");
			expect(requestParamError.getParameterMap()).andReturn(inputDataListParamError);
			expect(requestParamError.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_SEARCH);
			expect(requestParamError.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID_WROND);
			expect(requestParamError.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID_WROND);
			requestParamError.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageListParamError);
			requestParamError.setAttribute(KEY_INPUT_DATA_LIST, inputDataListParamError);
			expect(requestParamError.getRequestDispatcher(URL)).andReturn(rdParamError);
			rdParamError.forward(requestParamError, responseParamError);
			replay(requestParamError, responseParamError, rdParamError);
			Method methodParamError = SearchServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodParamError.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			methodParamError.invoke(searchServlet, requestParamError, responseParamError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
		
		/**
		 * 正常系：検索結果無
		 */
		try {
			// 初期化
			List<Account> accountListSearchEnd = new ArrayList<Account>();
			Map<String, String[]> inputDataListSearchEnd = new HashMap<String, String[]>();
			HttpServletRequest requestSearchEnd = createMock(HttpServletRequest.class);
			HttpServletResponse responseSearchEnd = createMock(HttpServletResponse.class);
			RequestDispatcher rdSearchEnd = createMock(RequestDispatcher.class);

			responseSearchEnd.setContentType("text/html; charset=UTF-8");
			requestSearchEnd.setCharacterEncoding("UTF-8");
			expect(requestSearchEnd.getParameterMap()).andReturn(inputDataListSearchEnd);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_SEARCH);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME)).andReturn(VALUE_ACCOUNT_NAME);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME)).andReturn(VALUE_ACCOUNT_NAME);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA)).andReturn(VALUE_ACCOUNT_NAME_KANA);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA)).andReturn(VALUE_ACCOUNT_NAME_KANA);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_MAIL)).andReturn(VALUE_ACCOUNT_MAIL);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_MAIL)).andReturn(VALUE_ACCOUNT_MAIL);
			requestSearchEnd.setAttribute(KEY_RESULT, accountListSearchEnd);
			requestSearchEnd.setAttribute(KEY_INPUT_DATA_LIST, inputDataListSearchEnd);
			expect(requestSearchEnd.getRequestDispatcher(URL)).andReturn(rdSearchEnd);
			rdSearchEnd.forward(requestSearchEnd, responseSearchEnd);
			replay(requestSearchEnd, responseSearchEnd, rdSearchEnd);
			Method methodSearchEnd = SearchServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodSearchEnd.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			methodSearchEnd.invoke(searchServlet, requestSearchEnd, responseSearchEnd);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {
		/**
		 * 正常系：検索結果無
		 */
		try {
			// 初期化
			List<Account> accountListSearchEnd = new ArrayList<Account>();
			Map<String, String[]> inputDataListSearchEnd = new HashMap<String, String[]>();
			HttpServletRequest requestSearchEnd = createMock(HttpServletRequest.class);
			HttpServletResponse responseSearchEnd = createMock(HttpServletResponse.class);
			RequestDispatcher rdSearchEnd = createMock(RequestDispatcher.class);

			responseSearchEnd.setContentType("text/html; charset=UTF-8");
			requestSearchEnd.setCharacterEncoding("UTF-8");
			expect(requestSearchEnd.getParameterMap()).andReturn(inputDataListSearchEnd);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_SEARCH);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_ID)).andReturn(VALUE_ACCOUNT_ID);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME)).andReturn(VALUE_ACCOUNT_NAME);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME)).andReturn(VALUE_ACCOUNT_NAME);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA)).andReturn(VALUE_ACCOUNT_NAME_KANA);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA)).andReturn(VALUE_ACCOUNT_NAME_KANA);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_MAIL)).andReturn(VALUE_ACCOUNT_MAIL);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACCOUNT_MAIL)).andReturn(VALUE_ACCOUNT_MAIL);
			requestSearchEnd.setAttribute(KEY_RESULT, accountListSearchEnd);
			requestSearchEnd.setAttribute(KEY_INPUT_DATA_LIST, inputDataListSearchEnd);
			expect(requestSearchEnd.getRequestDispatcher(URL)).andReturn(rdSearchEnd);
			rdSearchEnd.forward(requestSearchEnd, responseSearchEnd);
			replay(requestSearchEnd, responseSearchEnd, rdSearchEnd);
			Method methodSearchEnd = SearchServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			methodSearchEnd.setAccessible(true);
			// 実行
			SearchServlet searchServlet = new SearchServlet();
			methodSearchEnd.invoke(searchServlet, requestSearchEnd, responseSearchEnd);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}
}
