package com.nmnw.admin.function.item.detail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/item/detail", urlPatterns={"/admin/item/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String REQUEST_VALUE_ACTION_NEW_END = "new_end";
	private static final String REQUEST_VALUE_ACTION_EDIT_END = "edit_end";
	private static final String REQUEST_KEY_ITEM_ID = "item_id";
	private static final String KEY_RESULT = "result";
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
		String page = ConfigConstants.JSP_DIR_ITEM_DETAIL + "Detail.jsp";

		try {
			Item item = new Item();
			item.setId(Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_ID)));
			ItemDao itemdao = new ItemDao();
			Item result = itemdao.selectByItemId(item.getId());
			request.setAttribute(KEY_RESULT, result);
			request.setAttribute(KEY_MESSAGE, "");
			// äYìñÉfÅ[É^Ç™Ç»Ç¢èÍçá
			if (result.getId() == 0) {
				request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_NO_DATA);
			} else {
			// äYìñÉfÅ[É^Ç™Ç†ÇÈèÍçá
				// êVãKìoò^äÆóπ
				if (REQUEST_VALUE_ACTION_NEW_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
					request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_NEW_END);
				// ï“èWäÆóπ
				} else if (REQUEST_VALUE_ACTION_EDIT_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
					request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_EDIT_END);
				}
			}
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