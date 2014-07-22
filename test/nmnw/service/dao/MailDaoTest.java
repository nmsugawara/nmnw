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
		 * ���݂���R�[�h
		 */
		// ������
		String code = CODE;
		MailDao mailDao = new MailDao();
		Mail expected = new Mail();
		expected.setCode(code);
		expected.setSubject(SUBJECT);
		expected.setMessage(MESSAGE);
		// ���s
		Mail actual = mailDao.selectByCode(code);
		// ����
		assertThat("selectByCode:code", actual.getCode(), is(expected.getCode()));
		assertThat("selectByCode:subject", actual.getSubject(), is(expected.getSubject()));
		assertThat("selectByCode:message", actual.getMessage(), is(expected.getMessage()));
		// �㏈��
		
		/**
		 * ���݂��Ȃ�code
		 */
		// ������
		String codeNotExist = NOT_EXIST_CODE;
		// ���s
		Mail actualNotExist = mailDao.selectByCode(codeNotExist);
		// ����
		assertThat("selectByCode:code", actualNotExist.getCode(), is(""));
		assertThat("selectByCode:subject", actualNotExist.getSubject(), is(""));
		assertThat("selectByCode:message", actualNotExist.getMessage(), is(""));
		// �㏈��
	}
}
