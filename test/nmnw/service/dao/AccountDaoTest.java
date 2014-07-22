package nmnw.service.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.dao.Account;
import com.nmnw.service.dao.AccountDao;
import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.DbConnector;

@RunWith(Theories.class)
public class AccountDaoTest {
	public static final int ID = 11;
	public static final String NAME = "�e�X�g���Y";
	public static final String NAME_KANA = "�e�X�g�^���E";
	public static final String MAIL = "tracktest004@net-marketing.co.jp";
	public static final String PASS_WORD = "8pD8o5B1IhdQhYcy_H761fWOJQTcZhy8_xm7BLYOpGw";
	public static final String ZIP_CODE = "111-1111";
	public static final String ADDRESS = "�e�X�g";
	public static final String PHONE_NUMBER = "11-1111-1111";
	public static final Boolean DEL_FLG = false;
	public static final String TOKEN = "d359a56ee1020b3690d590bf123e45a6";
	public static final Date TOKEN_EXPIRE_TIME = DateConversionUtility.stringToDateTime("2014-07-11 12:38:05");
	public static final String SALT = "49c5552a6a5efb07";
	
	public static final int NOT_EXIST_ID = 9999;
	public static final String NOT_EXIST_MAIL = "test";
	public static final String NOT_EXIST_TOKEN = "test";
	public static final String WITHIN_EXPIRE = "2014-07-01";
	public static final String OVER_EXPIRE = "2015-07-01";

	public static final String NEW_NAME = "insert�e�X�g";
	public static final String NEW_NAME_KANA = "�e�X�g";
	public static final String NEW_MAIL = "insert_test@net-marketing.co.jp";
	public static final String NEW_PASS_WORD = "InsertTest";
	public static final String NEW_ZIP_CODE = "111-1111";
	public static final String NEW_ADDRESS = "�e�X�g";
	public static final String NEW_PHONE_NUMBER = "11-1111-1111";
	public static final Boolean NEW_DEL_FLG = false;
	public static final String NEW_TOKEN = "inserttest";
	public static final Date NEW_TOKEN_EXPIRE_TIME = DateConversionUtility.stringToDateTime("2014-07-11 11:11:11");
	public static final String NEW_SALT = "inserttest";

	public static final int UPDATE_ID = 12;

	@Test
	public void selectByAccountIdTest() throws Exception{
		/**
		 * ���݂�����ID
		 */
		// ������
		int accountIdExist = ID;
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setId(accountIdExist);
		expected.setName(NAME);
		expected.setNameKana(NAME_KANA);
		expected.setMail(MAIL);
		expected.setPassWord(PASS_WORD);
		expected.setZipCode(ZIP_CODE);
		expected.setAddress(ADDRESS);
		expected.setPhoneNumber(PHONE_NUMBER);
		expected.setDelFlg(DEL_FLG);
		expected.setToken(TOKEN);
		expected.setTokenExpireTime(TOKEN_EXPIRE_TIME);
		expected.setSalt(SALT);
		// ���s
		Account actual = accountDao.selectByAccountId(accountIdExist);
		// ����
		assertThat("selectByAccountId:Id", actual.getId(), is(expected.getId()));
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:PassWord", actual.getPassWord(), is(expected.getPassWord()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		assertEquals("selectByAccountId:TokenExpireTime", actual.getTokenExpireTime(), new java.sql.Timestamp(expected.getTokenExpireTime().getTime()));
		assertThat("selectByAccountId:Salt", actual.getSalt(), is(expected.getSalt()));
		// �㏈��
		
		/**
		 * ���݂��Ȃ����ID
		 */
		// ������
		int accountIdNotExist = NOT_EXIST_ID;
		// ���s
		Account actualNotExist = accountDao.selectByAccountId(accountIdNotExist);
		// ����
		assertThat("selectByAccountId:Id", actualNotExist.getId(), is(ConfigConstants.NULL_INT));
		assertThat("selectByAccountId:Name", actualNotExist.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualNotExist.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualNotExist.getMail(), is(""));
		assertThat("selectByAccountId:PassWord", actualNotExist.getPassWord(), is(""));
		assertThat("selectByAccountId:ZipCode", actualNotExist.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualNotExist.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualNotExist.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualNotExist.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualNotExist.getToken(), is(""));
		assertNull("selectByAccountId:TokenExpireTime", actualNotExist.getTokenExpireTime());
		assertThat("selectByAccountId:Salt", actualNotExist.getSalt(), is(""));
		// �㏈��
	}

	@Test
	public void selectByMailTest() throws Exception{
		/**
		 * ���݂�����ID
		 */
		// ������
		String accountMailExist = MAIL;
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setId(ID);
		expected.setName(NAME);
		expected.setNameKana(NAME_KANA);
		expected.setMail(accountMailExist);
		expected.setPassWord(PASS_WORD);
		expected.setZipCode(ZIP_CODE);
		expected.setAddress(ADDRESS);
		expected.setPhoneNumber(PHONE_NUMBER);
		expected.setDelFlg(DEL_FLG);
		expected.setToken(TOKEN);
		expected.setTokenExpireTime(TOKEN_EXPIRE_TIME);
		expected.setSalt(SALT);
		// ���s
		Account actual = accountDao.selectByMail(accountMailExist);
		// ����
		assertThat("selectByAccountId:Id", actual.getId(), is(expected.getId()));
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:PassWord", actual.getPassWord(), is(expected.getPassWord()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		assertEquals("selectByAccountId:TokenExpireTime", actual.getTokenExpireTime(), new java.sql.Timestamp(expected.getTokenExpireTime().getTime()));
		assertThat("selectByAccountId:Salt", actual.getSalt(), is(expected.getSalt()));
		// �㏈��
		
		/**
		 * ���݂��Ȃ����[���A�h���X
		 */
		// ������
		String accountMailNotExist = NOT_EXIST_MAIL;
		// ���s
		Account actualNotExist = accountDao.selectByMail(accountMailNotExist);
		// ����
		assertThat("selectByAccountId:Id", actualNotExist.getId(), is(ConfigConstants.NULL_INT));
		assertThat("selectByAccountId:Name", actualNotExist.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualNotExist.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualNotExist.getMail(), is(""));
		assertThat("selectByAccountId:PassWord", actualNotExist.getPassWord(), is(""));
		assertThat("selectByAccountId:ZipCode", actualNotExist.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualNotExist.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualNotExist.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualNotExist.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualNotExist.getToken(), is(""));
		assertNull("selectByAccountId:TokenExpireTime", actualNotExist.getTokenExpireTime());
		assertThat("selectByAccountId:Salt", actualNotExist.getSalt(), is(""));
		// �㏈��
	}

	@Test
	public void selectByTokenAndTokenExpireTimeTest() throws Exception{
		/**
		 * ���݂���g�[�N���A�L��������
		 */
		// ������
		String tokenExist = TOKEN;
		Calendar tokenExpireTime = DateConversionUtility.stringToCalendar(WITHIN_EXPIRE);
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setId(ID);
		expected.setName(NAME);
		expected.setNameKana(NAME_KANA);
		expected.setMail(MAIL);
		expected.setPassWord(PASS_WORD);
		expected.setZipCode(ZIP_CODE);
		expected.setAddress(ADDRESS);
		expected.setPhoneNumber(PHONE_NUMBER);
		expected.setDelFlg(DEL_FLG);
		expected.setToken(tokenExist);
		expected.setTokenExpireTime(TOKEN_EXPIRE_TIME);
		expected.setSalt(SALT);
		// ���s
		Account actual = accountDao.selectByTokenAndTokenExpireTime(tokenExist, tokenExpireTime);
		// ����
		assertThat("selectByAccountId:Id", actual.getId(), is(expected.getId()));
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:PassWord", actual.getPassWord(), is(expected.getPassWord()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		assertEquals("selectByAccountId:TokenExpireTime", actual.getTokenExpireTime(), new java.sql.Timestamp(expected.getTokenExpireTime().getTime()));
		assertThat("selectByAccountId:Salt", actual.getSalt(), is(expected.getSalt()));
		// �㏈��

		/**
		 * ���݂���g�[�N���A�L���������߂��Ă���
		 */
		// ������
		Calendar tokenExpireTimeOverExpire = DateConversionUtility.stringToCalendar(OVER_EXPIRE);
		// ���s
		Account actualOverExpire = accountDao.selectByTokenAndTokenExpireTime(tokenExist, tokenExpireTimeOverExpire);
		// ����
		assertThat("selectByAccountId:Id", actualOverExpire.getId(), is(ConfigConstants.NULL_INT));
		assertThat("selectByAccountId:Name", actualOverExpire.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualOverExpire.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualOverExpire.getMail(), is(""));
		assertThat("selectByAccountId:PassWord", actualOverExpire.getPassWord(), is(""));
		assertThat("selectByAccountId:ZipCode", actualOverExpire.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualOverExpire.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualOverExpire.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualOverExpire.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualOverExpire.getToken(), is(""));
		assertNull("selectByAccountId:TokenExpireTime", actualOverExpire.getTokenExpireTime());
		assertThat("selectByAccountId:Salt", actualOverExpire.getSalt(), is(""));
		// �㏈��

		/**
		 * ���݂��Ȃ��g�[�N���A�L���������߂��Ă���
		 */
		// ������
		String tokenNotExist = NOT_EXIST_TOKEN;
		// ���s
		Account actualNotExist = accountDao.selectByTokenAndTokenExpireTime(tokenNotExist, tokenExpireTimeOverExpire);
		// ����
		assertThat("selectByAccountId:Id", actualNotExist.getId(), is(ConfigConstants.NULL_INT));
		assertThat("selectByAccountId:Name", actualNotExist.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualNotExist.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualNotExist.getMail(), is(""));
		assertThat("selectByAccountId:PassWord", actualNotExist.getPassWord(), is(""));
		assertThat("selectByAccountId:ZipCode", actualNotExist.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualNotExist.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualNotExist.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualNotExist.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualNotExist.getToken(), is(""));
		assertNull("selectByAccountId:TokenExpireTime", actualNotExist.getTokenExpireTime());
		assertThat("selectByAccountId:Salt", actualNotExist.getSalt(), is(""));
		// �㏈��
	}

	@Test
	public void insertTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setName(NEW_NAME);
		expected.setNameKana(NEW_NAME_KANA);
		expected.setMail(NEW_MAIL);
		expected.setPassWord(NEW_PASS_WORD);
		expected.setZipCode(NEW_ZIP_CODE);
		expected.setAddress(NEW_ADDRESS);
		expected.setPhoneNumber(NEW_PHONE_NUMBER);
		expected.setDelFlg(NEW_DEL_FLG);
		expected.setToken(NEW_TOKEN);
		expected.setTokenExpireTime(NEW_TOKEN_EXPIRE_TIME);
		expected.setSalt(NEW_SALT);
		// ���s
		int accountId = accountDao.insert(expected);
		Account actual = accountDao.selectByAccountId(accountId);
		// ����
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertNull("selectByAccountId:PassWord", actual.getPassWord());
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(false));
		assertNull("selectByAccountId:Token", actual.getToken());
		assertNull("selectByAccountId:TokenExpireTime", actual.getTokenExpireTime());
		assertNull("selectByAccountId:Salt", actual.getSalt());
		// �㏈��
		if (accountId != 0) {
			Connection connection = DbConnector.getConnection();
			String sql = "delete from account where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, accountId);
			int daleteCount = statement.executeUpdate();
			statement.close();
			connection.commit();
			connection.close();
		}

		/**
		 * �ُ�n
		 */
		// ������
		Account expectedNoData = new Account();
		// ���s
		int accountIdNoData = accountDao.insert(expectedNoData);
		Account actualNoData = accountDao.selectByAccountId(accountIdNoData);
		// ����
		assertThat("selectByAccountId:Name", actualNoData.getName(), is(expectedNoData.getName()));
		assertThat("selectByAccountId:NameKana", actualNoData.getNameKana(), is(expectedNoData.getNameKana()));
		assertThat("selectByAccountId:Mail", actualNoData.getMail(), is(expectedNoData.getMail()));
		assertNull("selectByAccountId:PassWord", actualNoData.getPassWord());
		assertThat("selectByAccountId:ZipCode", actualNoData.getZipCode(), is(expectedNoData.getZipCode()));
		assertThat("selectByAccountId:Address", actualNoData.getAddress(), is(expectedNoData.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actualNoData.getPhoneNumber(), is(expectedNoData.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actualNoData.getDelFlg(), is(false));
		assertNull("selectByAccountId:Token", actualNoData.getToken());
		assertNull("selectByAccountId:TokenExpireTime", actualNoData.getTokenExpireTime());
		assertNull("selectByAccountId:Salt", actualNoData.getSalt());
		// �㏈��
		if (accountIdNoData != 0) {
			Connection connectionNoData = DbConnector.getConnection();
			String sqlNoData = "delete from account where id = ?";
			PreparedStatement statementNoData = connectionNoData.prepareStatement(sqlNoData);
			statementNoData.setInt(1, accountIdNoData);
			int daleteCountNoData = statementNoData.executeUpdate();
			statementNoData.close();
			connectionNoData.commit();
			connectionNoData.close();
		}
	}

	@Test
	public void updateTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		Random random = new Random();
		expected.setId(UPDATE_ID);
		expected.setName(String.valueOf(random.nextInt(50)));
		expected.setNameKana(String.valueOf(random.nextInt(50)));
		expected.setMail(String.valueOf(random.nextInt(50)));
		expected.setPassWord(String.valueOf(random.nextInt(50)));
		expected.setZipCode(String.valueOf(random.nextInt(50)));
		expected.setAddress(String.valueOf(random.nextInt(50)));
		expected.setPhoneNumber(String.valueOf(random.nextInt(50)));
		if (random.nextInt(2) == 0) {
			expected.setDelFlg(false);
		} else {
			expected.setDelFlg(true);
		}
		expected.setToken(String.valueOf(random.nextInt(50)));
		expected.setTokenExpireTime(DateConversionUtility.stringToDateTime("2014-07-" + String.valueOf(random.nextInt(30) + " 11:11:11")));
		expected.setSalt(String.valueOf(random.nextInt(50)));
		// ���s
		int accountCount = accountDao.update(expected);
		Account actual = accountDao.selectByAccountId(UPDATE_ID);
		// ����
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:PassWord", actual.getPassWord(), is(expected.getPassWord()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		assertEquals("selectByAccountId:TokenExpireTime", actual.getTokenExpireTime(), new java.sql.Timestamp(expected.getTokenExpireTime().getTime()));
		assertThat("selectByAccountId:Salt", actual.getSalt(), is(expected.getSalt()));
		// �㏈��

		/**
		 * ����n:�X�V��
		 */
		// ������
		Account expectedNoUpdateData = new Account();
		// ���s
		int accountCountNoUpdateData = accountDao.update(expectedNoUpdateData);
		// ����
		assertThat("selectByAccountId:�X�V�Ώۃf�[�^��", accountCountNoUpdateData, is(0));
		// �㏈��

		/**
		 * ����n:�X�V���f�[�^�܂�
		 */
		// ������
		Account expectedPartOfUpdateData = new Account();
		expectedPartOfUpdateData.setId(UPDATE_ID);
		// ���s
		int accountCountPartOfUpdateData = accountDao.update(expectedPartOfUpdateData);
		// ����
		assertThat("selectByAccountId:�X�V�Ώۃf�[�^�ꕔ��", accountCountPartOfUpdateData, is(0));
		// �㏈��

		/**
		 * �ُ�n:���݂��Ȃ�ID
		 */
		// ������
		Account expectedNotExist = new Account();
		expectedNotExist.setId(NOT_EXIST_ID);
		// ���s
		int accountCountNotExist = accountDao.update(expectedNotExist);
		// ����
		assertThat("selectByAccountId:���݂��Ȃ�ID", accountCountNotExist, is(0));
		// �㏈��
	}
}
