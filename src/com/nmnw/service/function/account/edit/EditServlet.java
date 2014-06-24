package com.nmnw.service.function.account.edit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.validator.AccountValidator;
import com.nmnw.service.utility.ExceptionUtility;

@WebServlet(name="account/edit", urlPatterns={"/account/edit"})
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct
	 */
	public EditServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
 		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		String action = request.getParameter("action");
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ACCOUNT_EDIT + "Edit.jsp";
		// �ҏW��ʕ\��
		if ("edit".equals(action)) {
			try {
				// parameter
				AccountDao accountdao = new AccountDao();
				Account result = accountdao.selectByAccountId(Integer.parseInt(request.getParameter("account_id")));
				// �Y���f�[�^���Ȃ��ꍇ
				if (result.getId() == 0) {
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
				} else {
					errorMessageList.add("");
				}
				request.setAttribute("result", result);
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		} else if ("edit_end".equals(action)) {
		// �ҏW����
			try {
				// validation
				AccountValidator av = new AccountValidator();
				av.checkName(request.getParameter("account_name"));
				av.checkNameKana(request.getParameter("account_name_kana"));
				av.checkMail(request.getParameter("account_mail"));
				av.checkZipCode(request.getParameter("account_zip_code"));
				av.checkAddress(request.getParameter("account_address"));
				av.checkPhoneNumber(request.getParameter("account_phone_number"));

				errorMessageList = av.getValidationList();
				// ���̓G���[��
				if (errorMessageList.size() != 0) {
					request.setAttribute("errorMessageList", errorMessageList);
					request.setAttribute("inputDataList", inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
				} else {
					// �f�[�^�I�u�W�F�N�g�ɒl�Z�b�g
					Account account = new Account();
					account.setId(Integer.valueOf(request.getParameter("account_id")));
					account.setName(request.getParameter("account_name"));
					account.setNameKana(request.getParameter("account_name_kana"));
					account.setMail(request.getParameter("account_mail"));
					account.setZipCode(request.getParameter("account_zip_code"));
					account.setAddress(request.getParameter("account_address"));
					account.setPhoneNumber(request.getParameter("account_phone_number"));

					AccountDao accountdao = new AccountDao();
					// �X�V
					int updateCount = accountdao.update(account);
					if (updateCount != 1) {
						// �G���[
						errorMessageList.add(MessageConstants.MESSAGE_EDIT_FAILED);
						request.setAttribute("errorMessageList", errorMessageList);
						request.setAttribute("inputDataList", inputDataList);
						request.getRequestDispatcher(page).forward(request, response);
						return;
					}
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ACCOUNT_DETAIL + "?account_id=" + request.getParameter("account_id") + "&action=edit_end";
					response.sendRedirect(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		} else {
		// action�p�����[�^�s���l
			errorMessageList.add(MessageConstants.MESSAGE_INVAILD_ACTION_PARAM);
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("inputDataList", inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
