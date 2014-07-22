package nmnw.admin.function.error;

import static org.easymock.EasyMock.*;
import static org.junit.Assume.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.function.error.ErrorServlet;

public class ErrorServletTest {
	public static final String URL = ConfigConstants.JSP_DIR_ERROR + "Error.jsp";
	public static final String EXCEPTION_MESSAGE = "exceptionMessage";
	public static final String EXCEPTION_CAUSE = "exceptionCause";
	public static final String VALUE_EXCEPTION_MESSAGE = null;
	public static final String VALUE_EXCEPTION_CAUSE = null;

	
	@Test
	public void doGetTest() {
		/**
		 * ê≥èÌånÅFåüçıâÊñ ï\é¶
		 */
		try {
			// èâä˙âª
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);
			HttpSession session = createMock(HttpSession.class);
			
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			expect(request.getSession()).andReturn(session);
			expect((String)session.getAttribute(EXCEPTION_MESSAGE)).andReturn(VALUE_EXCEPTION_MESSAGE);
			request.setAttribute(EXCEPTION_MESSAGE, VALUE_EXCEPTION_MESSAGE);
			expect((String)session.getAttribute(EXCEPTION_CAUSE)).andReturn(VALUE_EXCEPTION_CAUSE);
			request.setAttribute(EXCEPTION_CAUSE, VALUE_EXCEPTION_CAUSE);
			expect(request.getRequestDispatcher(URL)).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = ErrorServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// é¿çs
			ErrorServlet errorServlet = new ErrorServlet();
			method.invoke(errorServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// åüèÿ
		// å„èàóù
	}
}
