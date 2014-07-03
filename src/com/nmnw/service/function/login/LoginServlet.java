package com.nmnw.service.function.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.CipherUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;

@WebServlet(name="login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_REQUEST_KEY_ACTION = "action";
	private static final String VALUE_ACTION_LOGIN = "login";
	private static final String KEY_MESSAGE = "message";
	private static final String REQUEST_KEY_MAIL = "login_mail";
	private static final String REQUEST_KEY_PASSWORD = "login_password";
	private static final String KEY_LOGIN_ID = "id";
	private static final String KEY_LOGIN_NAME = "name";
	/**
	 * Construct
	 */
	public LoginServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_LOGIN + "Login.jsp";
		String message = "";
		// ログイン画面表示
		if (!VALUE_ACTION_LOGIN.equals(request.getParameter(REQUEST_REQUEST_KEY_ACTION))) {
			request.getRequestDispatcher(page).forward(request, response);
		} else {
		// ログイン処理
			try {
				AccountDao accountDao = new AccountDao();
				// パスワード、salt取得
				Account account = accountDao.selectByMail(request.getParameter(REQUEST_KEY_MAIL));
				// saltが取得できていたら
				if (account.getSalt() != null && account.getSalt().length() != 0) {
					// 入力パスワードとsaltをハッシュ化
					String password = CipherUtility.enctypt(request.getParameter(REQUEST_KEY_PASSWORD) + account.getSalt());
					// 保存されているパスワードとの比較
					if (password.equals(account.getPassWord())) {
						// ログイン成功
						HttpSession session = request.getSession();
						session.setAttribute(KEY_LOGIN_ID, account.getId());
						session.setAttribute(KEY_LOGIN_NAME, account.getName());
						String url = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_INDEX;
						response.sendRedirect(url);
						return;
					}
				}
				// ログイン失敗
				message = MessageConstants.MESSAGE_LOGIN_FAILED;
				request.setAttribute(KEY_MESSAGE, message);
				request.getRequestDispatcher(page).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}