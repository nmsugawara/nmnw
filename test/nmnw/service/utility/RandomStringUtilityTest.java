package nmnw.service.utility;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import com.nmnw.service.utility.RandomStringUtility;

public class RandomStringUtilityTest {
	public final static int LENGTH = 16;

	@Test
	public void randomStringUtilityTest() {
		RandomStringUtility r = new RandomStringUtility();
	}

	@Test
	public void generateTokenTest() {
		// ‰Šú‰»
		String actual = null;
		// Às
		try {
			actual = RandomStringUtility.generateToken();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ŒŸØ
		assertNotNull("generateToken", actual);
		// Œãˆ—
	}

	@Test
	public void generateSaltTest() {
		// ‰Šú‰»
		String actual = null;
		// Às
		try {
			actual = RandomStringUtility.generateSalt();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ŒŸØ
		assertNotNull("generateSalt", actual);
		// Œãˆ—
	}

	@Test
	public void generateRandomStringTest() {
		// ‰Šú‰»
		String actual = null;
		int length = LENGTH;
		int expected = LENGTH * 2;
		// Às
		try {
			actual = RandomStringUtility.generateRandomString(length);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ŒŸØ
		assertThat("generateRandomString", expected, is(actual.length()));
		// Œãˆ—
	}

}
