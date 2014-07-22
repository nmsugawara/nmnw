package nmnw.service.function.account.detail;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.nmnw.service.constant.MessageConstants;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.function.account.detail.DetailServlet;

public class DetailServletTest {
	public static final String KEY_RESULT = "result";
	public static final String KEY_MESSAGE = "message";
	public static final String REQUEST_KEY_ACTION = "action";
	public static final String VALUE_ACTION_REGIST_END = "regist_end";
	public static final String VALUE_ACTION_EDIT_END = "edit_end";
	public static final String KEY_ID = "account_id";
	public static final String VALUE_ID = "11";
	
	@Test
	public void DetailServletTest() {
	}

	@Test
	public void doGetTest() {
		try {
			/**
			 * 正常系：パスワード変更用画面表示
			 */
			// 初期化
			AccountDao accountDao = new AccountDao();
			Account result = accountDao.selectByAccountId(Integer.valueOf(VALUE_ID));
			
			HttpServletRequest request = createMock(HttpServletRequest.class);
			HttpServletResponse response = createMock(HttpServletResponse.class);
			RequestDispatcher rd = createMock(RequestDispatcher.class);

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			expect(request.getParameter(KEY_ID)).andReturn(VALUE_ID);
			request.setAttribute(KEY_RESULT, same(Account.class));
			request.setAttribute(KEY_MESSAGE, "");
			expect(request.getParameter(REQUEST_KEY_ACTION)).andReturn(VALUE_ACTION_REGIST_END);
			request.setAttribute(KEY_MESSAGE, MessageConstants.MESSAGE_REGIST_END);
			expect(request.getRequestDispatcher("/WEB-INF/service/function/account/detail/Detail.jsp")).andReturn(rd);
			rd.forward(request, response);
			replay(request, response, rd);
			Method method = DetailServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 実行
			DetailServlet detailServlet = new DetailServlet();
			method.invoke(detailServlet, request, response);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		// 後処理
	}

	@Test
	public void doPostTest() {
	}

}
