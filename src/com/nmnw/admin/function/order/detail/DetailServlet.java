package com.nmnw.admin.function.order.detail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDetail;
import com.nmnw.admin.dao.OrderDao;
import com.nmnw.admin.dao.OrderDetailDao;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/order/detail", urlPatterns={"/admin/order/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ORDER_ID = "order_id";
	private static final String KEY_RESULT = "result";
	private static final String KEY_RESULT_LIST = "resultList";
	private static final String KEY_MESSAGE = "message";

	
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
		String page = ConfigConstants.JSP_DIR_ORDER_DETAIL + "Detail.jsp";

		try {
			// íçï∂èÓïÒéÊìæ
			Order order = new Order();
			order.setOrderId(Integer.parseInt(request.getParameter(REQUEST_KEY_ORDER_ID)));
			OrderDao orderdao = new OrderDao();
			Order result = orderdao.selectByOrderId(order.getOrderId());
			request.setAttribute(KEY_RESULT, result);
			request.setAttribute(KEY_MESSAGE, "");
			// äYìñÉfÅ[É^Ç™Ç»Ç¢èÍçá
			if (result.getOrderId() == 0) {
				request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_NO_DATA);
			}
			// íçï∂è⁄ç◊èÓïÒéÊìæ
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(Integer.parseInt(request.getParameter(REQUEST_KEY_ORDER_ID)));
			OrderDetailDao orderdetaildao = new OrderDetailDao();
			List<OrderDetail> resultList = orderdetaildao.selectByOrderId(orderDetail.getOrderId());
			request.setAttribute(KEY_RESULT_LIST, resultList);
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
