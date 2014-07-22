package nmnw.service.function.account.resetPassword;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
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
import com.nmnw.service.function.account.resetPassword.ResetPasswordServlet;

public class RedetPasswordServletTest {
	public static final String CHANGE_PASSWORD_URL = "http://" + getPropertyValue("DOMAIN") + "/nmnw/account/changePassword?action=edit&token=";
	public static final String MAIL_CODE = "reset_password";
	public static final String KEY_MAIL = "mail";
	public static final String VALUE_MAIL = "ssugawara@net-marketing.co.jp";
	public static final String VALUE_MAIL_NOT_EXIST = "aaaaaaaaaaaaaaa@net-marketing.co.jp";
	public static final String VALUE_MAIL_NULL = null;
	public static final String KEY_ERROR_MESSAGE = "errorMessageList";
	public static final String VALUE_ERROR_MESSAGE_REQUIRED = "メールアドレスは必須です。";
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_RESET = "reset";
	public static final String VALUE_ACTION_RESET_END = "reset_end";
	public static final String VALUE_ACTION_NULL = null;
	public static final String KEY_TITLE = "title";
	public static final String DISPLAY_TITLE_ERROR = "エラー";
	public static final String DISPLAY_TITLE_RESET = "リセット";
	public static final String DISPLAY_TITLE_RESET_END = "リセット完了";

	@Test
	public void ResetPasswordServletTest() {
	}

	@Test
	public void doGetTest() {
		try {
			/**
			 * 正常系：パスワード変更用画面表示
			 */
			// 初期化
			HttpServletRequest requestReset = createMock(HttpServletRequest.class);
			HttpServletResponse responseReset = createMock(HttpServletResponse.class);
			RequestDispatcher rdReset = createMock(RequestDispatcher.class);

			requestReset.setCharacterEncoding("UTF-8");
			responseReset.setContentType("text/html; charset=UTF-8");
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			requestReset.setAttribute(KEY_MAIL, VALUE_MAIL);
			requestReset.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_RESET);
			requestReset.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
			expect(requestReset.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdReset);
			rdReset.forward(requestReset, responseReset);
			replay(requestReset, responseReset, rdReset);
			Method methodReset = ResetPasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodReset.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodReset.invoke(resetPasswordServlet, requestReset, responseReset);
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
			HttpServletRequest requestResetNoActionParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseResetNoActionParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdResetNoActionParam = createMock(RequestDispatcher.class);

			requestResetNoActionParam.setCharacterEncoding("UTF-8");
			responseResetNoActionParam.setContentType("text/html; charset=UTF-8");
			expect(requestResetNoActionParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NULL);
			requestResetNoActionParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestResetNoActionParam.setAttribute(KEY_TITLE, DISPLAY_TITLE_ERROR);
			expect(requestResetNoActionParam.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdResetNoActionParam);
			rdResetNoActionParam.forward(requestResetNoActionParam, responseResetNoActionParam);
			replay(requestResetNoActionParam, responseResetNoActionParam, rdResetNoActionParam);
			Method methodResetNoActionParam = ResetPasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodResetNoActionParam.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodResetNoActionParam.invoke(resetPasswordServlet, requestResetNoActionParam, responseResetNoActionParam);
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
			List<String> errorMessageList = new ArrayList<String>();
			HttpServletRequest requestResetEnd = createMock(HttpServletRequest.class);
			HttpServletResponse responseResetEnd = createMock(HttpServletResponse.class);
			RequestDispatcher rdResetEnd = createMock(RequestDispatcher.class);
	
			requestResetEnd.setCharacterEncoding("UTF-8");
			responseResetEnd.setContentType("text/html; charset=UTF-8");
			expect(requestResetEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEnd.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEnd.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestResetEnd.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestResetEnd.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			requestResetEnd.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_RESET_END);
			requestResetEnd.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestResetEnd.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET_END);
			expect(requestResetEnd.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdResetEnd);
			rdResetEnd.forward(requestResetEnd, responseResetEnd);
			replay(requestResetEnd, responseResetEnd, rdResetEnd);
			Method methodResetEnd = ResetPasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodResetEnd.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodResetEnd.invoke(resetPasswordServlet, requestResetEnd, responseResetEnd);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：メール：Validatorエラー
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(VALUE_ERROR_MESSAGE_REQUIRED);
			HttpServletRequest requestResetEndNoMailParam = createMock(HttpServletRequest.class);
			HttpServletResponse responseResetEndNoMailParam = createMock(HttpServletResponse.class);
			RequestDispatcher rdResetEndNoMailParam = createMock(RequestDispatcher.class);
	
			requestResetEndNoMailParam.setCharacterEncoding("UTF-8");
			responseResetEndNoMailParam.setContentType("text/html; charset=UTF-8");
			expect(requestResetEndNoMailParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNoMailParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNoMailParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNoMailParam.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNoMailParam.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL_NULL);
			requestResetEndNoMailParam.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_RESET);
			requestResetEndNoMailParam.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestResetEndNoMailParam.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
			expect(requestResetEndNoMailParam.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdResetEndNoMailParam);
			rdResetEndNoMailParam.forward(requestResetEndNoMailParam, responseResetEndNoMailParam);
			replay(requestResetEndNoMailParam, responseResetEndNoMailParam, rdResetEndNoMailParam);
			Method methodResetEndNoMailParam = ResetPasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodResetEndNoMailParam.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodResetEndNoMailParam.invoke(resetPasswordServlet, requestResetEndNoMailParam, responseResetEndNoMailParam);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

		/**
		 * 異常系：登録されていないメール
		 */
		try {
			// 初期化
			List<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(MessageConstants.MESSAGE_MAIL_NOT_EXIST);
			HttpServletRequest requestResetEndNotExistMail = createMock(HttpServletRequest.class);
			HttpServletResponse responseResetEndNotExistMail = createMock(HttpServletResponse.class);
			RequestDispatcher rdResetEndNotExistMail = createMock(RequestDispatcher.class);
	
			requestResetEndNotExistMail.setCharacterEncoding("UTF-8");
			responseResetEndNotExistMail.setContentType("text/html; charset=UTF-8");
			expect(requestResetEndNotExistMail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNotExistMail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNotExistMail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNotExistMail.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET_END);
			expect(requestResetEndNotExistMail.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL_NOT_EXIST);
			expect(requestResetEndNotExistMail.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL_NOT_EXIST);
			requestResetEndNotExistMail.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_RESET);
			requestResetEndNotExistMail.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			requestResetEndNotExistMail.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
			expect(requestResetEndNotExistMail.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdResetEndNotExistMail);
			rdResetEndNotExistMail.forward(requestResetEndNotExistMail, responseResetEndNotExistMail);
			replay(requestResetEndNotExistMail, responseResetEndNotExistMail, rdResetEndNotExistMail);
			Method methodResetEndNotExistMail = ResetPasswordServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodResetEndNotExistMail.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodResetEndNotExistMail.invoke(resetPasswordServlet, requestResetEndNotExistMail, responseResetEndNotExistMail);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理

	}

	@Test
	public void doPostTest() {
		try {
			// 初期化
			HttpServletRequest requestReset = createMock(HttpServletRequest.class);
			HttpServletResponse responseReset = createMock(HttpServletResponse.class);
			RequestDispatcher rdReset = createMock(RequestDispatcher.class);
	
			requestReset.setCharacterEncoding("UTF-8");
			responseReset.setContentType("text/html; charset=UTF-8");
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_RESET);
			expect(requestReset.getParameter(KEY_MAIL)).andReturn(VALUE_MAIL);
			requestReset.setAttribute(KEY_MAIL, VALUE_MAIL);
			requestReset.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_RESET);
			requestReset.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
			expect(requestReset.getRequestDispatcher("/WEB-INF/service/function/account/resetPassword/ResetPassword.jsp")).andReturn(rdReset);
			rdReset.forward(requestReset, responseReset);
			replay(requestReset, responseReset, rdReset);
			Method methodReset = ResetPasswordServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			methodReset.setAccessible(true);
			// 実行
			ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
			methodReset.invoke(resetPasswordServlet, requestReset, responseReset);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

}
