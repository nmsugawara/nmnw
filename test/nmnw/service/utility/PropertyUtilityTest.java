package nmnw.service.utility;

import static org.junit.Assert.*;
import org.junit.Test;
import com.nmnw.service.utility.PropertyUtility;

public class PropertyUtilityTest {

	@Test
	public void getPropertyValueTest() {
		// ������
		String expected = "localhost:8080";
		String keyTrue = "DOMAIN";
		String keyFalseNotExist = "TEST";

		/**
		 * ���݂���Key�̏ꍇ
		 */
		// ���s
		String actualTrue = PropertyUtility.getPropertyValue(keyTrue);
		// ����
		assertEquals("getPropertyValue:����n�G���[", actualTrue, expected);
		// �㏈��

		/**
		 * ���݂��Ȃ�Key�̏ꍇ
		 */
		// ���s
		String actualFalseNotExist = PropertyUtility.getPropertyValue(keyFalseNotExist);
		// ����
		assertNull("getPropertyValue:�ُ�n�G���[:���݂��Ȃ��l", actualFalseNotExist);
		// �㏈��
	}
}
