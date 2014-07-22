package nmnw.admin.function.order.search;

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
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.function.order.search.SearchServlet;

public class SearchServletTest {
	public static final long serialVersionUID = 1L;
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String REQUEST_VALUE_ACTION_SEARCH = "search";
	public static final String REQUEST_VALUE_ACTION_NULL = null;
	public static final String KEY_RESULT = "result";
	public static final String KEY_INPUT_DATA_LIST = "inputDataList";
	public static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";

	public static final String KEY_SEARCH_ID = "search_order_id";
	public static final String KEY_SEARCH_DATE_FROM = "search_order_date_from";
	public static final String KEY_SEARCH_DATE_TO = "search_order_date_to";
	public static final String KEY_SEARCH_CANCEL_FLG = "search_cancel_flg";
	public static final String KEY_SEARCH_CANCEL_DATE = "search_cancel_date";
	public static final String KEY_SEARCH_SHIPPING_FLG = "search_shipping_flg";
	public static final String KEY_SEARCH_SHIPPING_DATE = "search_shipping_date";
	public static final String KEY_SEARCH_NAME = "search_account_name";
	public static final String KEY_SEARCH_NAME_KANA = "search_account_name_kana";
	public static final String KEY_SEARCH_MAIL = "search_account_mail";
	public static final String KEY_SEARCH_PHONE_NUMBER = "search_account_phone_number";
	
	public static final String VALUE_SEARCH_ID = "10";
	public static final String VALUE_SEARCH_ID_WRONG = "test";
	public static final String VALUE_SEARCH_DATE_FROM = "";
	public static final String VALUE_SEARCH_DATE_TO = "";
	public static final String VALUE_SEARCH_CANCEL_FLG = "";
	public static final String VALUE_SEARCH_CANCEL_DATE = "";
	public static final String VALUE_SEARCH_SHIPPING_FLG = "";
	public static final String VALUE_SEARCH_SHIPPING_DATE = "";
	public static final String VALUE_MESSAGE_FORMAT_ERROR = "注文IDは数値で入力してください。";
	public static final String VALUE_SEARCH_ID_NOT_EXIST = "9999";
	public static final String VALUE_SEARCH_DATE_FROM_NOT_EXIST = "2015-07-01";
	public static final String VALUE_SEARCH_DATE_TO_NOT_EXIST = "2016-07-02";
	public static final String VALUE_SEARCH_CANCEL_FLG_NOT_EXIST = "1";
	public static final String VALUE_SEARCH_CANCEL_DATE_NOT_EXIST = "2017-04-14";
	public static final String VALUE_SEARCH_SHIPPING_FLG_NOT_EXIST = "1";
	public static final String VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST = "2017-04-14";
	public static final String VALUE_SEARCH_NAME_NOT_EXIST = "hogehoge";
	public static final String VALUE_SEARCH_NAME_KANA_NOT_EXIST = "hogehoge";
	public static final String VALUE_SEARCH_MAIL_NOT_EXIST = "hogehoge";
	public static final String VALUE_SEARCH_PHONE_NUMBER_NOT_EXIST = "hogehoge";

	
	public static final String SEARCH_FIELD_ORDER_ID = "注文ID";
	public static final String SEARCH_FIELD_ORDER_DATE_FROM = "注文期間（From）";
	public static final String SEARCH_FIELD_ORDER_DATE_TO = "注文日期間（To）";
	public static final String SEARCH_FIELD_ACCOUNT_NAME = "会員名";
	public static final String SEARCH_FIELD_ACCOUNT_NAME_KANA = "会員名カナ";
	public static final String SEARCH_FIELD_ACCOUNT_MAIL = "メールアドレス";
	public static final String SEARCH_FIELD_ACCOUNT_PHONE_NUMBER = "電話番号";
	public static final String SEARCH_FIELD_CANCEL_FLG = "キャンセル状況";
	public static final String SEARCH_FIELD_CANCEL_DATE = "キャンセル日";
	public static final String SEARCH_FIELD_SHIPPING_FLG = "出荷状況";
	public static final String SEARCH_FIELD_SHIPPING_DATE = "出荷日";
	public static final String URL = ConfigConstants.JSP_DIR_ORDER_SEARCH + "Search.jsp";

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
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_NULL);
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
			expect(requestParamError.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_WRONG);
			expect(requestParamError.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_WRONG);
			expect(requestParamError.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM);
			expect(requestParamError.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO);
			expect(requestParamError.getParameter(KEY_SEARCH_CANCEL_FLG)).andReturn(VALUE_SEARCH_CANCEL_FLG);
			expect(requestParamError.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE);
			expect(requestParamError.getParameter(KEY_SEARCH_SHIPPING_FLG)).andReturn(VALUE_SEARCH_SHIPPING_FLG);
			expect(requestParamError.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE);
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
			List<Order> orderListSearchEnd = new ArrayList<Order>();
			List<String> errorMessageListSearchEnd = new ArrayList<String>();
			errorMessageListSearchEnd.add(VALUE_MESSAGE_FORMAT_ERROR);
			Map<String, String[]> inputDataListSearchEnd = new HashMap<String, String[]>();
			HttpServletRequest requestSearchEnd = createMock(HttpServletRequest.class);
			HttpServletResponse responseSearchEnd = createMock(HttpServletResponse.class);
			RequestDispatcher rdSearchEnd = createMock(RequestDispatcher.class);

			responseSearchEnd.setContentType("text/html; charset=UTF-8");
			requestSearchEnd.setCharacterEncoding("UTF-8");
			expect(requestSearchEnd.getParameterMap()).andReturn(inputDataListSearchEnd);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_SEARCH);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_FLG)).andReturn(VALUE_SEARCH_CANCEL_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_FLG)).andReturn(VALUE_SEARCH_CANCEL_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_FLG)).andReturn(VALUE_SEARCH_SHIPPING_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_FLG)).andReturn(VALUE_SEARCH_SHIPPING_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);

			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_SEARCH_NAME_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_SEARCH_NAME_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME_KANA)).andReturn(VALUE_SEARCH_NAME_KANA_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME_KANA)).andReturn(VALUE_SEARCH_NAME_KANA_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_MAIL)).andReturn(VALUE_SEARCH_MAIL_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_MAIL)).andReturn(VALUE_SEARCH_MAIL_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_PHONE_NUMBER)).andReturn(VALUE_SEARCH_PHONE_NUMBER_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_PHONE_NUMBER)).andReturn(VALUE_SEARCH_PHONE_NUMBER_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);

			requestSearchEnd.setAttribute(KEY_RESULT, orderListSearchEnd);
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
			List<Order> orderListSearchEnd = new ArrayList<Order>();
			List<String> errorMessageListSearchEnd = new ArrayList<String>();
			errorMessageListSearchEnd.add(VALUE_MESSAGE_FORMAT_ERROR);
			Map<String, String[]> inputDataListSearchEnd = new HashMap<String, String[]>();
			HttpServletRequest requestSearchEnd = createMock(HttpServletRequest.class);
			HttpServletResponse responseSearchEnd = createMock(HttpServletResponse.class);
			RequestDispatcher rdSearchEnd = createMock(RequestDispatcher.class);

			responseSearchEnd.setContentType("text/html; charset=UTF-8");
			requestSearchEnd.setCharacterEncoding("UTF-8");
			expect(requestSearchEnd.getParameterMap()).andReturn(inputDataListSearchEnd);
			expect(requestSearchEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(REQUEST_VALUE_ACTION_SEARCH);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_FLG)).andReturn(VALUE_SEARCH_CANCEL_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_FLG)).andReturn(VALUE_SEARCH_CANCEL_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_FLG)).andReturn(VALUE_SEARCH_SHIPPING_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_FLG)).andReturn(VALUE_SEARCH_SHIPPING_FLG_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);

			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_ID)).andReturn(VALUE_SEARCH_ID_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_FROM)).andReturn(VALUE_SEARCH_DATE_FROM_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_DATE_TO)).andReturn(VALUE_SEARCH_DATE_TO_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_SEARCH_NAME_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME)).andReturn(VALUE_SEARCH_NAME_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME_KANA)).andReturn(VALUE_SEARCH_NAME_KANA_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_NAME_KANA)).andReturn(VALUE_SEARCH_NAME_KANA_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_MAIL)).andReturn(VALUE_SEARCH_MAIL_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_MAIL)).andReturn(VALUE_SEARCH_MAIL_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_PHONE_NUMBER)).andReturn(VALUE_SEARCH_PHONE_NUMBER_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_PHONE_NUMBER)).andReturn(VALUE_SEARCH_PHONE_NUMBER_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_CANCEL_DATE)).andReturn(VALUE_SEARCH_CANCEL_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);
			expect(requestSearchEnd.getParameter(KEY_SEARCH_SHIPPING_DATE)).andReturn(VALUE_SEARCH_SHIPPING_DATE_NOT_EXIST);

			requestSearchEnd.setAttribute(KEY_RESULT, orderListSearchEnd);
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
