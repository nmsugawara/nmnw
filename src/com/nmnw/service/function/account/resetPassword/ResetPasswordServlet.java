package com.nmnw.service.function.account.resetPassword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.CipherUtility;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.MailUtility;
import com.nmnw.service.utility.RandomStringUtility;
import com.nmnw.service.validator.AccountValidator;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.dao.Mail;
import com.nmnw.service.dao.MailDao;

@WebServlet(name="account/resetPassword", urlPatterns={"/account/resetPassword"})
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// subject
	private static final String CHANGE_PASSWORD_URL = "http://localhost:8080/nmnw/account/changePassword?action=edit&token=";
	private static final String MAIL_CODE = "reset_password";
	private static final String KEY_MAIL = "mail";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String KEY_ACTION = "action";
	private static final String VALUE_ACTION_RESET = "reset";
	private static final String VALUE_ACTION_RESET_END = "reset_end";
	private static final String KEY_TITLE = "title";
	private static final String DISPLAY_TITLE_ERROR = "エラー";
	private static final String DISPLAY_TITLE_RESET = "リセット";
	private static final String DISPLAY_TITLE_RESET_END = "リセット完了";
	
	/**
	 * Construct
	 */
	public ResetPasswordServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ACCOUNT_RESET_PASSWORD + "ResetPassword.jsp";
		List<String> errorMessageList = new ArrayList<String>();

		try {
			// actionパラメータがない、または意図しない値の場合
			String[] vaildActionParam = {VALUE_ACTION_RESET, VALUE_ACTION_RESET_END};
			if (request.getParameter(KEY_ACTION) == null || !Arrays.asList(vaildActionParam).contains(request.getParameter(KEY_ACTION))) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_TITLE, DISPLAY_TITLE_ERROR);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			////////////////////////////
			// パスワード変更用メール送信画面表示
			////////////////////////////
			if (VALUE_ACTION_RESET.equals(request.getParameter(KEY_ACTION))) {
				request.setAttribute(KEY_MAIL, request.getParameter(KEY_MAIL));
				request.setAttribute(KEY_ACTION, VALUE_ACTION_RESET);
				request.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			////////////////////////////
			// パスワード変更用メール送信
			////////////////////////////
			if (VALUE_ACTION_RESET_END.equals(request.getParameter(KEY_ACTION))) {
				// 入力チェック
				AccountValidator av = new AccountValidator();
				av.checkMail(request.getParameter(KEY_MAIL));
	
				errorMessageList = av.getValidationList();
				// 入力エラーの場合
				if (errorMessageList.size() != 0) {
					// エラー
					request.setAttribute(KEY_ACTION, VALUE_ACTION_RESET);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// 入力チェックOKの場合
				// 入力メールよりアカウント情報取得
				AccountDao accountDao = new AccountDao();
				Account account = accountDao.selectByMail(request.getParameter(KEY_MAIL));
				if (account.getId() == 0) {
					// エラー
					request.setAttribute(KEY_ACTION, VALUE_ACTION_RESET);
					errorMessageList.add(MessageConstants.MESSAGE_MAIL_NOT_EXIST);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// パスワード変更用ｔｏｋｅｎ生成
				String token = RandomStringUtility.generateToken();
				// token有効期限
				Date tokenExpireTime = DateConversionUtility.getdaysAfterDate(ConfigConstants.TOKEN_EXPIRE_DAYS);
				Account updateAccount = new Account();
				updateAccount.setId(account.getId());
				updateAccount.setToken(token);
				updateAccount.setTokenExpireTime(tokenExpireTime);
				// DB格納
				int updateCount = accountDao.update(updateAccount);

				// メール送信
				MailDao mailDao = new MailDao();
				Mail mail = mailDao.selectByCode(MAIL_CODE);
				String message = mail.getMessage().replace("{Url}", CHANGE_PASSWORD_URL + token);

				boolean sendResult = MailUtility.sendMail(request.getParameter(KEY_MAIL), mail.getSubject(), message);
				if (sendResult == false) {
					errorMessageList.add(MessageConstants.ERROR_SEND_MAIL);
				}
				request.setAttribute(KEY_ACTION, VALUE_ACTION_RESET_END);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_TITLE, DISPLAY_TITLE_RESET_END);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtility.redirectErrorPage(request, response, e);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}