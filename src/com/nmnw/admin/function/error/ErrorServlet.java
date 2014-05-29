package com.nmnw.admin.function.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="admin/error/error", urlPatterns={"/admin/error/error"})
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIR_BASE = "/WEB-INF/admin/function/error/";

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
		String page = DIR_BASE + "Error.jsp";
		request.setAttribute("errorMessage", (String)request.getAttribute("errorMessage"));
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
} 