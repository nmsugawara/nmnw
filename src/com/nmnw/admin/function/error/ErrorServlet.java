	package com.nmnw.admin.function.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;

@WebServlet(name="admin/error/error", urlPatterns={"/admin/error/error"})
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct
	 */
	public ErrorServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_ERROR + "Error.jsp";
		HttpSession session = request.getSession();
		request.setAttribute("exceptionMessage", (String)session.getAttribute("exceptionMessage"));
		request.setAttribute("exceptionCause", (String)session.getAttribute("exceptionCause"));
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
} 