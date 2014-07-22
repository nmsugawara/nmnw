package nmnw.service.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;

import org.junit.Test;

import com.nmnw.service.dao.Mail;
import com.nmnw.service.utility.DateConversionUtility;

public class MailTest {

	@Test
	public void MailTest() {
	}

	@Test
	public void setCodeTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		mail.setCode(expected);
		// ����
		assertEquals("setCode:����n�G���[", field.get(mail), expected);
		// �㏈��
	}

	@Test
	public void getCodeTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		field.set(mail, expected);
		// ����
		assertEquals("getCode:����n�G���[", mail.getCode(), expected);
		// �㏈��
	}

	@Test
	public void getCodeConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		field.set(mail, value);
		// ����
		assertEquals("getCodeConvertedHtml:����n�G���[", mail.getCodeConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setSubjectTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		mail.setSubject(expected);
		// ����
		assertEquals("setSubject:����n�G���[", field.get(mail), expected);
		// �㏈��
	}

	@Test
	public void getSubjectTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		field.set(mail, expected);
		// ����
		assertEquals("getSubject:����n�G���[", mail.getSubject(), expected);
		// �㏈��
	}

	@Test
	public void getSubjectConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		field.set(mail, value);
		// ����
		assertEquals("getSubjectConvertedHtml:����n�G���[", mail.getSubjectConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setMessageTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		mail.setMessage(expected);
		// ����
		assertEquals("setMessage:����n�G���[", field.get(mail), expected);
		// �㏈��
	}

	@Test
	public void getMessageTest() throws Exception{
		// ������
		String expected = "test";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		field.set(mail, expected);
		// ����
		assertEquals("getMessage:����n�G���[", mail.getMessage(), expected);
		// �㏈��
	}

	@Test
	public void getMessageConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// ���s
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		field.set(mail, value);
		// ����
		assertEquals("getMessageConvertedHtml:����n�G���[", mail.getMessageConvertedHtml(), expected);
		// �㏈��
	}
}
