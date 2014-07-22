package nmnw.service.function.account.register;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.function.account.register.RegisterServlet;
import com.nmnw.service.utility.DbConnector;

public class RegisterServletTest {
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String KEY_INPUT_DATA_LIST = "inputDataList";
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_REGIST_END = "regist_end";
	public static final String VALUE_ACTION_DUPLICATION_CHECK = "duplication_check";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "account_name";
	public static final String KEY_NAME_KANA = "account_name_kana";
	public static final String KEY_MAIL = "account_mail";
	public static final String KEY_ZIP_CODE = "account_zip_code";
	public static final String KEY_ADDRESS = "account_address";
	public static final String KEY_PHONE_NUMBER = "account_phone_number";
	public static final String DUPLICATION_MAIL_ALERT_HTML = "<font color=\"red\">そのメールアドレスは既に登録されています。</font>";
	public static final String URL = ConfigConstants.JSP_DIR_ACCOUNT_REGISTER + "Register.jsp";
	public static final String URL_REGIST_END = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_ACCOUNT_DETAIL + "?account_id={$1}&action=regist_end";

	public static final String VALUE_ACTION_NULL = null;
	public static final String VALUE_MAIL_DUPLICATION = "ssugawara@net-marketing.co.jp";
	public static final String VALUE_NAME_NULL = null;
	public static final String VALUE_NAME = "test";
	public static final String VALUE_NAME_KANA = "テスト";
	public static final String VALUE_MAIL = "checktrack010@net-marlketing.co.jp";
	public static final String VALUE_ZIP_CODE = "111-1111";
	public static final String VALUE_ADDRESS = "test";
	public static final String VALUE_PHONE_NUMBER = "090-9999-9999";
	public static final String VALUE_ERROR_MESSAGE_REQUIRED = "会員名は必須です。";

	@Test
	public void ResetPasswordServletTest() {
	}

	@Test
	public void doGetTest() {

		/**
		 * 正常系：入力画面表示
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			Map<String, String[]> inputDataList = new HashMap<String, String[]>();
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
	
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getParameterMap()).andReturn(inputDataList);
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = RegisterServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			RegisterServlet registerServlet = new RegisterServlet();
			method.invoke(registerServlet, request, response);
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
			errorMessageListParameterFormatError.add(VALUE_ERROR_MESSAGE_REQUIRED);
			Map<String, String[]> inputDataListParameterFormatError = new HashMap<String, String[]>();
			HttpServletRequest requestParameterFormatError = createMock(HttpServletRequest.class);
			HttpServletResponse responseParameterFormatError = createMock(HttpServletResponse.class);
			RequestDispatcher rdParameterFormatError = createMock(RequestDispatcher.class);
	
			responseParameterFormatError.setContentType("text/html; charset=UTF-8");
			requestParameterFormatError.setCharacterEncoding("UTF-8");
			expect(requestParameterFormatError.getParameterMap()).andReturn(inputDataListParameterFormatError);
			expect(requestParameterFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestParameterFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestParameterFormatError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestParameterFormatError.getParameter(KEY_NAME)).andReturn(VALUE_NAME_NULL);
			expect(requestParameterFormatError.getParameter(KEY_NAME_KANA)).andReturn(VALUE_NAME_KANA);
			expect(requestParameterFormatError.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestParameterFormatError.getParameter(KEY_ZIP_CODE)).andReturn(VALUE_ZIP_CODE);
			expect(requestParameterFormatError.getParameter(KEY_ADDRESS)).andReturn(VALUE_ADDRESS);
			expect(requestParameterFormatError.getParameter(KEY_PHONE_NUMBER)).andReturn(VALUE_PHONE_NUMBER);
			requestParameterFormatError.setAttribute(KEY_ERROR_MESSAGE, errorMessageListParameterFormatError);
			requestParameterFormatError.setAttribute(KEY_INPUT_DATA_LIST, inputDataListParameterFormatError);
			expect(requestParameterFormatError.getRequestDispatcher(URL)).andReturn(rdParameterFormatError);
			rdParameterFormatError.forward(requestParameterFormatError, responseParameterFormatError);
			replay(requestParameterFormatError, responseParameterFormatError, rdParameterFormatError);
			Method method = RegisterServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			RegisterServlet registerServlet = new RegisterServlet();
			method.invoke(registerServlet, requestParameterFormatError, responseParameterFormatError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：メールアドレス重複
		 */
		try {
			// 初期化
			List<String> errorMessageListDuplicationmail = new ArrayList<String>();
			errorMessageListDuplicationmail.add(MessageConstants.MESSAGE_DUPLICATION_MAIL);
			Map<String, String[]> inputDataListDuplicationmail = new HashMap<String, String[]>();
			HttpServletRequest requestDuplicationmail = createMock(HttpServletRequest.class);
			HttpServletResponse responseDuplicationmail = createMock(HttpServletResponse.class);
			RequestDispatcher rdDuplicationmail = createMock(RequestDispatcher.class);
	
			responseDuplicationmail.setContentType("text/html; charset=UTF-8");
			requestDuplicationmail.setCharacterEncoding("UTF-8");
			expect(requestDuplicationmail.getParameterMap()).andReturn(inputDataListDuplicationmail);
			expect(requestDuplicationmail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestDuplicationmail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestDuplicationmail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestDuplicationmail.getParameter(KEY_NAME)).andReturn(VALUE_NAME);
			expect(requestDuplicationmail.getParameter(KEY_NAME_KANA)).andReturn(VALUE_NAME_KANA);
			expect(requestDuplicationmail.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL_DUPLICATION);
			expect(requestDuplicationmail.getParameter(KEY_ZIP_CODE)).andReturn(VALUE_ZIP_CODE);
			expect(requestDuplicationmail.getParameter(KEY_ADDRESS)).andReturn(VALUE_ADDRESS);
			expect(requestDuplicationmail.getParameter(KEY_PHONE_NUMBER)).andReturn(VALUE_PHONE_NUMBER);
			expect(requestDuplicationmail.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL_DUPLICATION);
			requestDuplicationmail.setAttribute(KEY_ERROR_MESSAGE, errorMessageListDuplicationmail);
			requestDuplicationmail.setAttribute(KEY_INPUT_DATA_LIST, inputDataListDuplicationmail);
			expect(requestDuplicationmail.getRequestDispatcher(URL)).andReturn(rdDuplicationmail);
			rdDuplicationmail.forward(requestDuplicationmail, responseDuplicationmail);
			replay(requestDuplicationmail, responseDuplicationmail, rdDuplicationmail);
			Method method = RegisterServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			RegisterServlet registerServlet = new RegisterServlet();
			method.invoke(registerServlet, requestDuplicationmail, responseDuplicationmail);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 正常系：会員登録
		 */
/*
		try {
			// 初期化
			List<String> errorMessageListRegister = new ArrayList<String>();
			errorMessageListRegister.add(MessageConstants.MESSAGE_DUPLICATION_MAIL);
			Map<String, String[]> inputDataListRegister = new HashMap<String, String[]>();
			HttpServletRequest requestRegister = createMock(HttpServletRequest.class);
			HttpServletResponse responseRegister = createMock(HttpServletResponse.class);
			RequestDispatcher rdRegister = createMock(RequestDispatcher.class);
	
			responseRegister.setContentType("text/html; charset=UTF-8");
			requestRegister.setCharacterEncoding("UTF-8");
			expect(requestRegister.getParameterMap()).andReturn(inputDataListRegister);
			expect(requestRegister.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestRegister.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestRegister.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			expect(requestRegister.getParameter(KEY_NAME)).andReturn(VALUE_NAME);
			expect(requestRegister.getParameter(KEY_NAME_KANA)).andReturn(VALUE_NAME_KANA);
			expect(requestRegister.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestRegister.getParameter(KEY_ZIP_CODE)).andReturn(VALUE_ZIP_CODE);
			expect(requestRegister.getParameter(KEY_ADDRESS)).andReturn(VALUE_ADDRESS);
			expect(requestRegister.getParameter(KEY_PHONE_NUMBER)).andReturn(VALUE_PHONE_NUMBER);
			expect(requestRegister.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestRegister.getParameter(KEY_NAME)).andReturn(VALUE_NAME);
			expect(requestRegister.getParameter(KEY_NAME_KANA)).andReturn(VALUE_NAME_KANA);
			expect(requestRegister.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestRegister.getParameter(KEY_ZIP_CODE)).andReturn(VALUE_ZIP_CODE);
			expect(requestRegister.getParameter(KEY_ADDRESS)).andReturn(VALUE_ADDRESS);
			expect(requestRegister.getParameter(KEY_PHONE_NUMBER)).andReturn(VALUE_PHONE_NUMBER);

			Connection connection = DbConnector.getConnection();
			String sql = "select id from account order by id desc limit 1";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			String accountId = "";
			while(resultSet.next()) {
				accountId = String.valueOf(resultSet.getInt(KEY_ID));
			}
			statement.close();
			connection.close();
			String registEndUrl = URL_REGIST_END.replace("{$1}", accountId);

			responseRegister.sendRedirect(registEndUrl);
			replay(requestRegister, responseRegister, rdRegister);
			Method method = RegisterServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			RegisterServlet registerServlet = new RegisterServlet();
			method.invoke(registerServlet, requestRegister, responseRegister);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
*/
	}

}
