package com.nmnw.service.function.order.cancel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.validator.Validator;
import com.nmnw.service.dao.Order;
import com.nmnw.service.dao.OrderDao;

@WebServlet(name="order/cancel", urlPatterns={"/order/cancel"})
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIELD_ORDER_ID = "注文ID";
	private static final String SESSION_KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String SESSION_KEY_ORDER_ID = "orderId";
	private static final String KEY_ORDER_ID = "order_id";
	private static final String KEY_ID = "id";

	/**
	 * Construct
	 */
	public CancelServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ORDER_CANCEL + "Cancel.jsp";
		String orderId = request.getParameter(KEY_ORDER_ID);
		List<String> errorMessageList = new ArrayList<String>();
		try {
			// ログインチェック
			HttpSession session = request.getSession();
			// ログインしてない場合
			if (session.getAttribute(KEY_ID) == null) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
				request.setAttribute(SESSION_KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			// パラメータチェック
			Validator v = new Validator();
			if (!v.required(orderId, FIELD_ORDER_ID)) {
				v.isInt(orderId, FIELD_ORDER_ID);
			}

			errorMessageList = v.getErrorMessageList();
			if (errorMessageList.size() != 0) {
				// エラー
				request.setAttribute(SESSION_KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			OrderDao orderDao = new OrderDao();
			Order order = orderDao.selectByOrderId(Integer.valueOf(orderId));
			// 対象データ存在チェック
			if (order.getOrderId() == 0) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
				request.setAttribute(SESSION_KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			// キャンセル処理
			if (orderDao.updateCancelStatus(Integer.valueOf(orderId)) != 1) {
				// エラー
				errorMessageList.add(MessageConstants.MESSAGE_CANCEL_ORDER_FAILED);
				request.setAttribute("errorMessageList", errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			// 正常に1件更新されていた場合
			request.setAttribute(SESSION_KEY_ORDER_ID, orderId);
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