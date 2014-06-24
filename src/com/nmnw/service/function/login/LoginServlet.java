package com.nmnw.service.function.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.CipherUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;

@WebServlet(name="login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct
	 */
	public LoginServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_LOGIN + "Login.jsp";
		String action = request.getParameter("action");
		String message = "";
		// ���O�C����ʕ\��
		if (!"login".equals(action)) {
			request.getRequestDispatcher(page).forward(request, response);
		} else {
		// ���O�C������
			try {
				AccountDao accountDao = new AccountDao();
				// �p�X���[�h�Asalt�擾
				Account account = accountDao.selectByMail(request.getParameter("login_mail"));
				// salt���擾�ł��Ă�����
				if (account.getSalt() != null && account.getSalt().length() != 0) {
					// ���̓p�X���[�h��salt���n�b�V����
					String password = CipherUtility.enctypt(request.getParameter("login_password") + account.getSalt());
					// �ۑ�����Ă���p�X���[�h�Ƃ̔�r
					if (password.equals(account.getPassWord())) {
						// ���O�C������
						HttpSession session = request.getSession();
						session.setAttribute("id", account.getId());
						session.setAttribute("name", account.getName());
						String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ACCOUNT_REGISTER;
						response.sendRedirect(url);
						return;
					}
				}
				// ���O�C�����s
				message = MessageConstants.MESSAGE_LOGIN_FAILED;
				request.setAttribute("message", message);
				request.getRequestDispatcher(page).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}