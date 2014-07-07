package com.nmnw.admin.function.item.edit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.constant.MessageConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.validator.ItemValidator;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.ExceptionUtility;
import com.nmnw.admin.utility.RequestParameterUtility;
import com.nmnw.admin.utility.FileUtility;

import static com.nmnw.admin.utility.PropertyUtility.getPropertyValue;

@WebServlet(name="admin/item/edit", urlPatterns={"/admin/item/edit"})
@MultipartConfig(location = ConfigConstants.TMP_IMAGE_DIR)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String REQUEST_VALUE_ACTION_EDIT = "edit";
	private static final String REQUEST_VALUE_ACTION_EDIT_END = "edit_end";
	private static final String REQUEST_KEY_OLD_ITEM_IMAGE = "old_item_image";
	private static final String REQUEST_KEY_ITEM_ID = "item_id";
	private static final String REQUEST_KEY_ITEM_NAME = "item_name";
	private static final String REQUEST_KEY_ITEM_IMAGE = "item_image";
	private static final String REQUEST_KEY_ITEM_PRICE = "item_price";
	private static final String REQUEST_KEY_ITEM_CATEGORY = "item_category";
	private static final String REQUEST_KEY_ITEM_EXPLANATION = "item_explanation";
	private static final String REQUEST_KEY_ITEM_PERIOD_FROM = "item_sales_period_from";
	private static final String REQUEST_KEY_ITEM_PERIOD_TO = "item_sales_period_to";
	private static final String REQUEST_KEY_ITEM_STOCK = "item_stock";
	private static final String FILE_BASE_NAME = "item";
	private static final String KEY_RESULT = "result";
	private static final String KEY_INPUT_DATA_LIST = "inputDataList";
	private static final String KEY_ERROR_MESSAGE_LIST = "errorMessageList";

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
		List<String> errorMessageList = new ArrayList<String>();
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ITEM_EDIT + "Edit.jsp";
		// 編集画面表示
		if (REQUEST_VALUE_ACTION_EDIT.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			try {
				// parameter
				ItemDao itemdao = new ItemDao();
				Item result = itemdao.selectByItemId(Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_ID)));
				// 該当データが存在しない場合
				if (result.getId() == 0) {
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
				} else {
					errorMessageList.add("");
				}
				request.setAttribute(KEY_RESULT, result);
				request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
				request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
				return;
			}
		}
		// 編集完了
		if (REQUEST_VALUE_ACTION_EDIT_END.equals(request.getParameter(REQUEST_KEY_ACTION))) {
			try {
				// validation
				ItemValidator iv = new ItemValidator();
				iv.checkName(request.getParameter(REQUEST_KEY_ITEM_NAME));
				iv.checkPrice(request.getParameter(REQUEST_KEY_ITEM_PRICE));
				iv.checkCategory(request.getParameter(REQUEST_KEY_ITEM_CATEGORY));
				Part image = request.getPart(REQUEST_KEY_ITEM_IMAGE);
				if (!RequestParameterUtility.isEmptyImage(image)) {
					iv.checkImage(image);
				}
				iv.checkExplanation(request.getParameter(REQUEST_KEY_ITEM_EXPLANATION));
				iv.checkSalesPeriodFrom(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM));
				iv.checkSalesPeriodTo(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO));
				iv.checkStock(request.getParameter(REQUEST_KEY_ITEM_STOCK));
	
				errorMessageList = iv.getValidationList();
				// 入力エラー時
				if (errorMessageList.size() != 0) {
					request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
					request.setAttribute(KEY_INPUT_DATA_LIST, inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// データオブジェクトに値セット
				Item item = new Item();
				item.setId(Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_ID)));
				item.setName(request.getParameter(REQUEST_KEY_ITEM_NAME));
				item.setPrice(Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_PRICE)));
				item.setCategory(request.getParameter(REQUEST_KEY_ITEM_CATEGORY));
				item.setExplanation(request.getParameter(REQUEST_KEY_ITEM_EXPLANATION));
				item.setSalesPeriodFrom(DateConversionUtility.stringToDate(request.getParameter(REQUEST_KEY_ITEM_PERIOD_FROM)));
				item.setSalesPeriodTo(DateConversionUtility.stringToDate(request.getParameter(REQUEST_KEY_ITEM_PERIOD_TO)));
				item.setStock(Integer.parseInt(request.getParameter(REQUEST_KEY_ITEM_STOCK)));
				// 新規画像がアップロードされていなかったら現在登録の画像URL
				if (RequestParameterUtility.isEmptyImage(image)) {
					item.setImageUrl(request.getParameter(REQUEST_KEY_OLD_ITEM_IMAGE));
				} else {
					String newImageFileName = FileUtility.getNewFileName(image, FILE_BASE_NAME);
					image.write(getPropertyValue("STORED_IMAGE_DIR_ITEM") + newImageFileName);
					item.setImageUrl(newImageFileName);
				}
				ItemDao itemdao = new ItemDao();
				// 更新
				String itemId = String.valueOf(itemdao.update(item));
				String url = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_ITEM_DETAIL + "?item_id=" + itemId + "&action=edit_end";
				response.sendRedirect(url);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		}
		// エラー
		errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
		request.setAttribute(KEY_ERROR_MESSAGE_LIST, errorMessageList);
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
