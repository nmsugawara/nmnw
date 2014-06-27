package com.nmnw.service.function.purchase.confirm;

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

import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.ExceptionUtility;

@WebServlet(name="purchase/confirm", urlPatterns={"/purchase/confirm"})
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_CART = "cart";
	private static final String KEY_ACCOUNT = "account";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";

	/**
	 * Construct
	 */
	public ConfirmServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		String page = ConfigConstants.JSP_DIR_PURCHASE_CONFIRM + "Confirm.jsp";
		try {
			///////////////////
			// ログインチェック
			///////////////////
			HttpSession session = request.getSession();
			Integer loginId;
			// ログインしてない場合
			if (session.getAttribute("id") == null) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			loginId = (Integer)session.getAttribute("id");

			// Cartオブジェクトがなければ
			if (session.getAttribute(KEY_CART) == null) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_NO_CART_DATA);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			// 会員情報取得
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.selectByAccountId(loginId);

			request.setAttribute(KEY_ACCOUNT, account);
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
