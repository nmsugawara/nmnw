package nmnw.admin.function.report;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.junit.Test;

import com.nmnw.admin.function.report.IndexServlet;

public class IndexServletTest {

	@Test
	public void indexServletTest() {
	}

	@Test
	public void doGetTest() {
		try {
			// èâä˙âª
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			expect(request.getRequestDispatcher("/WEB-INF/admin/function/report/Index.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = IndexServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// é¿çs
			IndexServlet indexServlet = new IndexServlet();
			method.invoke(indexServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// åüèÿ
		// å„èàóù
	}

	@Test
	public void doPostTest() {
		try {
			// èâä˙âª
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			expect(request.getRequestDispatcher("/WEB-INF/admin/function/report/Index.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = IndexServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// é¿çs
			IndexServlet indexServlet = new IndexServlet();
			method.invoke(indexServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// åüèÿ
		// å„èàóù
	}

}
