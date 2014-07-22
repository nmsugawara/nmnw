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
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		mail.setCode(expected);
		// 検証
		assertEquals("setCode:正常系エラー", field.get(mail), expected);
		// 後処理
	}

	@Test
	public void getCodeTest() throws Exception{
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		field.set(mail, expected);
		// 検証
		assertEquals("getCode:正常系エラー", mail.getCode(), expected);
		// 後処理
	}

	@Test
	public void getCodeConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_code");
		field.setAccessible(true);
		field.set(mail, value);
		// 検証
		assertEquals("getCodeConvertedHtml:正常系エラー", mail.getCodeConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setSubjectTest() throws Exception{
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		mail.setSubject(expected);
		// 検証
		assertEquals("setSubject:正常系エラー", field.get(mail), expected);
		// 後処理
	}

	@Test
	public void getSubjectTest() throws Exception{
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		field.set(mail, expected);
		// 検証
		assertEquals("getSubject:正常系エラー", mail.getSubject(), expected);
		// 後処理
	}

	@Test
	public void getSubjectConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_subject");
		field.setAccessible(true);
		field.set(mail, value);
		// 検証
		assertEquals("getSubjectConvertedHtml:正常系エラー", mail.getSubjectConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setMessageTest() throws Exception{
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		mail.setMessage(expected);
		// 検証
		assertEquals("setMessage:正常系エラー", field.get(mail), expected);
		// 後処理
	}

	@Test
	public void getMessageTest() throws Exception{
		// 初期化
		String expected = "test";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		field.set(mail, expected);
		// 検証
		assertEquals("getMessage:正常系エラー", mail.getMessage(), expected);
		// 後処理
	}

	@Test
	public void getMessageConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Mail mail = new Mail();
		// 実行
		Field field = mail.getClass().getDeclaredField("_message");
		field.setAccessible(true);
		field.set(mail, value);
		// 検証
		assertEquals("getMessageConvertedHtml:正常系エラー", mail.getMessageConvertedHtml(), expected);
		// 後処理
	}
}
