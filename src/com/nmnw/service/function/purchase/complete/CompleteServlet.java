package com.nmnw.service.function.purchase.complete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.dao.Cart;
import com.nmnw.service.dao.Mail;
import com.nmnw.service.dao.MailDao;
import com.nmnw.service.dao.Order;
import com.nmnw.service.dao.OrderDao;
import com.nmnw.service.dao.OrderDetail;
import com.nmnw.service.dao.OrderDetailDao;
import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.utility.MailUtility;

@WebServlet(name="purchase/complete", urlPatterns={"/purchase/complete"})
public class CompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_ACCOUNT_ID = "id";
	private static final String KEY_CART = "cart";
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String VALUE_ACTION = "complete";
	private static final String KEY_ORDER = "order";
	private static final String KEY_ORDER_DETAIL = "orderDetail";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String MAIL_CODE = "purchase_complete";

	/**
	 * Construct
	 */
	public CompleteServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> errorMessageList = new ArrayList<String>();
		String page = ConfigConstants.JSP_DIR_PURCHASE_COMPLETE + "Complete.jsp";
		try {
			///////////////////
			// ���O�C���`�F�b�N
			///////////////////
			HttpSession session = request.getSession();
			Integer loginId;
			// ���O�C�����ĂȂ��ꍇ
			if (session.getAttribute(KEY_ACCOUNT_ID) == null) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			///////////////////
			// Cart�I�u�W�F�N�g�`�F�b�N
			///////////////////
			if (session.getAttribute(KEY_CART) == null) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_NO_CART_DATA);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			///////////////////
			// action�p�����[�^�`�F�b�N
			///////////////////
			if (request.getParameter(REQUEST_KEY_ACTION) == null || !VALUE_ACTION.equals((String)request.getParameter(REQUEST_KEY_ACTION))) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			///////////////////
			// �������o�^
			///////////////////
			// ������擾
			loginId = (Integer)session.getAttribute(KEY_ACCOUNT_ID);
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.selectByAccountId(loginId);
			// �������i���
			Cart cart = (Cart)session.getAttribute(KEY_CART);
			List<Cart.CartItem> cartItemList = cart.getItemList();
			// ���v���z
			int totalPrice = 0;
			for (int i = 0; i < cartItemList.size(); i++) {
				totalPrice += cartItemList.get(i).getItemPrice();
			}
			// �������insert
			OrderDao orderDao = new OrderDao();
			int orderId = orderDao.insert(account, totalPrice);
			// �����ڍ�insert
			OrderDetailDao orderDetailDao = new OrderDetailDao();
			if (!orderDetailDao.insertItemList(orderId, cartItemList)) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_NO_CART_DATA);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			///////////////////
			// �����������[��
			///////////////////
			MailDao mailDao = new MailDao();
			Mail mail = mailDao.selectByCode(MAIL_CODE);
			String message = mail.getMessage().replace("{accountName}", account.getNameConvertedHtml());
			message = message.replace("{orderId}", String.valueOf(orderId));
			// ���[�����M
			boolean sendResult = MailUtility.sendMail(account.getMail(), mail.getSubject(), message);
			if (sendResult == false) {
				errorMessageList.add(MessageConstants.ERROR_SEND_MAIL);
			}

			// session���璍�����폜
			session.removeAttribute(KEY_CART);
			// �����擾
			Order order = orderDao.selectByOrderId(orderId);
			// �����ڍ׎擾
			List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderId);
			request.setAttribute(KEY_ORDER, order);
			request.setAttribute(KEY_ORDER_DETAIL, orderDetailList);
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
