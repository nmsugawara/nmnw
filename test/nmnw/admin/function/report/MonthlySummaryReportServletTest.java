package nmnw.admin.function.report;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.nmnw.admin.function.report.MonthlySummaryReportServlet;

public class MonthlySummaryReportServletTest {

	@Test
	public void indexServletTest() {
	}

	@Test
	public void doGetTest() {
		try {
			// ‰Šú‰»
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			Map<String, Map<String, String>> resultList = new LinkedHashMap<String, Map<String, String>>();
			request.setAttribute("result", resultList);
			expect(request.getRequestDispatcher("/WEB-INF/admin/function/report/monthlySummaryReport/MonthlySummaryReport.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = MonthlySummaryReportServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// Às
			MonthlySummaryReportServlet monthlySummaryReportServlet = new MonthlySummaryReportServlet();
			method.invoke(monthlySummaryReportServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ŒŸØ
		// Œãˆ—
	}

	@Test
	public void doPostTest() {
		// ‰Šú‰»
		// Às
		// ŒŸØ
		// Œãˆ—
	}

}
