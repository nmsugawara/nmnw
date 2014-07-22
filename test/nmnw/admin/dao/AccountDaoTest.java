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
		 * 存在する会員ID
		 */
		// 初期化
		int accountIdExist = 11;
		AccountDao accountDao = new AccountDao();
		Account expected = new Account();
		expected.setId(accountIdExist);
		expected.setName("テスト太郎");
		expected.setNameKana("テストタロウ");
		expected.setMail("tracktest004@net-marketing.co.jp");
		expected.setZipCode("111-1111");
		expected.setAddress("テスト");
		expected.setPhoneNumber("11-1111-1111");
		expected.setDelFlg(false);
		expected.setToken("d359a56ee1020b3690d590bf123e45a6");
		expected.setSalt("49c5552a6a5efb07");
		// 実行
		Account actual = accountDao.selectByAccountId(accountIdExist);
		// 検証
		assertThat("selectByAccountId:Id", actual.getId(), is(expected.getId()));
		assertThat("selectByAccountId:Name", actual.getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:ZipCode", actual.getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.getToken(), is(expected.getToken()));
		// 後処理
		
		/**
		 * 存在しない会員ID
		 */
		// 初期化
		int accountIdNotExist = 9999;
		// 実行
		Account actualNotExist = accountDao.selectByAccountId(accountIdNotExist);
		// 検証
		assertThat("selectByAccountId:Id", actualNotExist.getId(), is(0));
		assertThat("selectByAccountId:Name", actualNotExist.getName(), is(""));
		assertThat("selectByAccountId:NameKana", actualNotExist.getNameKana(), is(""));
		assertThat("selectByAccountId:Mail", actualNotExist.getMail(), is(""));
		assertThat("selectByAccountId:ZipCode", actualNotExist.getZipCode(), is(""));
		assertThat("selectByAccountId:Address", actualNotExist.getAddress(), is(""));
		assertThat("selectByAccountId:PhoneNumber", actualNotExist.getPhoneNumber(), is(""));
		assertThat("selectByAccountId:DelFlg", actualNotExist.getDelFlg(), is(false));
		assertThat("selectByAccountId:Token", actualNotExist.getToken(), is(""));
		// 後処理
	}

	@Test
	public void selectBySearchTest() throws Exception{
		/**
		 * 検索条件有:
		 */
		// 初期化
		int accountIdExist = 11;
		String accountNameExist = "テ";
		String accountNameKanaExist = "ス";
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
		// 実行
		List<Account> actual = accountDao.selectBySearch(accountIdExist, accountNameExist, accountNameKanaExist, accountMailExist);
		// 検証
		assertThat("selectByAccountId:Name", actual.get(0).getName(), is(expected.getName()));
		assertThat("selectByAccountId:NameKana", actual.get(0).getNameKana(), is(expected.getNameKana()));
		assertThat("selectByAccountId:Mail", actual.get(0).getMail(), is(expected.getMail()));
		assertThat("selectByAccountId:ZipCode", actual.get(0).getZipCode(), is(expected.getZipCode()));
		assertThat("selectByAccountId:Address", actual.get(0).getAddress(), is(expected.getAddress()));
		assertThat("selectByAccountId:PhoneNumber", actual.get(0).getPhoneNumber(), is(expected.getPhoneNumber()));
		assertThat("selectByAccountId:DelFlg", actual.get(0).getDelFlg(), is(expected.getDelFlg()));
		assertThat("selectByAccountId:Token", actual.get(0).getToken(), is(expected.getToken()));
		// 後処理

		/**
		 * 検索条件無:空文字
		 */
		// 初期化
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

		// 実行
		List<Account> actualNotExist = accountDao.selectBySearch(accountIdNotExist, accountNameNotExist, accountNameKanaNotExist, accountmailNotExist);
		// 検証
		assertThat("selectBySearch:条件無", actualNotExist.size(), is(expectedListCountNotExist));

		/**
		 * 検索条件無:null
		 */
		// 初期化
		int accountIdNull = -1;
		String accountNameNull = null;
		String accountNameKanaNull = null;
		String accountmailNull = null;

		// 実行
		List<Account> actualNull = accountDao.selectBySearch(accountIdNull, accountNameNull, accountNameKanaNull, accountmailNull);
		// 検証
		assertThat("selectBySearch:条件無:null", actualNull.size(), is(expectedListCountNotExist));

	}
}
