package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import com.nmnw.admin.dao.Account;
import com.nmnw.admin.dao.AccountDao;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class AccountDaoTest {

	@Test
	public void selectByAccountIdTest() throws Exception{
		/**
		 * ���݂�����ID
		 */
		// ������
		int accountIdExist = 11;
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setId(accountIdExist);
		expected.setName("�e�X�g���Y");
		expected.setNameKana("�e�X�g�^���E");
		expected.setMail("tracktest004@net-marketing.co.jp");
		expected.setZipCode("111-1111");
		expected.setAddress("�e�X�g");
		expected.setPhoneNumber("11-1111-1111");
		expected.setDelFlg(false);
		expected.setToken("d359a56ee1020b3690d590bf123e45a6");
		expected.setSalt("49c5552a6a5efb07");
		// ���s
		Account actual = accountDao.selectByAccountId(accountIdExist);
		// ����
		assertThat("selectByAccountId:Id", actual.getId(), is(expected.getId()));
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		// �㏈��
		
		/**
		 * ���݂��Ȃ����ID
		 */
		// ������
		int accountIdNotExist = 9999;
		// ���s
		Account actualNotExist = accountDao.selectByAccountId(accountIdNotExist);
		// ����
		assertThat("selectByAccountId:Id", actualNotExist.getId(), is(0));
		assertThat("selectByAccountId:Name", actualNotExist.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualNotExist.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualNotExist.getMail(), is(""));
		assertThat("selectByAccountId:ZipCode", actualNotExist.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualNotExist.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualNotExist.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualNotExist.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualNotExist.getToken(), is(""));
		// �㏈��
	}

	@Test
	public void selectBySearchTest() throws Exception{
		/**
		 * ���������L:
		 */
		// ������
		int accountIdExist = 11;
		String accountNameExist = "�e";
		String accountNameKanaExist = "�X";
		String accountMailExist = "net";

		AccountDao accountDao = new AccountDao();
		Connection connection = DbConnector.getConnection();
		String sql = "select * from account where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountIdExist);
		ResultSet result = statement.executeQuery();
		Account expected = new Account();
		while (result.next()) {
			expected.setName(result.getString("name"));
			expected.setNameKana(result.getString("name_kana"));
			expected.setMail(result.getString("mail"));
			expected.setPassWord(result.getString("password"));
			expected.setZipCode(result.getString("zip_code"));
			expected.setAddress(result.getString("address"));
			expected.setPhoneNumber(result.getString("phone_number"));
			expected.setDelFlg(result.getBoolean("del_flg"));
			expected.setToken(result.getString("token"));
			expected.setSalt(result.getString("salt"));
		}
		// ���s
		List<Account> actual = accountDao.selectBySearch(accountIdExist, accountNameExist, accountNameKanaExist, accountMailExist);
		// ����
		assertThat("selectByAccountId:Name", actual.get(0).getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.get(0).getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.get(0).getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:ZipCode", actual.get(0).getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.get(0).getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.get(0).getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.get(0).getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.get(0).getToken(), is(expected.getToken()));
		// �㏈��

		/**
		 * ����������:�󕶎�
		 */
		// ������
		int accountIdNotExist = -1;
		String accountNameNotExist = "";
		String accountNameKanaNotExist = "";
		String accountmailNotExist = "";

		String sqlNotExist = "select count(*) as count from account";
		PreparedStatement statementNotExist = connection.prepareStatement(sqlNotExist);
		ResultSet resultNotExist = statementNotExist.executeQuery();
		int expectedListCountNotExist = 0;
		while (resultNotExist.next()) {
			expectedListCountNotExist = resultNotExist.getInt("count");
		}

		// ���s
		List<Account> actualNotExist = accountDao.selectBySearch(accountIdNotExist, accountNameNotExist, accountNameKanaNotExist, accountmailNotExist);
		// ����
		assertThat("selectBySearch:������", actualNotExist.size(), is(expectedListCountNotExist));

		/**
		 * ����������:null
		 */
		// ������
		int accountIdNull = -1;
		String accountNameNull = null;
		String accountNameKanaNull = null;
		String accountmailNull = null;

		// ���s
		List<Account> actualNull = accountDao.selectBySearch(accountIdNull, accountNameNull, accountNameKanaNull, accountmailNull);
		// ����
		assertThat("selectBySearch:������:null", actualNull.size(), is(expectedListCountNotExist));

	}
}
