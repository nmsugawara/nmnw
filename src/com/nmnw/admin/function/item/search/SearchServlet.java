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
import com.nmnw.admin.validator.Validator;
import com.nmnw.admin.utility.RequestParameterUtility;

@WebServlet(name="admin/item/search", urlPatterns={"/admin/item/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_FIELD_ID = "商品ID";
	private static final String SEARCH_FIELD_SALES_PERIOD_FROM = "販売期間(From）";
	private static final String SEARCH_FIELD_SALES_PERIOD_TO = "販売期間(To）";

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
			Validator v = new Validator();
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_id"))) {
				v.isInt(request.getParameter("search_id"), SEARCH_FIELD_ID);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_from"))) {
				if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_to"))) {
					// from,to有
					v.correctPeriod(request.getParameter("search_sales_period_from"), SEARCH_FIELD_SALES_PERIOD_FROM, request.getParameter("search_sales_period_to"), SEARCH_FIELD_SALES_PERIOD_TO);
				} else {
					// fromのみ
					v.isDate(request.getParameter("search_sales_period_from"), SEARCH_FIELD_SALES_PERIOD_FROM);
				}
			} else {
				if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sales_period_to"))) {
					// toのみ
					v.isDate(request.getParameter("search_sales_period_to"), SEARCH_FIELD_SALES_PERIOD_TO);
				}
			}

			errorMessageList = v.getErrorMessageList();
			// 入力エラー時
			if (errorMessageList.size() != 0) {
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
			// 検索
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
