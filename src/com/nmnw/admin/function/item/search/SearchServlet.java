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

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.validator.Validator;
import com.nmnw.admin.utility.ExceptionUtility;
import com.nmnw.admin.utility.RequestParameterUtility;

@WebServlet(name="admin/item/search", urlPatterns={"/admin/item/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String REQUEST_VALUE_ACTION_SEARCH = "search";
	private static final String REQUEST_KEY_ITEM_ID = "search_id";
	private static final String REQUEST_KEY_ITEM_NAME = "search_name";
	private static final String REQUEST_KEY_ITEM_CATEGORY = "search_category";
	private static final String REQUEST_KEY_ITEM_PERIOD_FROM = "search_sales_period_from";
	private static final String REQUEST_KEY_ITEM_PERIOD_TO = "search_sales_period_to";
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";
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
		String page = ConfigConstants.JSP_DIR_ITEM_SEARCH + "Search.jsp";
		// 検索画面表示
		if (!REQUEST_VALUE_ACTION_SEARCH.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// 検索処理
		// validation
		Validator v = new Validator();
		if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_ID))) {
			v.isInt(request.getParameter(REQUEST_KEY_ITEM_ID), SEARCH_FIELD_ID);
		}
		if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM))) {
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO))) {
				// from,to有
				v.correctPeriod(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM), SEARCH_FIELD_SALES_PERIOD_FROM, request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO), SEARCH_FIELD_SALES_PERIOD_TO);
			} else {
				// fromのみ
				v.isDate(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM), SEARCH_FIELD_SALES_PERIOD_FROM);
			}
		} else {
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO))) {
				// toのみ
				v.isDate(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO), SEARCH_FIELD_SALES_PERIOD_TO);
			}
		}

		errorMessageList = v.getErrorMessageList();
		// 入力エラー時
		if (errorMessageList.size() != 0) {
			request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
			request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
			request.getRequestDispatcher(page).forward(request, response);
			return;
		}
		// 検索
		try {
			int searchId = ConfigConstants.NULL_INT;
			String searchName = "";
			String searchCategory = "";
			String searchDateFrom = "";
			String searchDateTo = "";
			// parameter
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_ID))) {
				searchId = (Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_ID)));
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_NAME))) {
				searchName = request.getParameter(REQUEST_KEY_ITEM_NAME);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_CATEGORY))) {
				searchCategory = request.getParameter(REQUEST_KEY_ITEM_CATEGORY);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM))) {
				searchDateFrom = request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM);
			}
			if (!RequestParameterUtility.isEmptyParam(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO))) {
				searchDateTo = request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO);
			}
			ItemDao itemdao = new ItemDao();
			List<Item> itemList = itemdao.selectBySearch(searchId, searchName, searchCategory, searchDateFrom, searchDateTo);

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
