package nmnw.service.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.service.dao.Mail;
import com.nmnw.service.dao.MailDao;

@RunWith(Theories.class)
public class MailDaoTest {
	public static final String CODE = "test";
	public static final String SUBJECT = "test";
	public static final String MESSAGE = "test";
	public static final String NOT_EXIST_CODE = "testtest";
	
	@Test
	public void selectByCodeTest() throws Exception{
		/**
		 * 存在するコード
		 */
		// 初期化
		String code = CODE;
		MailDao mailDao = new MailDao();
		Mail expected = new Mail();
		expected.setCode(code);
		expected.setSubject(SUBJECT);
		expected.setMessage(MESSAGE);
		// 実行
		Mail actual = mailDao.selectByCode(code);
		// 検証
		assertThat("selectByCode:code", actual.getCode(), is(expected.getCode()));
		assertThat("selectByCode:subject", actual.getSubject(), is(expected.getSubject()));
		assertThat("selectByCode:message", actual.getMessage(), is(expected.getMessage()));
		// 後処理
		
		/**
		 * 存在しないcode
		 */
		// 初期化
		String codeNotExist = NOT_EXIST_CODE;
		// 実行
		Mail actualNotExist = mailDao.selectByCode(codeNotExist);
		// 検証
		assertThat("selectByCode:code", actualNotExist.getCode(), is(""));
		assertThat("selectByCode:subject", actualNotExist.getSubject(), is(""));
		assertThat("selectByCode:message", actualNotExist.getMessage(), is(""));
		// 後処理
	}
}
