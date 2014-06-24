package com.nmnw.service.function.item.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Item;
import com.nmnw.service.dao.ItemDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RequestParameterUtility;

@WebServlet(name="item/search", urlPatterns={"/item/search"})
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
		if (action != null && !"search".equals(action)) {
			// ÉGÉâÅ[
			errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
			request.setAttribute("errorMessageList", errorMessageList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// ëSåüçı
		if (action == null) {
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// èåèåüçı
		try {
			String currentDateTime = DateConversionUtility.getCurrentDateString();
			String searchName = "";
			String searchCategory = "";
			String searchSort = "";
			String searchDateFrom = currentDateTime;
			String searchDateTo = currentDateTime;
			// parameter
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_name"))) {
				searchName = request.getParameter("search_name");
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_category"))) {
				searchCategory = request.getParameter("search_category");
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter("search_sort"))) {
				searchSort = request.getParameter("search_sort");
			}
			ItemDao itemdao = new ItemDao();
			List<Item> itemList = itemdao.selectBySearch(searchName, searchCategory, searchDateFrom, searchDateTo, searchSort);

			request.setAttribute("result", itemList);
			request.setAttribute("inputDataList", inputDataList);
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
