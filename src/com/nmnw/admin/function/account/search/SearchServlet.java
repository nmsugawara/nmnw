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
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Account;
import com.nmnw.admin.dao.AccountDao;
import com.nmnw.admin.utility.ExceptionUtility;
import com.nmnw.admin.utility.RequestParameterUtility;
import com.nmnw.admin.validator.Validator;

@WebServlet(name="admin/account/search", urlPatterns={"/admin/account/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		String action = request.getParameter("action");
		String page = ConfigConstants.JSP_DIR_ACCOUNT_SEARCH + "Search.jsp";
		// 検索画面表示
		if (!"search".equals(action)) {
			errorMessageList.add("");
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("inputDataList", inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
		} else {
		// 検索処理
			// validation
			Validator v = new Validator();
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_id"))) {
				v.isInt(request.getParameter("search_id"), SEARCH_FIELD_ID);
			}

			errorMessageList = v.getErrorMessageList();
			// 入力チェックに該当時、エラーメッセージ表示
			if (errorMessageList.size() != 0) {
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				try {
					int searchId = ConfigConstants.NULL_INT;
					String searchName = "";
					String searchNameKana = "";
					String searchMail = "";
					// parameter
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_id"))) {
						searchId = (Integer.parseInt(request.getParameter("search_id")));
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_name"))) {
						searchName = request.getParameter("search_name");
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_name_kana"))) {
						searchNameKana = request.getParameter("search_name_kana");
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_mail"))) {
						searchMail = request.getParameter("search_mail");
					}
					AccountDao accountdao = new AccountDao();
					accountList = accountdao.selectBySearch(searchId, searchName, searchNameKana, searchMail);

					request.setAttribute("result", accountList);
					errorMessageList.add("");
					request.setAttribute("errorMessageList", errorMessageList);
					request.setAttribute("inputDataList", inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					ExceptionUtility.redirectErrorPage(request, response, e);
				}
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
