package nmnw.admin.utility;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import javax.servlet.http.Part;

import org.junit.Test;

import com.nmnw.admin.utility.RequestParameterUtility;

public class RequestParameterUtilityTest {

	@Test
	public void isEmptyParamTest() {
		// ������
		String valueTrueNull = null;
		String valueTrueNoString = "";
		String valueFalse = "test";

		/**
		 * �l����łȂ��ꍇ
		 */
		// ���s
		boolean actualFalse = RequestParameterUtility.isEmptyParam(valueFalse);
		// ����
		assertFalse( "isEmptyParam:����n�G���[:null�ł͂Ȃ�", actualFalse );
		// �㏈��

		/**
		 * �l��0�����̏ꍇ
		 */
		// ���s
		boolean actualTrueNoString = RequestParameterUtility.isEmptyParam(valueTrueNoString);
		// ����
		assertTrue( "isEmptyParam:����n�G���[:0����", actualTrueNoString );
		// �㏈��

		/**
		 * �l��null�̏ꍇ
		 */
		// ���s
		boolean actualTrueNull = RequestParameterUtility.isEmptyParam(valueTrueNull);
		// ����
		assertTrue( "isEmptyParam:����n�G���[:null", actualTrueNull );
		// �㏈��
	}

	@Test
	public void isEmptyImageTest() {

		/**
		 * �摜�f�[�^�������ꍇ
		 */
		// ������
		long notExistSize = 0;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(notExistSize);
		replay(valueTrue);
		// ���s
		boolean actualTrue = RequestParameterUtility.isEmptyImage(valueTrue);
		// ����
		assertTrue( "isEmptyImage:����n�G���[:�摜��", actualTrue );
		// �㏈��

		/**
		 * �摜�f�[�^���L��ꍇ
		 */
		// ������
		long existSize = 1;		
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(existSize);
		replay(valueFalse);
		// ���s
		boolean actualFalse = RequestParameterUtility.isEmptyImage(valueFalse);
		// ����
		assertFalse( "isEmptyImage:����n�G���[:�摜�L", actualFalse );
		// �㏈��
	}
}
