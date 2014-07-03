package com.nmnw.service.function.account.register;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.validator.AccountValidator;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;

@WebServlet(name="account/register", urlPatterns={"/account/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String VALUE_ACTION_REGIST_END = "regist_end";
	private static final String VALUE_ACTION_DUPLICATION_CHECK = "duplication_check";
	private static final String KEY_NAME = "account_name";
	private static final String KEY_NAME_KANA = "account_name_kana";
	private static final String KEY_MAIL = "account_mail";
	private static final String KEY_ZIP_CODE = "account_zip_code";
	private static final String KEY_ADDRESS = "account_address";
	private static final String KEY_PHONE_NUMBER = "account_phone_number";
	private static final String DUPLICATION_MAIL_ALERT_HTML = "<font color=\"red\">そのメールアドレスは既に登録されています。</font>";
	
	/**
	 * Construct
	 */
	public RegisterServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ACCOUNT_REGISTER + "Register.jsp";
		AccountDao accountDao = new AccountDao();
		// 入力画面表示
		if (request.getParameter(REQUEST_KEY_ACTION) == null) {
			request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// メール重複チェック
		if (VALUE_ACTION_DUPLICATION_CHECK.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			try {
				AccountValidator av = new AccountValidator();
				av.checkMail(request.getParameter(KEY_MAIL));
				errorMessageList = av.getValidationList();
				if (errorMessageList.size() != 0) {
					return;
				}
				Account account = accountDao.selectByMail(request.getParameter(KEY_MAIL));
				// 既に対象メールアドレスの登録有
				if (account.getMail() != null && account.getMail().length() > 0) {
					response.getWriter().write(DUPLICATION_MAIL_ALERT_HTML);
					return;
				}
				response.getWriter().write("");
				return;
			} catch (Exception e) {
				return;
			}
		}
		// 新規登録
		if (VALUE_ACTION_REGIST_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			try {
				// validation
				AccountValidator av = new AccountValidator();
				av.checkName(request.getParameter(KEY_NAME));
				av.checkNameKana(request.getParameter(KEY_NAME_KANA));
				av.checkMail(request.getParameter(KEY_MAIL));
				av.checkZipCode(request.getParameter(KEY_ZIP_CODE));
				av.checkAddress(request.getParameter(KEY_ADDRESS));
				av.checkPhoneNumber(request.getParameter(KEY_PHONE_NUMBER));
		
				errorMessageList = av.getValidationList();
				// 入力エラーの場合
				if (errorMessageList.size() != 0) {
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// メールアドレス重複チェック
				Account a = accountDao.selectByMail(request.getParameter(KEY_MAIL));
				// 既に対象メールアドレスの登録有
				if (a.getMail() != null && a.getMail().length() > 0) {
					errorMessageList.add(MessageConstants.MESSAGE_DUPLICATION_MAIL);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// set to data object
				Account account = new Account();
				account.setName(request.getParameter(KEY_NAME));
				account.setNameKana(request.getParameter(KEY_NAME_KANA));
				account.setMail(request.getParameter(KEY_MAIL));
				account.setZipCode(request.getParameter(KEY_ZIP_CODE));
				account.setAddress(request.getParameter(KEY_ADDRESS));
				account.setPhoneNumber(request.getParameter(KEY_PHONE_NUMBER));
	
				// insert
				String accountId = String.valueOf(accountDao.insert(account));
				String url = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_ACCOUNT_DETAIL + "?account_id=" + accountId + "&action=regist_end";
				response.sendRedirect(url);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
				return;
			}
		}
		// エラー
		errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
		request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}