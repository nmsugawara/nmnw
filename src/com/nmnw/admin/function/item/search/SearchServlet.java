package com.nmnw.admin.function.item.search;

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
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.validator.ItemValidator;
import com.nmnw.admin.utility.RequestParameterUtility;

@WebServlet(name="admin/item/search", urlPatterns={"/admin/item/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		List<String> errorMessageList = new ArrayList<String>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String action = request.getParameter("action");
		String page = ConfigConstants.JSP_DIR_ITEM_SEARCH + "Search.jsp";
		if ("search".equals(action)) {
			// validation
			ItemValidator iv = new ItemValidator();
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_id"))) {
				iv.checkSearchId(request.getParameter("search_id"));
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_from"))) {
				iv.checkSearchDateFrom(request.getParameter("search_sales_period_from"));
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_to"))) {
				iv.checkSearchDateTo(request.getParameter("search_sales_period_to"));
			}

			errorMessageList = iv.getValidationList();
			// has error: go back search page
			if (errorMessageList.size() != 0) {
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				try {
					int searchId = ConfigConstants.NULL_INT;
					String searchName = "";
					String searchCategory = "";
					String searchDateFrom = "";
					String searchDateTo = "";
					// parameter
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_id"))) {
						searchId = (Integer.parseInt(request.getParameter("search_id")));
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_name"))) {
						searchName = request.getParameter("search_name");
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_category"))) {
						searchCategory = request.getParameter("search_category");
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_from"))) {
						searchDateFrom = request.getParameter("search_sales_period_from");
					}
					if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_to"))) {
						searchDateTo = request.getParameter("search_sales_period_to");
					}
					ItemDao itemdao = new ItemDao();
					List<Item> itemList = itemdao.selectBySearch(searchId, searchName, searchCategory, searchDateFrom, searchDateTo);

					request.setAttribute("result", itemList);
					errorMessageList.add("");
					request.setAttribute("errorMessageList", errorMessageList);
					request.setAttribute("inputDataList", inputDataList);
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
		} else {
			errorMessageList.add("");
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("inputDataList", inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
