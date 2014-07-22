package nmnw.service.utility;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import javax.servlet.http.Part;

import org.junit.Test;

import com.nmnw.service.utility.RequestParameterUtility;

public class RequestParameterUtilityTest {
	public final static long IMAGE_EXIST = 1;
	public final static long IMAGE_NOT_EXIST = 0;

	@Test
	public void requestParameterTest() {
		RequestParameterUtility r = new RequestParameterUtility();
	}

	@Test
	public void isEmptyParamTest() {
		/**
		 * �l������ꍇ
		 */
		// ������
		String value = "test";
		// ���s
		Boolean actual = RequestParameterUtility.isEmptyParam(value);
		// ����
		assertFalse("isEmptyParam:�l������ꍇ", actual);
		// �㏈��

		/**
		 * �l�������ꍇ
		 */
		// ������
		String valueNoData = "";
		// ���s
		Boolean actualNoData = RequestParameterUtility.isEmptyParam(valueNoData);
		// ����
		assertTrue("isEmptyParam:�l�������ꍇ", actualNoData);
		// �㏈��

		/**
		 * �l��null�̏ꍇ
		 */
		// ������
		String valueNull = null;
		// ���s
		Boolean actualNull = RequestParameterUtility.isEmptyParam(valueNull);
		// ����
		assertTrue("isEmptyParam:�l��null�̏ꍇ", actualNull);
		// �㏈��
	}

	@Test
	public void isEmptyImageTest() {
		/**
		 * �摜������ꍇ
		 */
		// ������
		Part value = createMock(Part.class);
		expect(value.getSize()).andReturn(IMAGE_EXIST);
		replay(value);
		// ���s
		Boolean actual = RequestParameterUtility.isEmptyImage(value);
		// ����
		assertFalse("isEmptyImage:�摜������ꍇ", actual);
		// �㏈��

		/**
		 * �摜�������ꍇ
		 */
		// ������
		Part valueNoImage = createMock(Part.class);
		expect(valueNoImage.getSize()).andReturn(IMAGE_NOT_EXIST);
		replay(valueNoImage);
		// ���s
		Boolean actualNoImage = RequestParameterUtility.isEmptyImage(valueNoImage);
		// ����
		assertTrue("isEmptyImage:�摜�������ꍇ", actualNoImage);
		// �㏈��
	}
}
