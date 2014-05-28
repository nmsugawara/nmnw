package com.nmnw.admin.function.item.detail;

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

@WebServlet(name="admin/item/detail", urlPatterns={"/admin/item/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/item/detail/";

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
		Map<String, String> errorMessages = new HashMap<String, String>();
		String page = DIR_BASE + "Detail.jsp";

		// parameter
		Item item = new Item();
		item.setId(Integer.parseInt(request.getParameter("item_id")));
		try {
			ItemDao itemdao = new ItemDao();
			List<Item> itemList = itemdao.selectByItemId(item.getId());
			request.setAttribute("result", itemList);
			// message
			request.setAttribute("message", "");
			if ("new_end".equals(request.getParameter("action"))) {
				request.setAttribute("message", "�o�^���������܂����B");
			} else if ("edit_end".equals(request.getParameter("action"))) {
				request.setAttribute("message", "�ҏW���������܂����B");
			}
			request.getRequestDispatcher(page).forward(request, response);
//			} catch (ValidationCheckException e) {
			// Error���Z�b�g����New��
		} catch (SQLException e) {
			// �G���[�y�[�W��
		} catch (ClassNotFoundException ex) {
			// �G���[�y�[�W��
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
