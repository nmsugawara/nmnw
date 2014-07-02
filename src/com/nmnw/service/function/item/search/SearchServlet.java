package com.nmnw.service.function.item.search;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.dao.Item;
import com.nmnw.service.dao.ItemDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.RequestParameterUtility;

@WebServlet(name="item/search", urlPatterns={"/item/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_SEARCH_NAME = "search_name";
	private static final String KEY_SEARCH_CATEGORY = "search_category";
	private static final String KEY_SEARCH_SORT = "search_sort";

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
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ITEM_SEARCH + "Search.jsp";
		// åüçı
		try {
			String currentDateTime = DateConversionUtility.getCurrentDateString();
			String searchName = "";
			String searchCategory = "";
			String searchSort = "";
			String searchDateFrom = currentDateTime;
			String searchDateTo = currentDateTime;
			// parameter
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(KEY_SEARCH_NAME))) {
				searchName = request.getParameter(KEY_SEARCH_NAME);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(KEY_SEARCH_CATEGORY))) {
				searchCategory = request.getParameter(KEY_SEARCH_CATEGORY);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(KEY_SEARCH_SORT))) {
				searchSort = request.getParameter(KEY_SEARCH_SORT);
			}
			ItemDao itemdao = new ItemDao();
			List<Item> itemList = itemdao.selectBySearch(searchName, searchCategory, searchDateFrom, searchDateTo, searchSort);

			request.setAttribute(KEY_RESULT, itemList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
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
