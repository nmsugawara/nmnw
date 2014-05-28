package com.nmnw.admin.function.item.edit;

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
import com.nmnw.admin.function.item.edit.EditModel;

@WebServlet(name="admin/item/edit", urlPatterns={"/admin/item/edit"})
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/item/edit/";

	/**
	 * Construct
	 */
	public EditServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
 		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessages = new HashMap<String, String>();
		String action = request.getParameter("action");
		if ("edit".equals(action)) {
			try {
				// parameter
				Item item = new Item();
				item.setId(Integer.parseInt(request.getParameter("item_id")));
				ItemDao itemdao = new ItemDao();
				List<Item> itemList = itemdao.selectByItemId(item.getId());
				request.setAttribute("result", itemList);
				String page = DIR_BASE + "Edit.jsp";
				request.getRequestDispatcher(page).forward(request, response);
			} catch (SQLException e) {
					// エラーページへ
			} catch (ClassNotFoundException ex) {
					// エラーページへ
			}
		} else {
			// parameter
			Item item = new Item();
			item.setId(Integer.parseInt(request.getParameter("item_id")));
			item.setName(request.getParameter("item_name"));
			item.setPrice(Integer.parseInt(request.getParameter("item_price")));
			item.setCategory(Integer.parseInt(request.getParameter("item_category")));
			item.setExplanation(request.getParameter("item_explanation"));
			item.setSalesPeriodFrom(request.getParameter("item_sales_period_from"));
			item.setSalesPeriodTo(request.getParameter("item_sales_period_to"));
			item.setStock(Integer.parseInt(request.getParameter("item_stock")));
			item.setImageUrl("http://yahoo.co.jp");

			try {
				EditModel model = new EditModel();
				if (model.validationCheck(item) == false) {
					// ValidationCheckExceptionをthrow？
				}
				ItemDao itemdao = new ItemDao();
				String itemId = String.valueOf(itemdao.update(item));
				String url = "http://localhost:8080/nmnw/admin/item/detail?item_id=" + itemId + "&action=edit_end";
				response.sendRedirect(url);
//				} catch (ValidationCheckException e) {
				// ErrorをセットしてNewへ
			} catch (SQLException e) {
				// エラーページへ
			} catch (ClassNotFoundException ex) {
				// エラーページへ
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
