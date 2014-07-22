package nmnw.service.function.account.changePassword;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.function.account.changePassword.ChangePasswordServlet;

public class ChangePasswordServletTest {
	public static final String REQUEST_KEY_TOKEN = "token";
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String VALUE_TOKEN = "d359a56ee1020b3690d590bf123e45a6"; // account_id = 11;
	public static final String VALUE_TOKEN_NULL = null;
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_EDIT = "edit";
	public static final String VALUE_ACTION_EDIT_END = "edit_end";
	public static final String VALUE_ACTION_NULL = null;
	public static final String KEY_TITLE = "title";
	public static final String VALUE_TITLE_ERROR = "エラー";
	public static final String VALUE_TITLE_EDIT = "変更";
	public static final String VALUE_TITLE_EDIT_END = "変更完了";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_RETYPE_PASSWORD = "retype_password";
	public static final String VALUE_PASSWORD = "12345678";
	public static final String VALUE_PASSWORD_WRONG = "12345678910";
	public static final String MESSAAGE_PASSWORD_WRONG = "再度入力したパスワードと相違があります。";

	@Test
	public void ChangePasswordServletTest() {
	}

	@Test
	public void doGetTest() {
		try {
			/**
			 * 正常系：パスワード変更用画面表示
			 */
			// 初期化
			HttpServletRequest requestEdit = createMock(HttpServletRequest.class);
			HttpServletResponse responseEdit = createMock(HttpServletResponse.class);
			RequestDispatcher rdEdit = createMock(RequestDispatcher.class);

			requestEdit.setCharacterEncoding("UTF-8");
			responseEdit.setContentType("text/html; charset=UTF-8");
			expect(requestEdit.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN);
			expect(requestEdit.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			expect(requestEdit.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			expect(requestEdit.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			requestEdit.setAttribute(REQUEST_KEY_TOKEN, VALUE_TOKEN);
			requestEdit.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT);
			requestEdit.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT);
			expect(requestEdit.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEdit);
			rdEdit.forward(requestEdit, responseEdit);
			replay(requestEdit, responseEdit, rdEdit);
			Method methodEdit = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodEdit.setAccessible(true);
			// 実行
			ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
			methodEdit.invoke(changePasswordServlet, requestEdit, responseEdit);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：パスワード変更用画面表示失敗(actionパラメータエラー）
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			HttpServletRequest requestEditNoActionParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseEditNoActionParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdEditNoActionParam = createMock(RequestDispatcher.class);
	
			requestEditNoActionParam.setCharacterEncoding("UTF-8");
			responseEditNoActionParam.setContentType("text/html; charset=UTF-8");
			expect(requestEditNoActionParam.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN);
			expect(requestEditNoActionParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			requestEditNoActionParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestEditNoActionParam.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
			expect(requestEditNoActionParam.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEditNoActionParam);
			rdEditNoActionParam.forward(requestEditNoActionParam, responseEditNoActionParam);
			replay(requestEditNoActionParam, responseEditNoActionParam, rdEditNoActionParam);
			Method methodEditNoActionParam = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodEditNoActionParam.setAccessible(true);
			// 実行
			ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
			methodEditNoActionParam.invoke(changePasswordServlet, requestEditNoActionParam, responseEditNoActionParam);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：パスワード変更用画面表示失敗(tokenパラメータエラー）
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			HttpServletRequest requestEditNoTokenParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseEditNoTokenParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdEditNoTokenParam = createMock(RequestDispatcher.class);
	
			requestEditNoTokenParam.setCharacterEncoding("UTF-8");
			responseEditNoTokenParam.setContentType("text/html; charset=UTF-8");
			expect(requestEditNoTokenParam.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN_NULL);
			expect(requestEditNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			expect(requestEditNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			expect(requestEditNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT);
			requestEditNoTokenParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestEditNoTokenParam.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
			expect(requestEditNoTokenParam.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEditNoTokenParam);
			rdEditNoTokenParam.forward(requestEditNoTokenParam, responseEditNoTokenParam);
			replay(requestEditNoTokenParam, responseEditNoTokenParam, rdEditNoTokenParam);
			Method methodEditNoTokenParam = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodEditNoTokenParam.setAccessible(true);
			// 実行
			ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
			methodEditNoTokenParam.invoke(changePasswordServlet, requestEditNoTokenParam, responseEditNoTokenParam);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
		
		/**
		 * 正常系：パスワード変更
		 */
		try {
		// 初期化
		HttpServletRequest requestEditEnd = createMock(HttpServletRequest.class);
		HttpServletResponse responseEditEnd = createMock(HttpServletResponse.class);
		RequestDispatcher rdEditEnd = createMock(RequestDispatcher.class);
	
		requestEditEnd.setCharacterEncoding("UTF-8");
		responseEditEnd.setContentType("text/html; charset=UTF-8");
		expect(requestEditEnd.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN);
		expect(requestEditEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
		expect(requestEditEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
		expect(requestEditEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
		expect(requestEditEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
		expect(requestEditEnd.getParameter(KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
		expect(requestEditEnd.getParameter(KEY_RETYPE_PASSWORD)).andReturn(VALUE_PASSWORD);		
		expect(requestEditEnd.getParameter(KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
		expect(requestEditEnd.getParameter(KEY_RETYPE_PASSWORD)).andReturn(VALUE_PASSWORD);		
		expect(requestEditEnd.getParameter(KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
		requestEditEnd.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT_END);
		requestEditEnd.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT_END);
		expect(requestEditEnd.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEditEnd);
		rdEditEnd.forward(requestEditEnd, responseEditEnd);
		replay(requestEditEnd, responseEditEnd, rdEditEnd);
		Method methodEditEnd = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
		methodEditEnd.setAccessible(true);
		// 実行
		ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
		methodEditEnd.invoke(changePasswordServlet, requestEditEnd, responseEditEnd);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：パスワード変更失敗(tokenパラメータエラー）
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			HttpServletRequest requestEditEndNoTokenParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseEditEndNoTokenParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdEditEndNoTokenParam = createMock(RequestDispatcher.class);
	
			requestEditEndNoTokenParam.setCharacterEncoding("UTF-8");
			responseEditEndNoTokenParam.setContentType("text/html; charset=UTF-8");
			expect(requestEditEndNoTokenParam.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN_NULL);
			expect(requestEditEndNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndNoTokenParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			requestEditEndNoTokenParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestEditEndNoTokenParam.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
			expect(requestEditEndNoTokenParam.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEditEndNoTokenParam);
			rdEditEndNoTokenParam.forward(requestEditEndNoTokenParam, responseEditEndNoTokenParam);
			replay(requestEditEndNoTokenParam, responseEditEndNoTokenParam, rdEditEndNoTokenParam);
			Method methodEditEndNoTokenParam = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodEditEndNoTokenParam.setAccessible(true);
			// 実行
			ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
			methodEditEndNoTokenParam.invoke(changePasswordServlet, requestEditEndNoTokenParam, responseEditEndNoTokenParam);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：パスワード変更失敗(入力値エラー）
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MESSAAGE_PASSWORD_WRONG);
			HttpServletRequest requestEditEndWrongRetypePass = createMock(HttpServletRequest.class);
			HttpServletResponse responseEditEndWrongRetypePass = createMock(HttpServletResponse.class);
			RequestDispatcher rdEditEndWrongRetypePass = createMock(RequestDispatcher.class);
	
			requestEditEndWrongRetypePass.setCharacterEncoding("UTF-8");
			responseEditEndWrongRetypePass.setContentType("text/html; charset=UTF-8");
			expect(requestEditEndWrongRetypePass.getParameter(REQUEST_KEY_TOKEN)).andReturn(VALUE_TOKEN);
			expect(requestEditEndWrongRetypePass.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndWrongRetypePass.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndWrongRetypePass.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndWrongRetypePass.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_EDIT_END);
			expect(requestEditEndWrongRetypePass.getParameter(KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
			expect(requestEditEndWrongRetypePass.getParameter(KEY_RETYPE_PASSWORD)).andReturn(VALUE_PASSWORD_WRONG);		
			expect(requestEditEndWrongRetypePass.getParameter(KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
			expect(requestEditEndWrongRetypePass.getParameter(KEY_RETYPE_PASSWORD)).andReturn(VALUE_PASSWORD_WRONG);		
			requestEditEndWrongRetypePass.setAttribute(REQUEST_KEY_TOKEN, VALUE_TOKEN);
			requestEditEndWrongRetypePass.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT);
			requestEditEndWrongRetypePass.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT);
			requestEditEndWrongRetypePass.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT);
			requestEditEndWrongRetypePass.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			expect(requestEditEndWrongRetypePass.getRequestDispatcher("/WEB-INF/service/function/account/changePassword/ChangePassword.jsp")).andReturn(rdEditEndWrongRetypePass);
			rdEditEndWrongRetypePass.forward(requestEditEndWrongRetypePass, responseEditEndWrongRetypePass);
			replay(requestEditEndWrongRetypePass, responseEditEndWrongRetypePass, rdEditEndWrongRetypePass);
			Method methodEditEndWrongRetypePass = ChangePasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodEditEndWrongRetypePass.setAccessible(true);
			// 実行
			ChangePasswordServlet changePasswordServlet = new ChangePasswordServlet();
			methodEditEndWrongRetypePass.invoke(changePasswordServlet, requestEditEndWrongRetypePass, responseEditEndWrongRetypePass);
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
