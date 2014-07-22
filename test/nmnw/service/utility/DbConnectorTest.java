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
		// ‰Šú‰»
		String expected = "jdbc:mysql://localhost/nmnw?useUnicode=true&characterEncoding=UTF-8";
		String dbmsUrl = null;
		// Às
		try {
			Connection connection = DbConnector.getConnection();
			dbmsUrl = connection.getMetaData().getURL();
		} catch (Exception e) {
			assumeNoException (e);
		}
		// ŒŸØ
		assertThat( "getConnection:³íŒnƒGƒ‰[:DMBSURLˆê’v", dbmsUrl, is(expected));
		// Œãˆ—
	}

}
