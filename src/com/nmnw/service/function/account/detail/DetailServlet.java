package com.nmnw.service.function.account.detail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.utility.ExceptionUtility;

@WebServlet(name="account/detail", urlPatterns={"/account/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_RESULT = "result";
	private static final String KEY_MESSAGE = "message";
	private static final String KEY_ACTION = "action";
	private static final String VALUE_ACTION_REGIST_END = "regist_end";
	private static final String VALUE_ACTION_EDIT_END = "edit_end";
	private static final String KEY_ID = "account_id";

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
			account.setId(Integer.parseInt(request.getParameter(KEY_ID)));
			AccountDao accountdao = new AccountDao();
			Account result = accountdao.selectByAccountId(account.getId());
			request.setAttribute(KEY_RESULT, result);
			request.setAttribute(KEY_MESSAGE, "");
			// äYìñÉfÅ[É^Ç™Ç»Ç¢èÍçá
			if (result.getId() == 0) {
				request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_NO_DATA);
			} else {
			// äYìñÉfÅ[É^Ç™Ç†ÇÈèÍçá
				// êVãKìoò^äÆóπ
				if (VALUE_ACTION_REGIST_END.equals(request.getParameter(KEY_ACTION))) {
					request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_REGIST_END);
				// ï“èWäÆóπ
				} else if (VALUE_ACTION_EDIT_END.equals(request.getParameter(KEY_ACTION))) {
					request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_EDIT_END);
				}
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