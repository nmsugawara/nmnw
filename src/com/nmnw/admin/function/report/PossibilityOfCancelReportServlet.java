package com.nmnw.admin.function.report;

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
import com.nmnw.admin.dao.PossibilityOfCancelReportDao;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/report/possibilityofcancelreport", urlPatterns={"/admin/report/possibilityofcancelreport"})
public class PossibilityOfCancelReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int WITHIN_DAYS = 8;

	/**
	 * Construct
	 */
	public PossibilityOfCancelReportServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_REPORT_POC + "PossibilityOfCancelReport.jsp";
		try {
			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
			PossibilityOfCancelReportDao dao = new PossibilityOfCancelReportDao();
			resultList = dao.selectUnShippingOrShippingWithinRecentDays(WITHIN_DAYS);
			request.setAttribute("result", resultList);
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
