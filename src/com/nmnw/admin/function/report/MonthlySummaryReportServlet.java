package com.nmnw.admin.function.report;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.dao.MonthlySummaryReportDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.ExceptionUtility;

@WebServlet(name="admin/report/monthlysummaryreport", urlPatterns={"/admin/report/monthlysummaryreport"})
public class MonthlySummaryReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int GET_MONTHS = 6;

	/**
	 * Construct
	 */
	public MonthlySummaryReportServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_REPORT_SUMMARY + "MonthlySummaryReport.jsp";
		try {
			// 対象の月初/月末の日付リスト取得
			Calendar cal = Calendar.getInstance();
			List<Map<String, String>> dateList = new ArrayList<Map<String, String>>();
			dateList = DateConversionUtility.getLastBeginningAndEndOfMonthList(cal, GET_MONTHS);
			Map<String, Map<String, String>> resultList = new LinkedHashMap<String, Map<String, String>>();
			// 月毎にデータ集計
			for (int i = 0; i < GET_MONTHS; i++) {
				String from = dateList.get(i).get("from");
				String to = dateList.get(i).get("to");
				// 表示用日付（yyyy-mm）生成
				Calendar tmpCal = DateConversionUtility.stringToCalendar(from);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String yearMonth = sdf.format(tmpCal.getTime());

				MonthlySummaryReportDao dao = new MonthlySummaryReportDao();
				resultList.put(yearMonth, dao.selectSummaryDataByOrderPeriod(from, to));
			}
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
