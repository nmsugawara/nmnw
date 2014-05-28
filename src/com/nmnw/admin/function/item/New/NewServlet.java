package com.nmnw.admin.function.item.New;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.validator.ItemValidator;

@WebServlet(name="admin/item/new", urlPatterns={"/admin/item/new"})
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/item/New/";

	/**
	 * Construct
	 */
	public NewServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		String action = request.getParameter("action");
		// new
		if (!("new_end".equals(action))) {
			String page = DIR_BASE + "New.jsp";
			request.getRequestDispatcher(page).forward(request, response);
		} else {
		// new end
			// validation
			ItemValidator iv = new ItemValidator();
			iv.checkName(request.getParameter("item_name"));
			iv.checkPrice(request.getParameter("item_price"));
			iv.checkCategory(request.getParameter("item_category"));
			iv.checkExplanation(request.getParameter("item_explanation"));
			iv.checkSalesPeriodFrom(request.getParameter("item_sales_period_from"));
			iv.checkSalesPeriodTo(request.getParameter("item_sales_period_to"));
			iv.checkStock(request.getParameter("item_stock"));

			errorMessageList = iv.getValidationList();
			// has error: go back new page
			if (errorMessageList.size() != 0) {
				String page = DIR_BASE + "New.jsp";
				request.setAttribute("errorMessageList", errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				try {
					// set to data object
					Item item = new Item();
					item.setName(request.getParameter("item_name"));
					item.setPrice(Integer.parseInt(request.getParameter("item_price")));
					item.setCategory(Integer.parseInt(request.getParameter("item_category")));
					item.setExplanation(request.getParameter("item_explanation"));
					item.setSalesPeriodFrom(request.getParameter("item_sales_period_from"));
					item.setSalesPeriodTo(request.getParameter("item_sales_period_to"));
					item.setStock(Integer.parseInt(request.getParameter("item_stock")));
					item.setImageUrl("http://yahoo.co.jp");
				
					ItemDao itemdao = new ItemDao();
					String itemId = String.valueOf(itemdao.insert(item));
					String url = "http://localhost:8080/nmnw/admin/item/detail?item_id=" + itemId + "&action=new_end";
					response.sendRedirect(url);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("error");
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
	
	protected Image loadImage (File f) {
		try {
			BufferedImage img = ImageIO.read(f);
			return img;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}