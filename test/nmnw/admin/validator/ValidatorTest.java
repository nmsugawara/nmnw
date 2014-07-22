package nmnw.admin.validator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.junit.Test;

import com.nmnw.admin.validator.Validator;

public class ValidatorTest {

	@Test
	public void requiredTest() {
		// ������
		Validator v = new Validator();
		String valueTrueNotExist = "";
		String valueTrueNull = null;
		String valueFalse = "test";
		String fieldName = "test";

		/**
		 * �l����łȂ��ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.required(valueFalse, fieldName);
		// ����
		assertFalse( "required:�l������ꍇ", hasErrorFalse );
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		boolean hasErrorTrueNotExist = v.required(valueTrueNotExist, fieldName);
		// ����
		assertTrue( "required:�l���󕶎��̏ꍇ", hasErrorTrueNotExist );
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		boolean hasErrorTrueNull = v.required(valueTrueNull, fieldName);
		// ����
		assertTrue( "required:�l��null�̏ꍇ", hasErrorTrueNull );
		// �㏈��
	}

	@Test
	public void requiredSelectTest() {
		// ������
		Validator v = new Validator();
		String valueTrue = "0";
		String valueFalse = "1";
		String fieldName = "test";

		/**
		 * �I������Ă���ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.requiredSelect(valueFalse, fieldName);
		// ����
		assertFalse( "requiredSelect:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �I������Ă��Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.requiredSelect(valueTrue, fieldName);
		// ����
		assertTrue( "requiredSelect:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void requiredImageTest() throws ServletException, IOException {
		// ������
		Validator v = new Validator();
		String fieldName = "test";

		/**
		 * �摜�f�[�^������ꍇ
		 */
		long existSize = 1;
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(existSize);
		replay(valueFalse);
		// ���s
		boolean hasErrorFalse = v.requiredImage(valueFalse, fieldName);
		// ����
		assertFalse( "requiredImage:����n�G���[:�摜�L", hasErrorFalse );
		// �㏈��

		/**
		 * �摜�f�[�^���Ȃ��ꍇ
		 */
		long notExistSize = 0;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(notExistSize);
		replay(valueTrue);
		// ���s
		boolean hasErrorTrue = v.requiredImage(valueTrue, fieldName);
		// ����
		assertTrue( "requiredImage:����n�G���[:�摜��", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void isIntTest() {
		// ������
		Validator v = new Validator();
		String valueTrue = "test";
		String valueFalse = "1";
		String fieldName = "test";

		/**
		 * �l�������̏ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.isInt(valueFalse, fieldName);
		// ����
		assertFalse( "isInt:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �l�������łȂ��ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.isInt(valueTrue, fieldName);
		// ����
		assertTrue( "isInt:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void isDateTest() {
		// ������
		Validator v = new Validator();
		String valueFalse = "2014-07-01";
		String valueTrueNotDate = "test";
		String valueTrueIllegalFormat = "2014/07/01";
		String fieldName = "test";

		/**
		 * �l�����t�iYYYY-MM-DD�j�ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.isDate(valueFalse, fieldName);
		// ����
		assertFalse( "isDate:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �l�����t�����`�����������Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorTrueIllegalFormat = v.isInt(valueTrueIllegalFormat, fieldName);
		// ����
		assertTrue( "isDate:�ُ�n�G���[�F���t�`�����������Ȃ��ꍇ", hasErrorTrueIllegalFormat );
		// �㏈��

		/**
		 * �l�����t�ł͂Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorTrueNotDate = v.isInt(valueTrueNotDate, fieldName);
		// ����
		assertTrue( "isDate:�ُ�n�G���[�F���t�ł͂Ȃ��ꍇ", hasErrorTrueNotDate );
		// �㏈��
	}

	@Test
	public void correctPeriodTest() {
		// ������
		Validator v = new Validator();
		String valueFromFalse = "2014-07-01";
		String valueToFalse = "2014-07-31";
		String valueFromTrueIllegalFormat = "2014/07/01";
		String valueToTrueIllegalFormat = "2014/07/01";
		String valueFromTrueIllegalPeriod = "2014-07-01";
		String valueToTrueIllegalPeriod = "2014-06-01";

		String fieldName = "test";

		/**
		 * �l�����������t�`���A�����Ԃ�����ȏꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.correctPeriod(valueFromFalse, fieldName, valueToFalse, fieldName);
		// ����
		assertFalse( "correctPeriod:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �l�����������t�`���ł͂Ȃ��ꍇ(From)
		 */
		// ���s
		boolean hasErrorTrueIllegalFrom = v.correctPeriod(valueFromTrueIllegalFormat, fieldName, valueToFalse, fieldName);
		// ����
		assertTrue( "correctPeriod:�ُ�n�G���[�FFrom�̌`�����ُ�", hasErrorTrueIllegalFrom );
		// �㏈��

		/**
		 * �l�����������t�`���ł͂Ȃ��ꍇ(To)
		 */
		// ���s
		boolean hasErrorTrueIllegalTo = v.correctPeriod(valueFromFalse, fieldName, valueToTrueIllegalFormat, fieldName);
		// ����
		assertTrue( "correctPeriod:�ُ�n�G���[�FTo�̌`�����ُ�", hasErrorTrueIllegalTo );
		// �㏈��

		/**
		 * �l�����������t�`���ł͂Ȃ��ꍇ(From/To)
		 */
		// ���s
		boolean hasErrorTrueIllegalFromTo = v.correctPeriod(valueFromTrueIllegalFormat, fieldName, valueToTrueIllegalFormat, fieldName);
		// ����
		assertTrue( "correctPeriod:�ُ�n�G���[�FFromTo�̌`�����ُ�", hasErrorTrueIllegalFromTo );
		// �㏈��

		/**
		 * �l�����������t�`�������A���Ԃ��ُ�ȏꍇ
		 */
		// ���s
		boolean hasErrorTrueIllegalPeriod = v.correctPeriod(valueFromTrueIllegalPeriod, fieldName, valueToTrueIllegalPeriod, fieldName);
		// ����
		assertTrue( "correctPeriod:�ُ�n�G���[�F���Ԃ��ُ�", hasErrorTrueIllegalPeriod );
		// �㏈��
	}

	@Test
	public void maxSizeIntTest() {
		// ������
		Validator v = new Validator();
		int valueTrue = 5;
		int valueFalse = 1;
		int maxSize = 2;
		String fieldName = "test";

		/**
		 * �ő�l�𒴂��Ă��Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.maxSizeInt(valueFalse, maxSize, fieldName);
		// ����
		assertFalse( "maxSizeInt:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.maxSizeInt(valueTrue, maxSize, fieldName);
		// ����
		assertTrue( "maxSizeInt:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void maxSizeStringTest() {
		// ������
		Validator v = new Validator();
		String valueTrue = "aaa";
		String valueFalse = "a";
		int maxSize = 2;
		String fieldName = "test";

		/**
		 * �ő啶�����𒴂��Ă��Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.maxSizeString(valueFalse, maxSize, fieldName);
		// ����
		assertFalse( "maxSizeString:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �ő啶�����𒴂��Ă���ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.maxSizeString(valueTrue, maxSize, fieldName);
		// ����
		assertTrue( "maxSizeString:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void maxSizeImageTest() {
		// ������
		Validator v = new Validator();
		long maxSize = 500000;
		String fieldName = "test";

		/**
		 * �摜�f�[�^���ő�T�C�Y�ȓ��̏ꍇ
		 */
		long withinMaxSize = 100;
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(withinMaxSize);
		replay(valueFalse);
		// ���s
		boolean hasErrorFalse = v.maxSizeImage(valueFalse.getSize(), maxSize, fieldName);
		// ����
		assertFalse( "maxSizeImage:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �摜�f�[�^���ő�T�C�Y�ȏ�̏ꍇ
		 */
		long overMaxSize = 10000000;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(overMaxSize);
		replay(valueTrue);
		// ���s
		boolean hasErrorTrue = v.maxSizeImage(valueTrue.getSize(), maxSize, fieldName);
		// ����
		assertTrue( "maxSizeImage:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void minSizeIntTest() {
		// ������
		Validator v = new Validator();
		int valueTrue = 1;
		int valueFalse = 5;
		int minSize = 2;
		String fieldName = "test";

		/**
		 * �ŏ��l�𒴂��Ă��Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.minSizeInt(valueFalse, minSize, fieldName);
		// ����
		assertFalse( "minSizeInt:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �ŏ��l�𒴂��Ă���ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.minSizeInt(valueTrue, minSize, fieldName);
		// ����
		assertTrue( "minSizeInt:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void minSizeStringTest() {
		// ������
		Validator v = new Validator();
		String valueTrue = "a";
		String valueFalse = "aaa";
		int minSize = 2;
		String fieldName = "test";

		/**
		 * �ŏ��������𒴂��Ă��Ȃ��ꍇ
		 */
		// ���s
		boolean hasErrorFalse = v.minSizeString(valueFalse, minSize, fieldName);
		// ����
		assertFalse( "minSizeString:����n�G���[", hasErrorFalse );
		// �㏈��

		/**
		 * �ŏ��������𒴂��Ă���ꍇ
		 */
		// ���s
		boolean hasErrorTrue = v.minSizeString(valueTrue, minSize, fieldName);
		// ����
		assertTrue( "minSizeString:�ُ�n�G���[", hasErrorTrue );
		// �㏈��
	}

	@Test
	public void getMessageTest() {
		// ������
		Validator v = new Validator();
		String message = "{$1}{$2}";
		String value = "test";
		String expected = value+ value;

		/**
		 * ����Ƀ��b�Z�[�W���擾�o���A�u�����o�����ꍇ
		 */
		// ���s
		String resultMessage = v.getMessage(message, value, value);
		// ����
		assertThat( "getMessage:����n�G���[", resultMessage , is( expected ));
		// �㏈��

		/**
		 * �p�����[�^����
		 */
		// ���s
		String resultMessageTooManyParameters = v.getMessage(message, value, value, value);
		// ����
		assertThat( "getMessage:�ُ�n�G���[�F�p�����[�^�s��", resultMessageTooManyParameters , is( expected ));
		// �㏈��
	}

	@Test
	public void addErrorMessageListTest() {
		// ������
		Validator v = new Validator();
		String value = "test";
		List<String> expected = new ArrayList<String>();
		expected.add(value);

		/**
		 * ����Ƀ��b�Z�[�W�̒ǉ����o�����ꍇ
		 */
		// ���s
		v.addErrorMessageList(value);
		List<String> resultMessageList = v.getErrorMessageList();
		// ����
		assertThat( "addErrorMessageList:����n�G���[", resultMessageList , is( expected ));
		// �㏈��
	}

	@Test
	public void getErrorMessageListTest() {
		// ������
		Validator v = new Validator();
		String value = "test";
		List<String> expected = new ArrayList<String>();

		/**
		 * ���b�Z�[�W�������ǉ�����Ă��Ȃ��ꍇ
		 */
		// ���s
		List<String> resultMessageListNull = v.getErrorMessageList();
		// ����
		assertThat( "getErrorMessageList:����n�G���[�F���b�Z�[�W��", resultMessageListNull , is( expected ));
		// �㏈��

		/**
		 * ���b�Z�[�W���ǉ����ꂽ�ꍇ
		 */
		// ���s
		v.addErrorMessageList(value);
		List<String> resultMessageListNotNull = v.getErrorMessageList();
		expected.add(value);
		// ����
		assertThat( "getErrorMessageList:����n�G���[�F���b�Z�[�W�L", resultMessageListNotNull , is( expected ));
		// �㏈��
	}
}
