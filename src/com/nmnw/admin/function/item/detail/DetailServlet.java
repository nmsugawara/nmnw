package com.nmnw.admin.function.item.detail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/item/detail", urlPatterns={"/admin/item/detail"})
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
		String page = ConfigConstants.JSP_DIR_ITEM_DETAIL + "Detail.jsp";

		try {
			Item item = new Item();
			item.setId(Integer.parseInt(request.getParameter("item_id")));
			ItemDao itemdao = new ItemDao();
			Item result = itemdao.selectByItemId(item.getId());
			request.setAttribute("result", result);
			request.setAttribute("message", "");
			// äYìñÉfÅ[É^Ç™Ç»Ç¢èÍçá
			if (result.getId() == 0) {
				request.setAttribute("message", MessageConstants.MESSAGE_NO_DATA);
			} else {
			// äYìñÉfÅ[É^Ç™Ç†ÇÈèÍçá
				// êVãKìoò^äÆóπ
				if ("new_end".equals(request.getParameter("action"))) {
					request.setAttribute("message", MessageConstants.MESSAGE_NEW_END);
				// ï“èWäÆóπ
				} else if ("edit_end".equals(request.getParameter("action"))) {
					request.setAttribute("message", MessageConstants.MESSAGE_EDIT_END);
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