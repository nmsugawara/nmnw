package com.nmnw.admin.function.order.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDao;
import com.nmnw.admin.utility.ExceptionUtility;
import com.nmnw.admin.utility.RequestParameterUtility;
import com.nmnw.admin.validator.Validator;

@WebServlet(name="admin/order/search", urlPatterns={"/admin/order/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String REQUEST_VALUE_ACTION_SEARCH = "search";
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";

	private static final String SEARCH_FIELD_ORDER_ID = "����ID";
	private static final String SEARCH_FIELD_ORDER_DATE_FROM = "�������ԁiFrom�j";
	private static final String SEARCH_FIELD_ORDER_DATE_TO = "���������ԁiTo�j";
	private static final String SEARCH_FIELD_ACCOUNT_NAME = "�����";
	private static final String SEARCH_FIELD_ACCOUNT_NAME_KANA = "������J�i";
	private static final String SEARCH_FIELD_ACCOUNT_MAIL = "���[���A�h���X";
	private static final String SEARCH_FIELD_ACCOUNT_PHONE_NUMBER = "�d�b�ԍ�";
	private static final String SEARCH_FIELD_CANCEL_FLG = "�L�����Z����";
	private static final String SEARCH_FIELD_CANCEL_DATE = "�L�����Z����";
	private static final String SEARCH_FIELD_SHIPPING_FLG = "�o�׏�";
	private static final String SEARCH_FIELD_SHIPPING_DATE = "�o�ד�";

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
		List<Order> orderList = new ArrayList<Order>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ORDER_SEARCH + "Search.jsp";
		// ������ʕ\��
		if (!REQUEST_VALUE_ACTION_SEARCH.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// ��������
		// validation
		Validator v = new Validator();
		// ����ID
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_id"))) {
			v.isInt(request.getParameter("search_order_id"), SEARCH_FIELD_ORDER_ID);
		}
		// ������
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_date_from"))) {
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_date_to"))) {
				// from,to�L
				v.correctPeriod(request.getParameter("search_order_date_from"), SEARCH_FIELD_ORDER_DATE_FROM, request.getParameter("search_order_date_to"), SEARCH_FIELD_ORDER_DATE_TO);
			} else {
				// from�̂�
				v.isDate(request.getParameter("search_order_date_from"), SEARCH_FIELD_ORDER_DATE_FROM);
			}
		} else {
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_date_to"))) {
				// to�̂�
				v.isDate(request.getParameter("search_order_date_to"), SEARCH_FIELD_ORDER_DATE_TO);
			}
		}
		// �L�����Z���t���O
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_cancel_flg"))) {
			v.isInt(request.getParameter("search_cancel_flg"), SEARCH_FIELD_ORDER_ID);
		}
		// �L�����Z����
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_cancel_date"))) {
			v.isDate(request.getParameter("search_cancel_date"), SEARCH_FIELD_CANCEL_DATE);
		}
		// �o�׃t���O
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_shipping_flg"))) {
			v.isInt(request.getParameter("search_shipping_flg"), SEARCH_FIELD_SHIPPING_FLG);
		}
		// �o�ד�
		if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_shipping_date"))) {
			v.isDate(request.getParameter("search_shipping_date"), SEARCH_FIELD_SHIPPING_DATE);
		}

		errorMessageList = v.getErrorMessageList();
		// ���̓`�F�b�N�ɊY�����A�G���[���b�Z�[�W�\��
		if (errorMessageList.size() != 0) {
			request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		try {
			Map<String, Map<String, String>> searchParameterList = new LinkedHashMap<String, Map<String, String>>();

			// parameter
			// ����ID
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_id"))) {
				searchParameterList.put("order_id", new HashMap<String, String>());
				searchParameterList.get("order_id").put("type", "int");
				searchParameterList.get("order_id").put("value", request.getParameter("search_order_id"));
			}
			// ������From
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_date_from"))) {
				searchParameterList.put("order_date_from", new HashMap<String, String>());
				searchParameterList.get("order_date_from").put("type", "String");
				searchParameterList.get("order_date_from").put("value", request.getParameter("search_order_date_from"));
			}
			// ������To
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_order_date_to"))) {
				searchParameterList.put("order_date_to", new HashMap<String, String>());
				searchParameterList.get("order_date_to").put("type", "String");
				searchParameterList.get("order_date_to").put("value", request.getParameter("search_order_date_to"));
			}
			// �����
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_account_name"))) {
				searchParameterList.put("account_name", new HashMap<String, String>());
				searchParameterList.get("account_name").put("type", "String");
				searchParameterList.get("account_name").put("value", request.getParameter("search_account_name"));
			}
			// ������J�i
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_account_name_kana"))) {
				searchParameterList.put("account_name_kana", new HashMap<String, String>());
				searchParameterList.get("account_name_kana").put("type", "String");
				searchParameterList.get("account_name_kana").put("value", request.getParameter("search_account_name_kana"));
			}
			// ���[���A�h���X
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_account_mail"))) {
				searchParameterList.put("account_mail", new HashMap<String, String>());
				searchParameterList.get("account_mail").put("type", "String");
				searchParameterList.get("account_mail").put("value", request.getParameter("search_account_mail"));
			}
			// �d�b�ԍ�
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_account_phone_number"))) {
				searchParameterList.put("account_phone_number", new HashMap<String, String>());
				searchParameterList.get("account_phone_number").put("type", "String");
				searchParameterList.get("account_phone_number").put("value", request.getParameter("search_account_phone_number"));
			}
			// �L�����Z����
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_cancel_date"))) {
				searchParameterList.put("cancel_date", new HashMap<String, String>());
				searchParameterList.get("cancel_date").put("type", "String");
				searchParameterList.get("cancel_date").put("value", request.getParameter("search_cancel_date"));
			}
			// �o�ד�
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_shipping_date"))) {
				searchParameterList.put("shipping_date", new HashMap<String, String>());
				searchParameterList.get("shipping_date").put("type", "String");
				searchParameterList.get("shipping_date").put("value", request.getParameter("search_shipping_date"));
			}
			OrderDao orderdao = new OrderDao();
			orderList = orderdao.selectBySearch(searchParameterList);

			request.setAttribute(KEY_RESULT, orderList);
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
