package com.nmnw.service.function.account.changePassword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.CipherUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RandomStringUtility;
import com.nmnw.service.validator.AccountValidator;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;

@WebServlet(name="account/changePassword", urlPatterns={"/account/changePassword"})
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_TOKEN = "token";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String VALUE_ACTION_EDIT = "edit";
	private static final String VALUE_ACTION_EDIT_END = "edit_end";
	private static final String KEY_TITLE = "title";
	private static final String VALUE_TITLE_ERROR = "エラー";
	private static final String VALUE_TITLE_EDIT = "変更";
	private static final String VALUE_TITLE_EDIT_END = "変更完了";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_RETYPE_PASSWORD = "retype_password";

	/**
	 * Construct
	 */
	public ChangePasswordServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ACCOUNT_CHANGE_PASSWORD + "ChangePassword.jsp";
		String token = request.getParameter(REQUEST_KEY_TOKEN);
		List<String> errorMessageList = new ArrayList<String>();
		Calendar currentDateTime = Calendar.getInstance();

		try {
			// actionパラメータがない、または意図しない値の場合
			String[] vaildActionParam = {VALUE_ACTION_EDIT, VALUE_ACTION_EDIT_END};
			if (request.getParameter(REQUEST_KEY_ACTION) == null || !Arrays.asList(vaildActionParam).contains(request.getParameter(REQUEST_KEY_ACTION))) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			////////////////////////////
			// パスワード変更用画面表示
			////////////////////////////
			if (VALUE_ACTION_EDIT.equals(request.getParameter(REQUEST_KEY_ACTION))) {
				// tokenパラメータチェック
				Account account = getAccountByToken(token, currentDateTime);
				if (account == null) {
					// エラー
					errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				request.setAttribute(REQUEST_KEY_TOKEN, token);
				request.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT);
				request.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			////////////////////////////
			// パスワード変更
			////////////////////////////
			if (VALUE_ACTION_EDIT_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
				// tokenパラメータチェック
				Account account = getAccountByToken(token, currentDateTime);
				if (account == null) {
					// エラー
					errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// 入力チェック
				AccountValidator av = new AccountValidator();
				av.checkPassWord(request.getParameter(KEY_PASSWORD));
				av.checkPassWord(request.getParameter(KEY_RETYPE_PASSWORD));
				av.checkPassWordAndRetypePassWord(request.getParameter(KEY_PASSWORD), request.getParameter(KEY_RETYPE_PASSWORD));

				errorMessageList = av.getValidationList();
				// 入力エラーの場合
				if (errorMessageList.size() != 0) {
					// 再度変更画面表示
					request.setAttribute(REQUEST_KEY_TOKEN, token);
					request.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT);
					request.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// 変更
				// salt生成
				String salt = RandomStringUtility.generateSalt();
				// 入力パスワードとsaltを組み合わせてハッシュ化
				String enctyptPassword = CipherUtility.enctypt(request.getParameter(KEY_PASSWORD) + salt);
				Account updateAccount = new Account();
				updateAccount.setId(account.getId());
				updateAccount.setPassWord(enctyptPassword);
				updateAccount.setSalt(salt);
				// update
				AccountDao accountDao = new AccountDao();
				int updateCount = accountDao.update(updateAccount);
				// 正常に1件更新されていた場合
				if (updateCount == 1) {
					request.setAttribute(REQUEST_KEY_ACTION, VALUE_ACTION_EDIT_END);
					request.setAttribute(KEY_TITLE, VALUE_TITLE_EDIT_END);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_CHANGE_PASSWORD_FAILED);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_TITLE, VALUE_TITLE_ERROR);
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

	/**
	 * actionパラメータチェック
	 * @param token
	 * @return Account
	 */
	private Account getAccountByToken (String token, Calendar currentDateTime) {
		if (token == null) {
			return null;
		}
		try {
			// 会員情報取得
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.selectByTokenAndTokenExpireTime(token, currentDateTime);
			// tokenが不正
			if (account == null) {
				// エラー
				return null;
			}
			return account;
		} catch (Exception e) {
			return null;
		}
	}
}