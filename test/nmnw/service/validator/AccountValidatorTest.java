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
		// 初期化
		String value = "test";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "会員名は必須です。";
		// 201文字
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "会員名は200文字以内で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkName(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkName:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkName(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkName:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値が最大値を超えている場合
		 */
		// 実行
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkName(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// 検証
		assertThat( "checkName:値が最大値を超えている場合", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// 後処理
	}

	@Test
	public void checkNameKanaTest() {
		// 初期化
		String value = "テスト";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "会員名カナは必須です。";

		String valueWrongFormat = "test";
		String expectedWrongFormat = "会員名カナは全角カタカナで入力してください。";
		// 201文字
		String valueMaxSize = "アアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアアア";
		String expectedMaxSize = "会員名カナは200文字以内で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkNameKana(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkNameKana:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkNameKana(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkNameKana:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値の形式が正しくない場合
		 */
		// 実行
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkNameKana(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// 検証
		assertThat( "checkNameKana: 値の形式が正しくない場合", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// 後処理

		/**
		 * 値が最大値を超えている場合
		 */
		// 実行
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkNameKana(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// 検証
		assertThat( "checkNameKana:値が最大値を超えている場合", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// 後処理
	}

	@Test
	public void checkMailTest() {
		// 初期化
		String value = "test@net-marketing.co.jp";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "メールアドレスは必須です。";
		// 201文字
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "メールアドレスは200文字以内で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkMail(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkMail:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkMail(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkMail:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値が最大値を超えている場合
		 */
		// 実行
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkMail(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// 検証
		assertThat( "checkMail:値が最大値を超えている場合", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// 後処理
	}

	@Test
	public void checkPassWordTest() {
		// 初期化
		String value = "12345678";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "パスワードは必須です。";

		String valueWrongFormat = "テスト";
		String expectedWrongFormat = "パスワードは半角英数字で入力してください。";
		// 7文字
		String valueMinSize = "1234567";
		String expectedMinSize = "パスワードは8文字以上で入力してください。";

		// 21文字
		String valueMaxSize = "123456789012345678901";
		String expectedMaxSize = "パスワードは20文字以内で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkPassWord(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkPassWord:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkPassWord(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkPassWord:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値の形式が正しくない場合
		 */
		// 実行
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkPassWord(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// 検証
		assertThat( "checkPassWord: 値の形式が正しくない場合", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// 後処理

		/**
		 * 値が最小値より少ない場合
		 */
		// 実行
		AccountValidator vMinSize = new AccountValidator();
		vMinSize.checkPassWord(valueMinSize);
		List<String> actualFalseMinSize = vMinSize.getValidationList();
		// 検証
		assertThat( "checkPassWord:値が最小値より少ない場合", actualFalseMinSize.get(0), is(expectedMinSize));
		// 後処理

		/**
		 * 値が最大値を超えている場合
		 */
		// 実行
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkPassWord(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// 検証
		assertThat( "checkPassWord:値が最大値を超えている場合", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// 後処理
	}

	@Test
	public void checkPassWordAndRetypePassWordTest() {
		// 初期化
		String compareFrom = "12345678";
		String value = "12345678";
		int expected = 0;

		String valueNotEqual = "12";
		String expectedNotEqual = "再度入力したパスワードと相違があります。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkPassWordAndRetypePassWord(compareFrom, value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkPassWordAndRetypePassWord:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が合わない場合
		 */
		// 実行
		AccountValidator vNotEqual = new AccountValidator();
		vNotEqual.checkPassWordAndRetypePassWord(compareFrom, valueNotEqual);
		List<String> actualFalseNotEqual = vNotEqual.getValidationList();
		// 検証
		assertThat( "checkPassWordAndRetypePassWord:値が合わない場合", actualFalseNotEqual.get(0), is(expectedNotEqual));
		// 後処理
	}

	@Test
	public void checkZipCodeTest() {
		// 初期化
		String value = "111-1111";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "郵便番号は必須です。";

		String valueWrongFormat = "asa-1111";
		String expectedWrongFormat = "郵便番号は郵便番号形式（数字でxxx-xxxx）で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkZipCode(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkZipCode:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkZipCode(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkZipCode:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値の形式が正しくない場合
		 */
		// 実行
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkZipCode(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// 検証
		assertThat( "checkZipCode:値の形式が正しくない場合", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// 後処理
	}

	@Test
	public void checkAddressTest() {
		// 初期化
		String value = "test";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "住所は必須です。";
		// 201文字
		String valueMaxSize = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String expectedMaxSize = "住所は200文字以内で入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkAddress(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkAddress:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkAddress(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkAddress:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値が最大値を超えている場合
		 */
		// 実行
		AccountValidator vMaxSize = new AccountValidator();
		vMaxSize.checkAddress(valueMaxSize);
		List<String> actualFalseMaxSize = vMaxSize.getValidationList();
		// 検証
		assertThat( "checkAddress:値が最大値を超えている場合", actualFalseMaxSize.get(0), is(expectedMaxSize));
		// 後処理
	}

	@Test
	public void checkPhoneNumberTest() {
		// 初期化
		String value = "099-0009-1111";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "電話番号は必須です。";

		String valueWrongFormat = "a11-1111-1111";
		String expectedWrongFormat = "電話番号は数字および半角ハイフンで入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkPhoneNumber(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkPhoneNumber:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkPhoneNumber(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkPhoneNumber:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値の形式が正しくない場合
		 */
		// 実行
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkPhoneNumber(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// 検証
		assertThat( "checkPhoneNumber:値の形式が正しくない場合", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// 後処理
	}

	@Test
	public void checkDelFlgTest() {
		// 初期化
		String value = "true";
		int expected = 0;

		String valueNotExist = "";
		String expectedNotExist = "削除フラグは必須です。";

		String valueWrongFormat = "nutral";
		String expectedWrongFormat = "削除フラグはtrueまたｈfalseで入力してください。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkDelFlg(value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkDelFlg:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		AccountValidator vNotExist = new AccountValidator();
		vNotExist.checkDelFlg(valueNotExist);
		List<String> actualFalseNotExist = vNotExist.getValidationList();
		// 検証
		assertThat( "checkDelFlg:値が空文字の場合", actualFalseNotExist.get(0), is(expectedNotExist));
		// 後処理

		/**
		 * 値の形式が正しくない場合
		 */
		// 実行
		AccountValidator vWrongFormat = new AccountValidator();
		vWrongFormat.checkDelFlg(valueWrongFormat);
		List<String> actualFalseWrongFormat = vWrongFormat.getValidationList();
		// 検証
		assertThat( "checkDelFlg:値の形式が正しくない場合", actualFalseWrongFormat.get(0), is(expectedWrongFormat));
		// 後処理
	}

	@Test
	public void checkTokenExpireTimeTest() {
		// 初期化
		Date compareFrom = DateConversionUtility.stringToDate("2014-07-01");
		Date value = DateConversionUtility.stringToDate("2014-06-25");
		int expected = 0;

		Date valueOverExpire = DateConversionUtility.stringToDate("2014-07-02");
		String expectedOverExpire = "トークンの有効期限が切れています。";

		/**
		 * 正常な場合
		 */
		// 実行
		AccountValidator v = new AccountValidator();
		v.checkTokenExpireTime(compareFrom, value);
		List<String> actualTrue = v.getValidationList();
		// 検証
		assertThat( "checkTokenExpireTime:正常", actualTrue.size(), is(expected));
		// 後処理

		/**
		 * 有効期限を過ぎている場合
		 */
		// 実行
		AccountValidator vOverExpire = new AccountValidator();
		vOverExpire.checkTokenExpireTime(compareFrom, valueOverExpire);
		List<String> actualFalseOverExpire = vOverExpire.getValidationList();
		// 検証
		assertThat( "checkTokenExpireTime:有効期限を過ぎている場合", actualFalseOverExpire.get(0), is(expectedOverExpire));
		// 後処理
	}
}
