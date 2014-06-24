package com.nmnw.service.function.account.register;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RandomStringUtility;
import com.nmnw.service.validator.AccountValidator;
import com.nmnw.service.constant.ConfigConstants;

@WebServlet(name="account/register", urlPatterns={"/account/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String action = request.getParameter("action");
		String page = ConfigConstants.JSP_DIR_ACCOUNT_REGISTER + "Register.jsp";
		HttpSession session = request.getSession();
		// ì¸óÕâÊñ ï\é¶
		if (!("regist_end".equals(action))) {
			errorMessageList.add("");
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("inputDataList", inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
		} else {
		// êVãKìoò^
			// validation
			AccountValidator av = new AccountValidator();
			av.checkName(request.getParameter("account_name"));
			av.checkNameKana(request.getParameter("account_name_kana"));
			av.checkMail(request.getParameter("account_mail"));
			av.checkZipCode(request.getParameter("account_zip_code"));
			av.checkAddress(request.getParameter("account_address"));
			av.checkPhoneNumber(request.getParameter("account_phone_number"));

			errorMessageList = av.getValidationList();
			// ì¸óÕÉGÉâÅ[ÇÃèÍçá
			if (errorMessageList.size() != 0) {
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				try {
					// set to data object
					Account account = new Account();
					account.setName(request.getParameter("account_name"));
					account.setNameKana(request.getParameter("account_name_kana"));
					account.setMail(request.getParameter("account_mail"));
					account.setZipCode(request.getParameter("account_zip_code"));
					account.setAddress(request.getParameter("account_address"));
					account.setPhoneNumber(request.getParameter("account_phone_number"));

					AccountDao accountdao = new AccountDao();
					// insert
					String accountId = String.valueOf(accountdao.insert(account));
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ACCOUNT_DETAIL + "?account_id=" + accountId + "&action=regist_end";
					response.sendRedirect(url);
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