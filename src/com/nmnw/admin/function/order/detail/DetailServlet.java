package com.nmnw.admin.function.order.detail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDetail;
import com.nmnw.admin.dao.OrderDao;
import com.nmnw.admin.dao.OrderDetailDao;

@WebServlet(name="admin/order/detail", urlPatterns={"/admin/order/detail"})
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
		String page = ConfigConstants.JSP_DIR_ORDER_DETAIL + "Detail.jsp";

		try {
			// íçï∂èÓïÒéÊìæ
			Order order = new Order();
			order.setOrderId(Integer.parseInt(request.getParameter("order_id")));
			OrderDao orderdao = new OrderDao();
			Order result = orderdao.selectByOrderId(order.getOrderId());
			request.setAttribute("result", result);
			// íçï∂è⁄ç◊èÓïÒéÊìæ
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(Integer.parseInt(request.getParameter("order_id")));
			OrderDetailDao orderdetaildao = new OrderDetailDao();
			List<OrderDetail> resultList = orderdetaildao.selectByOrderId(orderDetail.getOrderId());
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher(page).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMessage = e.getStackTrace().toString();
			HttpSession session = request.getSession();
			session.setAttribute("exceptionMessage", exceptionMessage);
			String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ERROR;
			response.sendRedirect(url);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
