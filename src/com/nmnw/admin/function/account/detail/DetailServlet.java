package com.nmnw.admin.function.account.detail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.dao.Account;
import com.nmnw.admin.dao.AccountDao;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/account/detail", urlPatterns={"/admin/account/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACCOUNT_ID = "account_id";
	private static final String KEY_RESULT = "result";
	private static final String KEY_MESSAGE = "message";

	/**
	 * Construct
	 */
	public DetailServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ACCOUNT_DETAIL + "Detail.jsp";

		try {
			Account account = new Account();
			account.setId(Integer.parseInt(request.getParameter(REQUEST_KEY_ACCOUNT_ID)));
			AccountDao accountdao = new AccountDao();
			Account result = accountdao.selectByAccountId(account.getId());
			request.setAttribute(KEY_RESULT, result);
			request.setAttribute(KEY_MESSAGE, "");
			// �Y���f�[�^���Ȃ��ꍇ
			if (result.getId() == 0) {
				request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_NO_DATA);
			}
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
