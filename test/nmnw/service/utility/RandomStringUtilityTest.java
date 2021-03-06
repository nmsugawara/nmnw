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
		// 初期化
		String actual = null;
		// 実行
		try {
			actual = RandomStringUtility.generateToken();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		assertNotNull("generateToken", actual);
		// 後処理
	}

	@Test
	public void generateSaltTest() {
		// 初期化
		String actual = null;
		// 実行
		try {
			actual = RandomStringUtility.generateSalt();
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		assertNotNull("generateSalt", actual);
		// 後処理
	}

	@Test
	public void generateRandomStringTest() {
		// 初期化
		String actual = null;
		int length = LENGTH;
		int expected = LENGTH * 2;
		// 実行
		try {
			actual = RandomStringUtility.generateRandomString(length);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// 検証
		assertThat("generateRandomString", expected, is(actual.length()));
		// 後処理
	}

}
