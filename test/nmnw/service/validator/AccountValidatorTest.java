package nmnw.service.validator;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.junit.Test;

import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.validator.AccountValidator;

public class AccountValidatorTest {

	@Test
	public void checkNameTest() {
		// ������
		String value = "test";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "������͕K�{�ł��B";
		// 201����
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "�������200�����ȓ��œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkName(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkName:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkName(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkName:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l���ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkName(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// ����
		assertThat( "checkName:�l���ő�l�𒴂��Ă���ꍇ", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// �㏈��
	}

	@Test
	public void checkNameKanaTest() {
		// ������
		String value = "�e�X�g";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "������J�i�͕K�{�ł��B";

		String valueWrongFormat = "test";
		String expectedWrongFormat = "������J�i�͑S�p�J�^�J�i�œ��͂��Ă��������B";
		// 201����
		String valueMaxSize = "�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A�A";
		String expectedMaxSize = "������J�i��200�����ȓ��œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkNameKana(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkNameKana:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkNameKana(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkNameKana:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l�̌`�����������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkNameKana(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// ����
		assertThat( "checkNameKana: �l�̌`�����������Ȃ��ꍇ", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// �㏈��

		/**
		 * �l���ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkNameKana(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// ����
		assertThat( "checkNameKana:�l���ő�l�𒴂��Ă���ꍇ", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// �㏈��
	}

	@Test
	public void checkMailTest() {
		// ������
		String value = "test@net-marketing.co.jp";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "���[���A�h���X�͕K�{�ł��B";
		// 201����
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "���[���A�h���X��200�����ȓ��œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkMail(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkMail:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkMail(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkMail:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l���ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkMail(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// ����
		assertThat( "checkMail:�l���ő�l�𒴂��Ă���ꍇ", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// �㏈��
	}

	@Test
	public void checkPassWordTest() {
		// ������
		String value = "12345678";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "�p�X���[�h�͕K�{�ł��B";

		String valueWrongFormat = "�e�X�g";
		String expectedWrongFormat = "�p�X���[�h�͔��p�p�����œ��͂��Ă��������B";
		// 7����
		String valueMinSize = "1234567";
		String expectedMinSize = "�p�X���[�h��8�����ȏ�œ��͂��Ă��������B";

		// 21����
		String valueMaxSize = "123456789012345678901";
		String expectedMaxSize = "�p�X���[�h��20�����ȓ��œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkPassWord(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkPassWord:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkPassWord(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkPassWord:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l�̌`�����������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkPassWord(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// ����
		assertThat( "checkPassWord: �l�̌`�����������Ȃ��ꍇ", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// �㏈��

		/**
		 * �l���ŏ��l��菭�Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vMinSize = new AccountValidator();
		vMinSize.checkPassWord(valueMinSize);
		List<String> actualFalseMinSize = vMinSize.getValidationList();
		// ����
		assertThat( "checkPassWord:�l���ŏ��l��菭�Ȃ��ꍇ", actualFalseMinSize.get(0), is(expectedMinSize));
		// �㏈��

		/**
		 * �l���ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkPassWord(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// ����
		assertThat( "checkPassWord:�l���ő�l�𒴂��Ă���ꍇ", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// �㏈��
	}

	@Test
	public void checkPassWordAndRetypePassWordTest() {
		// ������
		String compareFrom = "12345678";
		String value = "12345678";
		int expected = 0;

		String valueNotEqual = "12";
		String expectedNotEqual = "�ēx���͂����p�X���[�h�Ƒ��Ⴊ����܂��B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkPassWordAndRetypePassWord(compareFrom, value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkPassWordAndRetypePassWord:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vNotEqual = new AccountValidator();
		vNotEqual.checkPassWordAndRetypePassWord(compareFrom, valueNotEqual);
		List<String> actualFalseNotEqual = vNotEqual.getValidationList();
		// ����
		assertThat( "checkPassWordAndRetypePassWord:�l������Ȃ��ꍇ", actualFalseNotEqual.get(0), is(expectedNotEqual));
		// �㏈��
	}

	@Test
	public void checkZipCodeTest() {
		// ������
		String value = "111-1111";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "�X�֔ԍ��͕K�{�ł��B";

		String valueWrongFormat = "asa-1111";
		String expectedWrongFormat = "�X�֔ԍ��͗X�֔ԍ��`���i������xxx-xxxx�j�œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkZipCode(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkZipCode:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkZipCode(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkZipCode:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l�̌`�����������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkZipCode(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// ����
		assertThat( "checkZipCode:�l�̌`�����������Ȃ��ꍇ", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// �㏈��
	}

	@Test
	public void checkAddressTest() {
		// ������
		String value = "test";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "�Z���͕K�{�ł��B";
		// 201����
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "�Z����200�����ȓ��œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkAddress(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkAddress:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkAddress(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkAddress:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l���ő�l�𒴂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkAddress(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// ����
		assertThat( "checkAddress:�l���ő�l�𒴂��Ă���ꍇ", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// �㏈��
	}

	@Test
	public void checkPhoneNumberTest() {
		// ������
		String value = "099-0009-1111";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "�d�b�ԍ��͕K�{�ł��B";

		String valueWrongFormat = "a11-1111-1111";
		String expectedWrongFormat = "�d�b�ԍ��͐�������є��p�n�C�t���œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkPhoneNumber(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkPhoneNumber:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkPhoneNumber(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkPhoneNumber:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l�̌`�����������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkPhoneNumber(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// ����
		assertThat( "checkPhoneNumber:�l�̌`�����������Ȃ��ꍇ", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// �㏈��
	}

	@Test
	public void checkDelFlgTest() {
		// ������
		String value = "true";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "�폜�t���O�͕K�{�ł��B";

		String valueWrongFormat = "nutral";
		String expectedWrongFormat = "�폜�t���O��true�܂���false�œ��͂��Ă��������B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkDelFlg(value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkDelFlg:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �l����̏ꍇ
		 */
		// ���s
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkDelFlg(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// ����
		assertThat( "checkDelFlg:�l���󕶎��̏ꍇ", actualFalseNotExist.get(0), is(expectedNotExist));
		// �㏈��

		/**
		 * �l�̌`�����������Ȃ��ꍇ
		 */
		// ���s
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkDelFlg(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// ����
		assertThat( "checkDelFlg:�l�̌`�����������Ȃ��ꍇ", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// �㏈��
	}

	@Test
	public void checkTokenExpireTimeTest() {
		// ������
		Date compareFrom = DateConversionUtility.stringToDate("2014-07-01");
		Date value = DateConversionUtility.stringToDate("2014-06-25");
		int expected = 0;

		Date valueOverExpire = DateConversionUtility.stringToDate("2014-07-02");
		String expectedOverExpire = "�g�[�N���̗L���������؂�Ă��܂��B";

		/**
		 * ����ȏꍇ
		 */
		// ���s
		AccountValidator v = new AccountValidator();
		v.checkTokenExpireTime(compareFrom, value);
		List<String> actualTrue = v.getValidationList();
		// ����
		assertThat( "checkTokenExpireTime:����", actualTrue.size(), is(expected));
		// �㏈��

		/**
		 * �L���������߂��Ă���ꍇ
		 */
		// ���s
		AccountValidator vOverExpire = new AccountValidator();
		vOverExpire.checkTokenExpireTime(compareFrom, valueOverExpire);
		List<String> actualFalseOverExpire = vOverExpire.getValidationList();
		// ����
		assertThat( "checkTokenExpireTime:�L���������߂��Ă���ꍇ", actualFalseOverExpire.get(0), is(expectedOverExpire));
		// �㏈��
	}
}
