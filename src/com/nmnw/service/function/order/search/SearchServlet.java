package com.nmnw.service.function.order.search;

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

import com.nmnw.service.dao.OrderDetail;
import com.nmnw.service.dao.OrderDetailDao;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Order;
import com.nmnw.service.dao.OrderDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RequestParameterUtility;
import com.nmnw.service.validator.Validator;

@WebServlet(name="order/search", urlPatterns={"/order/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_ID = "id";
	private static final String KEY_ORDER_PERIOD = "order_period";
	private static final String KEY_ORDER = "order";
	private static final String KEY_ORDER_DETAIL = "orderDetail";
	private static final String KEY_ORDER_STATUS = "orderStatus";
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";
	private static final String ORDER_STATUS_ORDER = "������";
	private static final String ORDER_STATUS_ORDER_CANCEL = "�L�����Z��";
	private static final String ORDER_STATUS_SHIPPING = "������";
	private static final String ORDER_STATUS_SHIPPING_CANCEL = "������L�����Z��";
	
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
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ORDER_SEARCH + "Search.jsp";
		// ���O�C�����擾
		HttpSession session = request.getSession();
		Integer loginId;
		// ���O�C�����ĂȂ��ꍇ
		if (session.getAttribute(KEY_ID) == null) {
			// �G���[
			errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
			request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		loginId = (Integer)session.getAttribute(KEY_ID);
		// ����
		try {
			// �����擾
			String orderPeriod = "";
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(KEY_ORDER_PERIOD))) {
				orderPeriod = DateConversionUtility.getdaysAfterString(Integer.valueOf(request.getParameter("order_period")));
			}
			OrderDao orderdao = new OrderDao();
			orderList = orderdao.selectByAccountIdAndOrderPeriod(loginId.intValue(), orderPeriod);
			// �����ڍ׎擾
			for (int i = 0; i < orderList.size(); i++) {
				Map<String, Object> orderInfo = new HashMap<String, Object>();
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderList.get(i).getOrderId());
				orderInfo.put(KEY_ORDER, orderList.get(i));
				orderInfo.put(KEY_ORDER_DETAIL, orderDetailList);
				orderInfo.put(KEY_ORDER_STATUS, getOrderStatus(orderList.get(i).getShippingFlg(), orderList.get(i).getCancelFlg()));
				resultList.add(orderInfo);
			}
			request.setAttribute(KEY_RESULT, resultList);
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

	/**
	 * �����X�e�[�^�X�擾
	 * @param shippingFlg
	 * @param cancelFlg
	 * @return
	 */
	private String getOrderStatus (boolean shippingFlg, boolean cancelFlg) {
		String orderStatus = "";
		if (shippingFlg) {
			if (cancelFlg) {
				// �z����L�����Z��
				orderStatus = ORDER_STATUS_SHIPPING_CANCEL;
			} else {
				// �z����
				orderStatus = ORDER_STATUS_SHIPPING;
			}
		} else {
			if (cancelFlg) {
				// �����L�����Z��
				orderStatus = ORDER_STATUS_ORDER_CANCEL;
			} else {
				// ������
				orderStatus = ORDER_STATUS_ORDER;				
			}
		}
		return orderStatus;
	}
}
