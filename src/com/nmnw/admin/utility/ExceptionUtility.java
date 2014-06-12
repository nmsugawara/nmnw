package com.nmnw.admin.utility;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.admin.constant.ConfigConstants;

public class ExceptionUtility {

	public ExceptionUtility () {
	}

	/**
	 * Exception�������A�G���[�y�[�W�փ��_�C���N�g
	 * @param request
	 * @param response
	 * @param e
	 * @throws IOException
	 */
	public static void redirectErrorPage (HttpServletRequest request, HttpServletResponse response, Exception e)
			throws IOException {
		String exceptionMessage = "";
		String exceptionCause = "";
		// ���b�Z�[�W�擾
		exceptionMessage = getExceptionMessage(e);
		// �����擾
		exceptionCause = getExceptionCause(e);

		HttpSession session = request.getSession();
		session.setAttribute("exceptionMessage", exceptionMessage);
		session.setAttribute("exceptionCause", exceptionCause);
		String url = "http://" + ConfigConstants.DOMAIN + ConfigConstants.SERVLET_DIR_ERROR;
		response.sendRedirect(url);
	}

	/**
	 * Exception ���b�Z�[�W���擾
	 * @param e
	 * @return
	 */
	public static String getExceptionMessage (Exception e) {
		return e.toString();
	}

	/**
	 * Exception �������擾
	 * @param e
	 * @return
	 */
	public static String getExceptionCause (Exception e) {
		StringBuilder exceptionCause = new StringBuilder();
		if (e.getCause() != null) {
			for (StackTraceElement ste : e.getCause().getStackTrace()) {
				exceptionCause.append(ste + "\n");
			}
		} else {
			for (StackTraceElement ste : e.getStackTrace()) {
				exceptionCause.append(ste + "\n");
			}
		}
		return exceptionCause.toString();
	}
}
