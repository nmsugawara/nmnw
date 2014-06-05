package com.nmnw.admin.function.item.New;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.utility.FileUtility;
import com.nmnw.admin.validator.ItemValidator;
import com.nmnw.admin.Enum.ItemCategoryEnum;
import com.nmnw.admin.constant.ConfigConstants;

@WebServlet(name="admin/item/new", urlPatterns={"/admin/item/new"})
@MultipartConfig(location = ConfigConstants.TMP_IMAGE_DIR)
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Map<String, String[]> inputDataList = request.getParameterMap();
		// get item category list
		List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
		request.setAttribute("itemCategoryList", itemCategoryList);
		// get "action" parameter
		String action = request.getParameter("action");
		String page = ConfigConstants.JSP_DIR_ITEM_NEW + "New.jsp";
		// new
		if (!("new_end".equals(action))) {
			errorMessageList.add("");
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("inputDataList", inputDataList);
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
			Part image = request.getPart("item_image");
			iv.checkImage(image);

			errorMessageList = iv.getValidationList();
			// has error: go back new page
			if (errorMessageList.size() != 0) {
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				try {
					// set to data object
					Item item = new Item();
					item.setName(request.getParameter("item_name"));
					item.setPrice(Integer.parseInt(request.getParameter("item_price")));
					item.setCategory(request.getParameter("item_category"));
					item.setExplanation(request.getParameter("item_explanation"));
					item.setSalesPeriodFrom(request.getParameter("item_sales_period_from"));
					item.setSalesPeriodTo(request.getParameter("item_sales_period_to"));
					item.setStock(Integer.parseInt(request.getParameter("item_stock")));
					String newImageFileName = FileUtility.getNewFileName(image, "item");
					image.write(ConfigConstants.STORED_IMAGE_DIR_ITEM + newImageFileName);
					item.setImageUrl(newImageFileName);

					ItemDao itemdao = new ItemDao();
					String itemId = String.valueOf(itemdao.insert(item));
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ITEM_DETAIL + "?item_id=" + itemId + "&action=new_end";
					response.sendRedirect(url);
				} catch (Exception e) {
					e.printStackTrace();
					String exceptionMessage = e.getStackTrace().toString();
					String exceptionCause = e.getCause().toString();
					HttpSession session = request.getSession();
					session.setAttribute("exceptionMessage", exceptionMessage);
					session.setAttribute("exceptionCause", exceptionCause);
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ERROR;
					response.sendRedirect(url);
				}
			}
		}
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}