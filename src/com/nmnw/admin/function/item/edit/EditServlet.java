package com.nmnw.admin.function.item.edit;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nmnw.admin.Enum.ItemCategoryEnum;
import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.validator.ItemValidator;

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
		// get item category list
		List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
		request.setAttribute("itemCategoryList", itemCategoryList);
		// get "action" parameter
		String page = ConfigConstants.JSP_DIR_ITEM_EDIT + "Edit.jsp";
		// edit
		if ("edit".equals(action)) {
			try {
				// parameter
				Item item = new Item();
				item.setId(Integer.parseInt(request.getParameter("item_id")));
				ItemDao itemdao = new ItemDao();
				Item result = itemdao.selectByItemId(item.getId());
				request.setAttribute("result", result);
				errorMessageList.add("");
				request.setAttribute("errorMessageList", errorMessageList);
				request.setAttribute("inputDataList", inputDataList);
				request.getRequestDispatcher(page).forward(request, response);
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
		} else {
			try {
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
					// set to data object
					Item item = new Item();
					item.setId(Integer.parseInt(request.getParameter("item_id")));
					item.setName(request.getParameter("item_name"));
					item.setPrice(Integer.parseInt(request.getParameter("item_price")));
					item.setCategory(request.getParameter("item_category"));
					item.setExplanation(request.getParameter("item_explanation"));
					item.setSalesPeriodFrom(request.getParameter("item_sales_period_from"));
					item.setSalesPeriodTo(request.getParameter("item_sales_period_to"));
					item.setStock(Integer.parseInt(request.getParameter("item_stock")));
					String newImageFileName = getNewImageFileName(image);
					image.write(ConfigConstants.STORED_IMAGE_DIR_ITEM + newImageFileName);
					item.setImageUrl(newImageFileName);
	
					ItemDao itemdao = new ItemDao();
					String itemId = String.valueOf(itemdao.update(item));
					String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ITEM_DETAIL + "?item_id=" + itemId + "&action=edit_end";
					response.sendRedirect(url);
				}
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

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

	protected String getNewImageFileName (Part image) {
		String newFileName = "";
		String contentDispotision = image.getHeader("Content-Disposition");
		String[] contentDispotisions = contentDispotision.split(";");
		for (String cd : contentDispotisions) {
			if (cd.trim().startsWith("filename")) {
				// generate file name
				String filePath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				int lastSeparatorIndex = filePath.lastIndexOf(File.separator);
				String oldFileName = filePath.substring(lastSeparatorIndex + 1);
				String[] oldFileString = oldFileName.split("\\.");
				String oldFileExtension = oldFileString[oldFileString.length - 1];
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
				newFileName = "image" + sdf.format(cal.getTime()) + "." + oldFileExtension;
				return newFileName;
			}
		}
		throw new IllegalStateException();
	}
}
