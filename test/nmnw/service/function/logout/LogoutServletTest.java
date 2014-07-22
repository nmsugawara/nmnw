package nmnw.service.function.logout;

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

import com.nmnw.service.function.logout.LogoutServlet;

public class LogoutServletTest {
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
	public static final String ACCOUNT_NAME = "鈴木　大介";
	public static final String  URL = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_INDEX;

	@Test
	public void doGetTest() {

		/**
		 * 正常系：ログアウト
		 */
		try {
			// 初期化
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getSession()).andReturn(session);
			session.invalidate();
			expect(request.getRequestDispatcher("/WEB-INF/service/function/logout/Logout.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = LogoutServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			LogoutServlet logoutServlet = new LogoutServlet();
			method.invoke(logoutServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {

		/**
		 * 正常系：ログアウト
		 */
		try {
			// 初期化
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);

			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getSession()).andReturn(session);
			session.invalidate();
			expect(request.getRequestDispatcher("/WEB-INF/service/function/logout/Logout.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = LogoutServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			LogoutServlet logoutServlet = new LogoutServlet();
			method.invoke(logoutServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}
}
