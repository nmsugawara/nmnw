package com.nmnw.admin.function.item.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;

@WebServlet(name="admin/item/search", urlPatterns={"/admin/item/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/item/search/";

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
		Map<String, String> errorMessages = new HashMap<String, String>();
		String page = DIR_BASE + "Search.jsp";
		String action = request.getParameter("action");

		if ("search".equals(action)) {
			try {
				// parameter
				Item searchInfo = new Item();
/*
				searchInfo.setItemId(Integer.parseInt(request.getParameter("item_id")));
				searchInfo.setItemName(request.getParameter("item_name"));
				searchInfo.setItemPrice(Integer.parseInt(request.getParameter("item_price")));
				searchInfo.setItemCategory(Integer.parseInt(request.getParameter("item_category")));
				searchInfo.setItemSalesPeriodFrom(request.getParameter("item_sales_period_from"));
				searchInfo.setItemSalesPeriodTo(request.getParameter("item_sales_period_to"));
				searchInfo.setItemStock(Integer.parseInt(request.getParameter("item_stock")));
*/
				ItemDao itemdao = new ItemDao();
				List<Item> itemList = itemdao.selectAll();
				request.setAttribute("result", itemList);
				request.getRequestDispatcher(page).forward(request, response);
//			} catch (ValidationCheckException e) {
				// ErrorをセットしてNewへ
			} catch (SQLException e) {
				// エラーページへ
			} catch (ClassNotFoundException ex) {
				// エラーページへ
			}
		} else {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
