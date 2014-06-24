package com.nmnw.service.function.account.detail;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RandomStringUtility;

@WebServlet(name="account/detail", urlPatterns={"/account/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			account.setId(Integer.parseInt(request.getParameter("account_id")));
			AccountDao accountdao = new AccountDao();
			Account result = accountdao.selectByAccountId(account.getId());
			request.setAttribute("result", result);
			request.setAttribute("message", "");
			// 該当データがない場合
			if (result.getId() == 0) {
				request.setAttribute("message", MessageConstants.MESSAGE_NO_DATA);
			} else {
			// 該当データがある場合
				// 新規登録完了
				if ("regist_end".equals(request.getParameter("action"))) {
					request.setAttribute("message", MessageConstants.MESSAGE_REGIST_END);
				// 編集完了
				} else if ("edit_end".equals(request.getParameter("action"))) {
					request.setAttribute("message", MessageConstants.MESSAGE_EDIT_END);
				}
				// パスワード変更用ｔｏｋｅｎ生成
				String token = RandomStringUtility.generateToken();
				// token有効期限
				Date tokenExpireTime = DateConversionUtility.getdaysAfterDate(ConfigConstants.TOKEN_EXPIRE_DAYS);
				Account updateAccount = new Account();
				updateAccount.setId(result.getId());
				updateAccount.setToken(token);
				updateAccount.setTokenExpireTime(tokenExpireTime);
				// DB格納
				int updateCount = accountdao.update(updateAccount);
				request.setAttribute("token", token);
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