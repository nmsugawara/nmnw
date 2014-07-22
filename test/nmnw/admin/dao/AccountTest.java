package nmnw.admin.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import com.nmnw.admin.dao.Account;

public class AccountTest {

	@Test
	public void accountTest() {
	}

	@Test
	public void setIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		account.setId(expected);
		// 検証
		assertEquals("setId:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getId:正常系エラー", account.getId(), expected);
		// 後処理
	}

	@Test
	public void setNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		account.setName(expected);
		// 検証
		assertEquals("setName:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getName:正常系エラー", account.getName(), expected);
		// 後処理
	}

	@Test
	public void getNameConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getNameConvertedHtml:正常系エラー", account.getNameConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setNameKanaTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		account.setNameKana(expected);
		// 検証
		assertEquals("setNameKana:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getNameKanaTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getNameKana:正常系エラー", account.getNameKana(), expected);
		// 後処理
	}

	@Test
	public void getNameKanaConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getNameKanaConvertedHtml:正常系エラー", account.getNameKanaConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setMailTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		account.setMail(expected);
		// 検証
		assertEquals("setMail:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getMailTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getMail:正常系エラー", account.getMail(), expected);
		// 後処理
	}

	@Test
	public void getMailConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getMailConvertedHtml:正常系エラー", account.getMailConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setPassWordTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		account.setPassWord(expected);
		// 検証
		assertEquals("setPassWord:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getPassWordTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getPassWord:正常系エラー", account.getPassWord(), expected);
		// 後処理
	}

	@Test
	public void getPassWordConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getPassWordConvertedHtml:正常系エラー", account.getPassWordConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setZipCodeTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		account.setZipCode(expected);
		// 検証
		assertEquals("setZipCode:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getZipCodeTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getZipCode:正常系エラー", account.getZipCode(), expected);
		// 後処理
	}

	@Test
	public void getZipCodeConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getZipCodeConvertedHtml:正常系エラー", account.getZipCodeConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setAddressTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		account.setAddress(expected);
		// 検証
		assertEquals("setAddress:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getAddressTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getAddress:正常系エラー", account.getAddress(), expected);
		// 後処理
	}

	@Test
	public void getAddressConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getAddressConvertedHtml:正常系エラー", account.getAddressConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setPhoneNumberTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		account.setPhoneNumber(expected);
		// 検証
		assertEquals("setPhoneNumber:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getPhoneNumberTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getPhoneNumber:正常系エラー", account.getPhoneNumber(), expected);
		// 後処理
	}

	@Test
	public void getPhoneNumberConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		field.set(account, value);
		// 検証
		assertEquals("getPhoneNumberConvertedHtml:正常系エラー", account.getPhoneNumberConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setDelFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Account accountTrue = new Account();
		// 実行
		Field fieldTrue = accountTrue.getClass().getDeclaredField("_delFlg");
		fieldTrue.setAccessible(true);
		accountTrue.setDelFlg(expectedTrue);
		// 検証
		assertTrue("setDelFlg:True", (boolean)fieldTrue.get(accountTrue));
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Account accountFalse = new Account();
		// 実行
		Field fieldFalse = accountFalse.getClass().getDeclaredField("_delFlg");
		fieldFalse.setAccessible(true);
		accountFalse.setDelFlg(expectedFalse);
		// 検証
		assertFalse("setDelFlg:False", (boolean)fieldFalse.get(accountFalse));
		// 後処理
	}

	@Test
	public void getDelFlgTest() throws Exception{
		/**
		 * true
		 */
		// 初期化
		boolean expectedTrue = true;
		Account accountTrue = new Account();
		// 実行
		Field fieldTrue = accountTrue.getClass().getDeclaredField("_delFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(accountTrue, expectedTrue);
		// 検証
		assertTrue("getDelFlg:True", accountTrue.getDelFlg());
		// 後処理

		/**
		 * false
		 */
		// 初期化
		boolean expectedFalse = false;
		Account accountFalse = new Account();
		// 実行
		Field fieldFalse = accountFalse.getClass().getDeclaredField("_delFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(accountTrue, expectedFalse);
		// 検証
		assertFalse("setDelFlg:False", accountTrue.getDelFlg());
		// 後処理
	}

	@Test
	public void setTokenTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_token");
		field.setAccessible(true);
		account.setToken(expected);
		// 検証
		assertEquals("setToken:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getTokenTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_token");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getToken:正常系エラー", account.getToken(), expected);
		// 後処理
	}

	@Test
	public void setSaltTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_salt");
		field.setAccessible(true);
		account.setSalt(expected);
		// 検証
		assertEquals("setSalt:正常系エラー", field.get(account), expected);
		// 後処理
	}

	@Test
	public void getSaltTest() throws Exception{
		// 初期化
		String expected = "test";
		Account account = new Account();
		// 実行
		Field field = account.getClass().getDeclaredField("_salt");
		field.setAccessible(true);
		field.set(account, expected);
		// 検証
		assertEquals("getSalt:正常系エラー", account.getSalt(), expected);
		// 後処理
	}
}
