package nmnw.service.utility;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.sql.Connection;
import com.nmnw.service.utility.DbConnector;

public class DbConnectorTest {

	@Test
	public void testGetConnection() {
		// 初期化
		String expected = "jdbc:mysql://localhost/nmnw?useUnicode=true&characterEncoding=UTF-8";
		String dbmsUrl = null;
		// 実行
		try {
			Connection connection = DbConnector.getConnection();
			dbmsUrl = connection.getMetaData().getURL();
		} catch (Exception e) {
			assumeNoException (e);
		}
		// 検証
		assertThat( "getConnection:正常系エラー:DMBSURL一致", dbmsUrl, is(expected));
		// 後処理
	}

}
