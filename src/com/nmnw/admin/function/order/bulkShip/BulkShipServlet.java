package com.nmnw.admin.function.order.bulkShip;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDao;
import com.nmnw.admin.dao.OrderDetail;
import com.nmnw.admin.dao.OrderDetailDao;
import com.nmnw.admin.utility.RequestParameterUtility;
import com.nmnw.admin.validator.Validator;

@WebServlet(name="admin/order/bulkShip", urlPatterns={"/admin/order/bulkShip"})
public class BulkShipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIELD_ORDER_ID = "注文ID";

	/**
	 * Construct
	 */
	public BulkShipServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ORDER_SEARCH + "Search.jsp";

		try {
			// 注文IDを受け取っていなければ検索画面へリダイレクト
			if (RequestParameterUtility.isEmptyParam(request.getParameter("bulkShip"))) {
				String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ORDER_SEARCH;
				response.sendRedirect(url);
			}
			// validation
			Validator v = new Validator();
			// 選択された注文ID取得
			String orderIdList[] = request.getParameterValues("bulkShip");
			for (int i = 0; i < orderIdList.length; i++) {
				// 注文IDがint型でない場合、処理しない
				if (v.isInt(orderIdList[i], FIELD_ORDER_ID)) {
					continue;
				}
				int orderId = Integer.valueOf(orderIdList[i]);
				// 出荷関連カラムデータ確認
				OrderDao orderdao = new ItemDao();
				Order result = selectByOrderId(orderId);


			}
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