package com.nmnw.service.function.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URLEncoder;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.utility.ExceptionUtility;
import com.nmnw.service.dao.Cart;
import com.nmnw.service.dao.Item;
import com.nmnw.service.dao.ItemDao;
import com.nmnw.service.validator.Validator;
import static com.nmnw.service.utility.PropertyUtility.getPropertyValue;

@WebServlet(name="cart", urlPatterns={"/cart"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIELD_ITEM_ID = "���iID";
	private static final String FIELD_ITEM_COUNT = "���i��";
	private static final String REQUEST_KEY_ACTION = "action";
	private static final String ACTION_ADD = "add";
	private static final String ACTION_DELETE = "delete";
	private static final String ACTION_MODIFY = "modify";
	private static final String KEY_CART = "cart";
	private static final String REQUEST_KEY_REFURL = "ref_url";
	private static final String KEY_REFURL = "refUrl";
	private static final String REQUEST_KEY_MESSAGE = "message_code";
	private static final String KEY_MESSAGE = "messageCode";
	private static final String KEY_ITEM_STOCK = "stockList";
	private static final String KEY_ERROR_MESSAGE = "errorMessageList";
	private static final String KEY_LOGIN_ID = "id";
	private static final String KEY_ITEM_ID = "item_id";
	private static final String KEY_ITEM_COUNT = "item_count";

	/**
	 * Construct
	 */
	public CartServlet () {
		super();
	}

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = ConfigConstants.JSP_DIR_CART + "Cart.jsp";
		String url = "http://" + getPropertyValue("DOMAIN") + ConfigConstants.SERVLET_DIR_CART;
		String messageCode = "";
		if (request.getParameter(REQUEST_KEY_MESSAGE) != null) {
			messageCode = (String)request.getParameter(REQUEST_KEY_MESSAGE);
		}
		String refUrl = request.getParameter(REQUEST_KEY_REFURL);
		String encodeRefUrl = "";
		List<String> errorMessageList = new ArrayList<String>();
		try {
			////////////////
			// ���O�C���`�F�b�N
			////////////////
			HttpSession session = request.getSession();
			// ���O�C�����ĂȂ��ꍇ
			if (session.getAttribute(KEY_LOGIN_ID) == null) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_NOT_LOGIN);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_REFURL, refUrl);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			// action�p�����[�^���Ӑ}���Ȃ��l�̏ꍇ
			String[] vaildActionParam = {ACTION_ADD, ACTION_DELETE, ACTION_MODIFY};
			if (request.getParameter(REQUEST_KEY_ACTION) != null && !Arrays.asList(vaildActionParam).contains(request.getParameter(REQUEST_KEY_ACTION))) {
				// �G���[
				errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
				request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
				request.setAttribute(KEY_REFURL, refUrl);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			////////////////////////////
			// �J�[�g��ʕ\��
			////////////////////////////
			// action�p�����[�^���Ȃ��ꍇ
			if (request.getParameter(REQUEST_KEY_ACTION) == null) {
				// �Z�b�V��������Cart�I�u�W�F�N�g�L���m�F
				Boolean hasObject = false;
				Enumeration<String> en = session.getAttributeNames();
				while (en.hasMoreElements()) {
					String key = (String)en.nextElement();
					if (KEY_CART.equals(key)) {
						hasObject = true;
					}
				}
				Map<Integer, Integer> stockList = new LinkedHashMap<Integer, Integer>();
				// Cart�I�u�W�F�N�g���Ȃ����
				if (!hasObject) {
					// �������Ȃ�
				} else {
					// session����cart���擾
					List<Cart.CartItem> cartItemList = new ArrayList<Cart.CartItem>();
					Cart cart = (Cart)session.getAttribute(KEY_CART);
					cartItemList = cart.getItemList();
					// cart���̏��iID�A���i�݌ɂ��擾
					for(int i = 0; i < cartItemList.size(); i++) {
						Cart.CartItem cartItem = (Cart.CartItem)cartItemList.get(i);
						ItemDao itemDao = new ItemDao();
						Item item = itemDao.selectByItemId(cartItem.getItemId());
						stockList.put(item.getId(), item.getStock());
					}
				}
				// ��ʕ\��
				request.setAttribute(KEY_ITEM_STOCK, stockList);
				request.setAttribute(KEY_REFURL, refUrl);
				request.setAttribute(KEY_MESSAGE, messageCode);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
			////////////////////////////
			// ���i�ǉ�
			////////////////////////////
			if (ACTION_ADD.equals(request.getParameter(REQUEST_KEY_ACTION))) {
				// ���i�p�����[�^�`�F�b�N
				Validator v = new Validator();
				// ���iID
				if(!v.required(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID)) {
					v.isInt(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID);
				}
				// ��
				if(!v.required(request.getParameter(KEY_ITEM_COUNT), FIELD_ITEM_COUNT)) {
					v.isInt(request.getParameter(KEY_ITEM_COUNT), FIELD_ITEM_COUNT);
				}
				errorMessageList = v.getErrorMessageList();
				if (errorMessageList.size() != 0) {
					// �G���[
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// ���i����DB���擾
				ItemDao itemDao = new ItemDao();
				Item item = itemDao.selectByItemId(Integer.valueOf(request.getParameter(KEY_ITEM_ID)));
				if (item.getId() == ConfigConstants.NULL_INT) {
					// �G���[
					errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// �Z�b�V��������Cart�I�u�W�F�N�g�L���m�F
				Boolean hasObject = false;
				Enumeration<String> en = session.getAttributeNames();
				while (en.hasMoreElements()) {
					String key = (String)en.nextElement();
					if (KEY_CART.equals(key)) {
						hasObject = true;
					}
				}
				// Cart�I�u�W�F�N�g���Ȃ���ΐV�K����
				Cart cart = hasObject ? (Cart)session.getAttribute(KEY_CART) : new Cart();
				// �f�[�^�Z�b�g
				cart.addItem(item.getId(), item.getName(), item.getPrice(), Integer.valueOf(request.getParameter(KEY_ITEM_COUNT)));
				// �Z�b�V�����֕ۑ�
				session.setAttribute(KEY_CART, cart);
				if (refUrl != null) {
					encodeRefUrl = URLEncoder.encode(refUrl, "UTF-8");
				}
				response.sendRedirect(url + "?ref_url=" + encodeRefUrl + "&message_code=" + ACTION_ADD);
				return;
			}
			////////////////////////////
			// ���i�폜
			////////////////////////////
			if (ACTION_DELETE.equals(request.getParameter(REQUEST_KEY_ACTION))) {
				// ���i�p�����[�^�`�F�b�N
				Validator v = new Validator();
				// ���iID
				v.required(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID);
				v.isInt(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID);

				errorMessageList = v.getErrorMessageList();
				if (errorMessageList.size() != 0) {
					// �G���[
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// �Z�b�V��������Cart�I�u�W�F�N�g�L���m�F
				Boolean hasObject = false;
				Enumeration<String> en = session.getAttributeNames();
				while (en.hasMoreElements()) {
					String key = (String)en.nextElement();
					if (KEY_CART.equals(key)) {
						hasObject = true;
					}
				}
				// Cart�I�u�W�F�N�g���Ȃ����
				if (!hasObject) {
					// �G���[
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				Cart cart = (Cart)session.getAttribute(KEY_CART);
				// �f�[�^�폜
				cart.deleteItem(Integer.valueOf(request.getParameter(KEY_ITEM_ID)));
				// �Z�b�V�����֕ۑ�
				session.setAttribute(KEY_CART, cart);
				if (refUrl != null) {
					encodeRefUrl = URLEncoder.encode(refUrl, "UTF-8");
				}
				response.sendRedirect(url + "?ref_url=" + encodeRefUrl + "&message_code=" + ACTION_DELETE);
				return;
			}
			////////////////////////////
			// ���i���ύX
			////////////////////////////
			if (ACTION_MODIFY.equals(request.getParameter(REQUEST_KEY_ACTION))) {
				// ���i�p�����[�^�`�F�b�N
				Validator v = new Validator();
				// ���iID
				v.required(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID);
				v.isInt(request.getParameter(KEY_ITEM_ID), FIELD_ITEM_ID);
				// ��
				v.required(request.getParameter(KEY_ITEM_COUNT), FIELD_ITEM_COUNT);
				v.isInt(request.getParameter(KEY_ITEM_COUNT), FIELD_ITEM_COUNT);

				errorMessageList = v.getErrorMessageList();
				if (errorMessageList.size() != 0) {
					// �G���[
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// ���i����DB���擾
				ItemDao itemDao = new ItemDao();
				Item item = itemDao.selectByItemId(Integer.valueOf(request.getParameter(KEY_ITEM_ID)));
				if (item.getId() == ConfigConstants.NULL_INT) {
					// �G���[
					errorMessageList.add(MessageConstants.MESSAGE_ILLEGAL_PARAMETER);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				// �Z�b�V��������Cart�I�u�W�F�N�g�L���m�F
				Boolean hasObject = false;
				Enumeration<String> en = session.getAttributeNames();
				while (en.hasMoreElements()) {
					String key = (String)en.nextElement();
					if (KEY_CART.equals(key)) {
						hasObject = true;
					}
				}
				// Cart�I�u�W�F�N�g���Ȃ����
				if (!hasObject) {
					// �G���[
					errorMessageList.add(MessageConstants.MESSAGE_NO_DATA);
					request.setAttribute(KEY_ERROR_MESSAGE, errorMessageList);
					request.setAttribute(KEY_REFURL, refUrl);
					request.getRequestDispatcher(page).forward(request, response);
					return;
				}
				Cart cart = (Cart)session.getAttribute(KEY_CART);
				// �f�[�^�ύX
				cart.modifyItemCount(item.getId(), Integer.valueOf(request.getParameter(KEY_ITEM_COUNT)));
				// �Z�b�V�����֕ۑ�
				session.setAttribute(KEY_CART, cart);
				if (refUrl != null) {
					encodeRefUrl = URLEncoder.encode(refUrl, "UTF-8");
				}
				response.sendRedirect(url + "?ref_url=" + encodeRefUrl + "&message_code=" + ACTION_MODIFY);
				return;
			}
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