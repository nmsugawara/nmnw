package com.nmnw.admin.function.item.detail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;

@WebServlet(name="admin/item/detail", urlPatterns={"/admin/item/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/item/detail/";
	private static final String MESSAGE_NEW_END = "ìoò^Ç™äÆóπÇµÇ‹ÇµÇΩÅB";
	private static final String MESSAGE_EDIT_END = "ï“èWÇ™äÆóπÇµÇ‹ÇµÇΩÅB";

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
		String page = DIR_BASE + "Detail.jsp";

		try {
			Item item = new Item();
			item.setId(Integer.parseInt(request.getParameter("item_id")));
			ItemDao itemdao = new ItemDao();
			Item result = itemdao.selectByItemId(item.getId());
			request.setAttribute("result", result);
			// get message
			request.setAttribute("message", "");
			if ("new_end".equals(request.getParameter("action"))) {
				request.setAttribute("message", MESSAGE_NEW_END);
			} else if ("edit_end".equals(request.getParameter("action"))) {
				request.setAttribute("message", MESSAGE_EDIT_END);
			}
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