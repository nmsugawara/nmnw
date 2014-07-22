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
		// ������
		int expected = 1;
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		account.setId(expected);
		// ����
		assertEquals("setId:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getIdTest() throws Exception{
		// ������
		int expected = 1;
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getId:����n�G���[", account.getId(), expected);
		// �㏈��
	}

	@Test
	public void setNameTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		account.setName(expected);
		// ����
		assertEquals("setName:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getNameTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getName:����n�G���[", account.getName(), expected);
		// �㏈��
	}

	@Test
	public void getNameConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getNameConvertedHtml:����n�G���[", account.getNameConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setNameKanaTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		account.setNameKana(expected);
		// ����
		assertEquals("setNameKana:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getNameKanaTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getNameKana:����n�G���[", account.getNameKana(), expected);
		// �㏈��
	}

	@Test
	public void getNameKanaConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_nameKana");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getNameKanaConvertedHtml:����n�G���[", account.getNameKanaConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setMailTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		account.setMail(expected);
		// ����
		assertEquals("setMail:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getMailTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getMail:����n�G���[", account.getMail(), expected);
		// �㏈��
	}

	@Test
	public void getMailConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_mail");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getMailConvertedHtml:����n�G���[", account.getMailConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setPassWordTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		account.setPassWord(expected);
		// ����
		assertEquals("setPassWord:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getPassWordTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getPassWord:����n�G���[", account.getPassWord(), expected);
		// �㏈��
	}

	@Test
	public void getPassWordConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_passWord");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getPassWordConvertedHtml:����n�G���[", account.getPassWordConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setZipCodeTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		account.setZipCode(expected);
		// ����
		assertEquals("setZipCode:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getZipCodeTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getZipCode:����n�G���[", account.getZipCode(), expected);
		// �㏈��
	}

	@Test
	public void getZipCodeConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_zipCode");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getZipCodeConvertedHtml:����n�G���[", account.getZipCodeConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setAddressTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		account.setAddress(expected);
		// ����
		assertEquals("setAddress:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getAddressTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getAddress:����n�G���[", account.getAddress(), expected);
		// �㏈��
	}

	@Test
	public void getAddressConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_address");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getAddressConvertedHtml:����n�G���[", account.getAddressConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setPhoneNumberTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		account.setPhoneNumber(expected);
		// ����
		assertEquals("setPhoneNumber:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getPhoneNumberTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getPhoneNumber:����n�G���[", account.getPhoneNumber(), expected);
		// �㏈��
	}

	@Test
	public void getPhoneNumberConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_phoneNumber");
		field.setAccessible(true);
		field.set(account, value);
		// ����
		assertEquals("getPhoneNumberConvertedHtml:����n�G���[", account.getPhoneNumberConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setDelFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Account accountTrue = new Account();
		// ���s
		Field fieldTrue = accountTrue.getClass().getDeclaredField("_delFlg");
		fieldTrue.setAccessible(true);
		accountTrue.setDelFlg(expectedTrue);
		// ����
		assertTrue("setDelFlg:True", (boolean)fieldTrue.get(accountTrue));
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Account accountFalse = new Account();
		// ���s
		Field fieldFalse = accountFalse.getClass().getDeclaredField("_delFlg");
		fieldFalse.setAccessible(true);
		accountFalse.setDelFlg(expectedFalse);
		// ����
		assertFalse("setDelFlg:False", (boolean)fieldFalse.get(accountFalse));
		// �㏈��
	}

	@Test
	public void getDelFlgTest() throws Exception{
		/**
		 * true
		 */
		// ������
		boolean expectedTrue = true;
		Account accountTrue = new Account();
		// ���s
		Field fieldTrue = accountTrue.getClass().getDeclaredField("_delFlg");
		fieldTrue.setAccessible(true);
		fieldTrue.set(accountTrue, expectedTrue);
		// ����
		assertTrue("getDelFlg:True", accountTrue.getDelFlg());
		// �㏈��

		/**
		 * false
		 */
		// ������
		boolean expectedFalse = false;
		Account accountFalse = new Account();
		// ���s
		Field fieldFalse = accountFalse.getClass().getDeclaredField("_delFlg");
		fieldFalse.setAccessible(true);
		fieldTrue.set(accountTrue, expectedFalse);
		// ����
		assertFalse("setDelFlg:False", accountTrue.getDelFlg());
		// �㏈��
	}

	@Test
	public void setTokenTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_token");
		field.setAccessible(true);
		account.setToken(expected);
		// ����
		assertEquals("setToken:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getTokenTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_token");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getToken:����n�G���[", account.getToken(), expected);
		// �㏈��
	}

	@Test
	public void setSaltTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_salt");
		field.setAccessible(true);
		account.setSalt(expected);
		// ����
		assertEquals("setSalt:����n�G���[", field.get(account), expected);
		// �㏈��
	}

	@Test
	public void getSaltTest() throws Exception{
		// ������
		String expected = "test";
		Account account = new Account();
		// ���s
		Field field = account.getClass().getDeclaredField("_salt");
		field.setAccessible(true);
		field.set(account, expected);
		// ����
		assertEquals("getSalt:����n�G���[", account.getSalt(), expected);
		// �㏈��
	}
}
