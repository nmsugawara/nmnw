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
		// ������
		String expected = "jdbc:mysql://localhost/nmnw?useUnicode=true&characterEncoding=UTF-8";
		String dbmsUrl = null;
		// ���s
		try {
			Connection connection = DbConnector.getConnection();
			dbmsUrl = connection.getMetaData().getURL();
		} catch (Exception e) {
			assumeNoException (e);
		}
		// ����
		assertThat( "getConnection:����n�G���[:DMBSURL��v", dbmsUrl, is(expected));
		// �㏈��
	}

}
