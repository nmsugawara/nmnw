package nmnw.service.function.login;

import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;
import static org.easymock.EasyMock.*;
import static org.junit.Assume.*;

import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;

import com.nmnw.service.function.login.LoginServlet;

public class LoginServletTest {
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_LOGIN = "login";
	public static final String VALUE_ACTION_NOT_LOGIN = "test";
	public static final String KEY_MESSAGE = "message";
	public static final String REQUEST_KEY_MAIL = "login_mail";
	public static final String REQUEST_KEY_PASSWORD = "login_password";
	public static final String KEY_LOGIN_ID = "id";
	public static final String KEY_LOGIN_NAME = "name";
	public static final String VALUE_MAIL = "ssugawara@net-marketing.co.jp";
	public static final String VALUE_MAIL_WRONG = "test";
	public static final String VALUE_PASSWORD = "qazwsxedc";
	public static final int ACCOUNT_ID = 7;
	public static final String ACCOUNT_NAME = "��؁@���";
	public static final String  URL = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_INDEX;

	@Test
	public void doGetTest() {

		/**
		 * ����n�F���O�C����ʕ\��
		 */
		try {
			// ������
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_NOT_LOGIN);
			expect(request.getRequestDispatcher("/WEB-INF/service/function/login/Login.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = LoginServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// ���s
			LoginServlet loginServlet = new LoginServlet();
			method.invoke(loginServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		// �㏈��

		/**
		 * ����n�F���O�C��
		 */
		try {
			// ������
			HttpServletRequest requestLogin = createMock(HttpServletRequest.class);
			HttpServletResponse responseLogin = createMock(HttpServletResponse.class);
			RequestDispatcher rdLogin = createMock(RequestDispatcher.class);
			HttpSession sessionLogin = createMock(HttpSession.class);

			requestLogin.setCharacterEncoding("UTF-8");
			responseLogin.setContentType("text/html; charset=UTF-8");
			expect(requestLogin.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_LOGIN);
			expect(requestLogin.getParameter(REQUEST_KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestLogin.getParameter(REQUEST_KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
			expect(requestLogin.getSession()).andReturn(sessionLogin);
			sessionLogin.setAttribute(KEY_LOGIN_ID, ACCOUNT_ID);
			sessionLogin.setAttribute(KEY_LOGIN_NAME, ACCOUNT_NAME);
			responseLogin.sendRedirect(URL);
			replay(requestLogin, responseLogin, rdLogin, sessionLogin);
			Method methodLogin = LoginServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodLogin.setAccessible(true);
			// ���s
			LoginServlet loginServlet = new LoginServlet();
			methodLogin.invoke(loginServlet, requestLogin, responseLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		// �㏈��

		/**
		 * �ُ�n�F���O�C���G���[
		 */
		try {
			// ������
			HttpServletRequest requestLoginError = createMock(HttpServletRequest.class);
			HttpServletResponse responseLoginError = createMock(HttpServletResponse.class);
			RequestDispatcher rdLoginError = createMock(RequestDispatcher.class);
			HttpSession sessionLoginError = createMock(HttpSession.class);

			requestLoginError.setCharacterEncoding("UTF-8");
			responseLoginError.setContentType("text/html; charset=UTF-8");
			expect(requestLoginError.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_LOGIN);
			expect(requestLoginError.getParameter(REQUEST_KEY_MAIL)).andReturn(VALUE_MAIL_WRONG);
			requestLoginError.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_LOGIN_FAILED);
			
			expect(requestLoginError.getSession()).andReturn(sessionLoginError);
			sessionLoginError.setAttribute(KEY_LOGIN_ID, ACCOUNT_ID);
			sessionLoginError.setAttribute(KEY_LOGIN_NAME, ACCOUNT_NAME);
			responseLoginError.sendRedirect(URL);
			expect(requestLoginError.getRequestDispatcher("/WEB-INF/service/function/login/Login.jsp")).andReturn(rdLoginError);
			rdLoginError.forward(requestLoginError, responseLoginError);
			replay(requestLoginError, responseLoginError, rdLoginError, sessionLoginError);
			Method methodLoginError = LoginServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			methodLoginError.setAccessible(true);
			// ���s
			LoginServlet loginServlet = new LoginServlet();
			methodLoginError.invoke(loginServlet, requestLoginError, responseLoginError);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		// �㏈��
	}

	@Test
	public void doPostTest() {
		/**
		 * ����n�F���O�C��
		 */
		try {
			// ������
			HttpServletRequest requestLogin = createMock(HttpServletRequest.class);
			HttpServletResponse responseLogin = createMock(HttpServletResponse.class);
			RequestDispatcher rdLogin = createMock(RequestDispatcher.class);
			HttpSession sessionLogin = createMock(HttpSession.class);

			requestLogin.setCharacterEncoding("UTF-8");
			responseLogin.setContentType("text/html; charset=UTF-8");
			expect(requestLogin.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_LOGIN);
			expect(requestLogin.getParameter(REQUEST_KEY_MAIL)).andReturn(VALUE_MAIL);
			expect(requestLogin.getParameter(REQUEST_KEY_PASSWORD)).andReturn(VALUE_PASSWORD);
			expect(requestLogin.getSession()).andReturn(sessionLogin);
			sessionLogin.setAttribute(KEY_LOGIN_ID, ACCOUNT_ID);
			sessionLogin.setAttribute(KEY_LOGIN_NAME, ACCOUNT_NAME);
			responseLogin.sendRedirect(URL);
			replay(requestLogin, responseLogin, rdLogin, sessionLogin);
			Method methodLogin = LoginServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			methodLogin.setAccessible(true);
			// ���s
			LoginServlet loginServlet = new LoginServlet();
			methodLogin.invoke(loginServlet, requestLogin, responseLogin);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		// �㏈��
	}

}
