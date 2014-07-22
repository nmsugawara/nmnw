package nmnw.service.utility;

import static org.junit.Assert.*;
import org.junit.Test;
import com.nmnw.service.utility.PropertyUtility;

public class PropertyUtilityTest {

	@Test
	public void getPropertyValueTest() {
		// 初期化
		String expected = "localhost:8080";
		String keyTrue = "DOMAIN";
		String keyFalseNotExist = "TEST";

		/**
		 * 存在するKeyの場合
		 */
		// 実行
		String actualTrue = PropertyUtility.getPropertyValue(keyTrue);
		// 検証
		assertEquals("getPropertyValue:正常系エラー", actualTrue, expected);
		// 後処理

		/**
		 * 存在しないKeyの場合
		 */
		// 実行
		String actualFalseNotExist = PropertyUtility.getPropertyValue(keyFalseNotExist);
		// 検証
		assertNull("getPropertyValue:異常系エラー:存在しない値", actualFalseNotExist);
		// 後処理
	}
}
