package com.nmnw.admin.function.order.bulkShip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.constant.ValidatorConstants;
import com.nmnw.admin.dao.Order;
import com.nmnw.admin.dao.OrderDao;
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
		String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ORDER_SEARCH + "?action=bulkShip";
		HttpSession session = request.getSession();
		List<String> messageList = new ArrayList<String>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		try {
			// 注文IDを受け取っていなければ検索画面へリダイレクト
			if (request.getParameterValues("bulkShip") == null) {
				Validator v = new Validator();
				messageList.add(v.getMessage(ValidatorConstants.MESSAGE_REQUIRED_SELECT, FIELD_ORDER_ID));
				session.setAttribute("messageList", messageList);
				session.setAttribute("inputDataList", inputDataList);
				response.sendRedirect(url);
			} else {
				// validation
				Validator v = new Validator();
				// 選択された注文ID取得
				String orderIdList[] = request.getParameterValues("bulkShip");
				List<String> successOrderIdList = new ArrayList<String>();
				List<String> errorOrderIdList = new ArrayList<String>();
				for (int i = 0; i < orderIdList.length; i++) {
					// 注文IDがint型でない場合、処理しない
					if (v.isInt(orderIdList[i], FIELD_ORDER_ID)) {
						errorOrderIdList.add(orderIdList[i]);
						continue;
					}
					int orderId = Integer.valueOf(orderIdList[i]);
					// 出荷関連カラムデータ確認
					OrderDao orderdao = new OrderDao();
					Order result = orderdao.selectByOrderId(orderId);
					// 該当データが存在しない場合、処理しない
					if (result.getOrderId() == 0) {
						errorOrderIdList.add(orderIdList[i]);
						continue;
					}
 					// キャンセル済、出荷済の場合、処理しない
					if (result.getCancelFlg() == true || result.getShippingFlg() == true ) {
						errorOrderIdList.add(orderIdList[i]);
						continue;
					}
					// 出荷情報更新
					int successOrderId = orderdao.updateShippingStatus(orderId);
					successOrderIdList.add(orderIdList[i]);
				}
				if (!successOrderIdList.isEmpty()) {
					int count = successOrderIdList.size();
					messageList.add(count + MessageConstants.MESSAGE_BULK_SHIPPING_SUCCESS);
				}
				if (!errorOrderIdList.isEmpty()) {
					int count = errorOrderIdList.size();
					messageList.add(count + MessageConstants.MESSAGE_BULK_SHIPPING_ERROR);
				}
				session.setAttribute("messageList", messageList);
				session.setAttribute("inputDataList", inputDataList);
				response.sendRedirect(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMessage = e.getStackTrace().toString();
			session.setAttribute("exceptionMessage", exceptionMessage);
			url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ERROR;
			response.sendRedirect(url);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}