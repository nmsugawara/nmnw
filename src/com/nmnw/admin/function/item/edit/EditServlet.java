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

@WebServlet(name="admin/item/edit", urlPatterns={"/admin/item/edit"})
@MultipartConfig(location = ConfigConstants.TMP_IMAGE_DIR)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String action = request.getParameter("action");
		Map<String, String[]> inputDataList = request.getParameterMap();
		String page = ConfigConstants.JSP_DIR_ITEM_EDIT + "Edit.jsp";
		// 編集画面表示
		if ("edit".equals(action)) {
			try {
				// parameter
				ItemDao itemdao = new ItemDao();
				Item result = itemdao.selectByItemId(Integer.parseInt(request.getParameter("item_id")));
				// 該当データが存在しない場合
				if (result.getId() == 0) {
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
				} else {
					errorMessageList.add("");
				}
				request.setAttribute("result", result);
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		} else {
		// 編集完了
			try {
				// validation
				ItemValidator iv = new ItemValidator();
				iv.checkName(request.getParameter("item_name"));
				iv.checkPrice(request.getParameter("item_price"));
				iv.checkCategory(request.getParameter("item_category"));
				Part image = request.getPart("item_image");
				if (!RequestParameterUtility.isEmptyImage(image)) {
					iv.checkImage(image);
				}
				iv.checkExplanation(request.getParameter("item_explanation"));
				iv.checkSalesPeriodFrom(request.getParameter("item_sales_period_from"));
				iv.checkSalesPeriodTo(request.getParameter("item_sales_period_to"));
				iv.checkStock(request.getParameter("item_stock"));

				errorMessageList = iv.getValidationList();
				// 入力エラー時
				if (errorMessageList.size() != 0) {
					request.setAttribute("errorMessageList", errorMessageList);
					request.setAttribute("inputDataList", inputDataList);
					request.getRequestDispatcher(page).forward(request, response);
				} else {
					// データオブジェクトに値セット
					Item item = new Item();
					item.setId(Integer.parseInt(request.getParameter("item_id")));
					item.setName(request.getParameter("item_name"));
					item.setPrice(Integer.parseInt(request.getParameter("item_price")));
					item.setCategory(request.getParameter("item_category"));
					item.setExplanation(request.getParameter("item_explanation"));
					item.setSalesPeriodFrom(DateConversionUtility.stringToDate(request.getParameter("item_sales_period_from")));
					item.setSalesPeriodTo(DateConversionUtility.stringToDate(request.getParameter("item_sales_period_to")));
					item.setStock(Integer.parseInt(request.getParameter("item_stock")));
					// 新規画像がアップロードされていなかったら現在登録の画像URL
					if (RequestParameterUtility.isEmptyImage(image)) {
						item.setImageUrl(request.getParameter("old_item_image"));
					} else {
						String newImageFileName = FileUtility.getNewFileName(image, "item");
						image.write(ConfigConstants.STORED_IMAGE_DIR_ITEM + newImageFileName);
						item.setImageUrl(newImageFileName);
					}
					ItemDao itemdao = new ItemDao();
					// 更新
					String itemId = String.valueOf(itemdao.update(item));
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ITEM_DETAIL + "?item_id=" + itemId + "&action=edit_end";
					response.sendRedirect(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtility.redirectErrorPage(request, response, e);
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
