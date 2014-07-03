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
import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;

@WebServlet(name="account/edit", urlPatterns={"/account/edit"})
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_RESULT = "result";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String VALUE_ACTION_EDIT = "edit";
	private static final String VALUE_ACTION_EDIT_END = "edit_end";
	private static final String KEY_ID = "account_id";
	private static final String KEY_NAME = "account_name";
	private static final String KEY_NAME_KANA = "account_name_kana";
	private static final String KEY_MAIL = "account_mail";
	private static final String KEY_ZIP_CODE = "account_zip_code";
	private static final String KEY_ADDRESS = "account_address";
	private static final String KEY_PHONE_NUMBER = "account_phone_number";

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
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ACCOUNT_EDIT + "Edit.jsp";
		// 編集画面表示
		if (VALUE_ACTION_EDIT.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			try {
				// parameter
				AccountDao accountdao = new AccountDao();
				Account result = accountdao.selectByAccountId(Integer.parseInt(request.getParameter(KEY_ID)));
				// 該当データがない場合
				if (result.getId() == 0) {
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
				} else {
					errorMessageList.add("");
				}
				request.setAttribute(KEY_RESULT, result);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
				return;
			}
		}
		if (VALUE_ACTION_EDIT_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
		// 編集完了
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
				// 入力エラー時
				if (errorMessageList.size() != 0) {
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// データオブジェクトに値セット
				Account account = new Account();
				account.setId(Integer.valueOf(request.getParameter(KEY_ID)));
				account.setName(request.getParameter(KEY_NAME));
				account.setNameKana(request.getParameter(KEY_NAME_KANA));
				account.setMail(request.getParameter(KEY_MAIL));
				account.setZipCode(request.getParameter(KEY_ZIP_CODE));
				account.setAddress(request.getParameter(KEY_ADDRESS));
				account.setPhoneNumber(request.getParameter(KEY_PHONE_NUMBER));

				AccountDao accountdao = new AccountDao();
				// 更新
				int updateCount = accountdao.update(account);
				if (updateCount != 1) {
					// エラー
					errorMessageList.add(MessageConstants.MESSAGE_EDIT_FAILED);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				String url = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_ACCOUNT_DETAIL + "?account_id=" + request.getParameter("account_id") + "&action=edit_end";
				response.sendRedirect(url);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
				return;
			}
		} 
		// actionパラメータ不正値
		errorMessageList.add(MessageConstants.MESSAGE_INVAILD_ACTION_PARAM);
		request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
		request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
