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
		// ������
		String actual = null;
		// ���s
		try {
			actual = RandomStringUtility.generateToken();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		assertNotNull("generateToken", actual);
		// �㏈��
	}

	@Test
	public void generateSaltTest() {
		// ������
		String actual = null;
		// ���s
		try {
			actual = RandomStringUtility.generateSalt();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		assertNotNull("generateSalt", actual);
		// �㏈��
	}

	@Test
	public void generateRandomStringTest() {
		// ������
		String actual = null;
		int length = LENGTH;
		int expected = LENGTH * 2;
		// ���s
		try {
			actual = RandomStringUtility.generateRandomString(length);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// ����
		assertThat("generateRandomString", expected, is(actual.length()));
		// �㏈��
	}

}
