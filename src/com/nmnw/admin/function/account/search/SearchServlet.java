package com.nmnw.admin.function.account.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Account;
import com.nmnw.admin.dao.AccountDao;
import com.nmnw.admin.utility.ExceptionUtility;
import com.nmnw.admin.utility.RequestParameterUtility;
import com.nmnw.admin.validator.Validator;

@WebServlet(name="admin/account/search", urlPatterns={"/admin/account/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String REQUEST_VALUE_ACTION_SEARCH = "search";
	private static final String REQUEST_KEY_ACCOUNT_ID = "search_id";
	private static final String REQUEST_KEY_ACCOUNT_NAME = "search_name";
	private static final String REQUEST_KEY_ACCOUNT_NAME_KANA = "search_name_kana";
	private static final String REQUEST_KEY_ACCOUNT_MAIL = "search_mail";
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";

	private static final String SEARCH_FIELD_ID = "会員ID";

	/**
	 * Construct
	 */
	public SearchServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		List<Account> accountList = new ArrayList<Account>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ACCOUNT_SEARCH + "Search.jsp";
		// 検索画面表示
		if (!REQUEST_VALUE_ACTION_SEARCH.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// 検索処理
		// validation
		Validator v = new Validator();
		if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ACCOUNT_ID))) {
			v.isInt(request.getParameter(REQUEST_KEY_ACCOUNT_ID), SEARCH_FIELD_ID);
		}

		errorMessageList = v.getErrorMessageList();
		// 入力チェックに該当時、エラーメッセージ表示
		if (errorMessageList.size() != 0) {
			request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		try {
			int searchId = ConfigConstants.NULL_INT;
			String searchName = "";
			String searchNameKana = "";
			String searchMail = "";
			// parameter
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ACCOUNT_ID))) {
				searchId = (Integer.parseInt(request.getParameter(REQUEST_KEY_ACCOUNT_ID)));
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ACCOUNT_NAME))) {
				searchName = request.getParameter(REQUEST_KEY_ACCOUNT_NAME);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA))) {
				searchNameKana = request.getParameter(REQUEST_KEY_ACCOUNT_NAME_KANA);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ACCOUNT_MAIL))) {
				searchMail = request.getParameter(REQUEST_KEY_ACCOUNT_MAIL);
			}
			AccountDao accountdao = new AccountDao();
			accountList = accountdao.selectBySearch(searchId, searchName, searchNameKana, searchMail);

			request.setAttribute(KEY_RESULT, accountList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
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
