package nmnw.service.validator;

import static org.junit.Assert.*;
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
import com.nmnw.service.validator.Validator;

public class ValidatorTest {

	@Test
	public void requiredTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrueNotExist = "";
		String valueTrueNull = null;
		String valueFalse = "test";
		String fieldName = "test";

		/**
		 * 値が空でない場合
		 */
		// 実行
		boolean hasErrorFalse = v.required(valueFalse, fieldName);
		// 検証
		assertFalse( "required:値がある場合", hasErrorFalse );
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		boolean hasErrorTrueNotExist = v.required(valueTrueNotExist, fieldName);
		// 検証
		assertTrue( "required:値が空文字の場合", hasErrorTrueNotExist );
		// 後処理

		/**
		 * 値が空の場合
		 */
		// 実行
		boolean hasErrorTrueNull = v.required(valueTrueNull, fieldName);
		// 検証
		assertTrue( "required:値がnullの場合", hasErrorTrueNull );
		// 後処理
	}

	@Test
	public void requiredSelectTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "0";
		String valueFalse = "1";
		String fieldName = "test";

		/**
		 * 選択されている場合
		 */
		// 実行
		boolean hasErrorFalse = v.requiredSelect(valueFalse, fieldName);
		// 検証
		assertFalse( "requiredSelect:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 選択されていない場合
		 */
		// 実行
		boolean hasErrorTrue = v.requiredSelect(valueTrue, fieldName);
		// 検証
		assertTrue( "requiredSelect:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void requiredImageTest() throws ServletException, IOException {
		// 初期化
		Validator v = new Validator();
		String fieldName = "test";

		/**
		 * 画像データがある場合
		 */
		long existSize = 1;
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(existSize);
		replay(valueFalse);
		// 実行
		boolean hasErrorFalse = v.requiredImage(valueFalse, fieldName);
		// 検証
		assertFalse( "requiredImage:正常系エラー:画像有", hasErrorFalse );
		// 後処理

		/**
		 * 画像データがない場合
		 */
		long notExistSize = 0;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(notExistSize);
		replay(valueTrue);
		// 実行
		boolean hasErrorTrue = v.requiredImage(valueTrue, fieldName);
		// 検証
		assertTrue( "requiredImage:正常系エラー:画像無", hasErrorTrue );
		// 後処理
	}

	@Test
	public void isIntTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "test";
		String valueFalse = "1";
		String fieldName = "test";

		/**
		 * 値が数字の場合
		 */
		// 実行
		boolean hasErrorFalse = v.isInt(valueFalse, fieldName);
		// 検証
		assertFalse( "isInt:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 値が数字でない場合
		 */
		// 実行
		boolean hasErrorTrue = v.isInt(valueTrue, fieldName);
		// 検証
		assertTrue( "isInt:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void isBooleanTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "1";
		String valueFalseTrue = "true";
		String valueFalseFalse = "false";
		String fieldName = "test";

		/**
		 * 値がBooleanの場合:true
		 */
		// 実行
		boolean hasErrorFalseTrue = v.isBoolean(valueFalseTrue, fieldName);
		// 検証
		assertFalse( "isBoolean:正常系エラー:true", hasErrorFalseTrue );
		// 後処理

		/**
		 * 値がBooleanの場合:false
		 */
		// 実行
		boolean hasErrorFalseFalse = v.isBoolean(valueFalseFalse, fieldName);
		// 検証
		assertFalse( "isBoolean:正常系エラー:false", hasErrorFalseFalse );
		// 後処理

		/**
		 * 値がBooleanでない場合
		 */
		// 実行
		boolean hasErrorTrue = v.isBoolean(valueTrue, fieldName);
		// 検証
		assertTrue( "isBoolean:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void isDateTest() {
		// 初期化
		Validator v = new Validator();
		String valueFalse = "2014-07-01";
		String valueTrueNotDate = "test";
		String valueTrueIllegalFormat = "2014/07/01";
		String fieldName = "test";

		/**
		 * 値が日付（YYYY-MM-DD）場合
		 */
		// 実行
		boolean hasErrorFalse = v.isDate(valueFalse, fieldName);
		// 検証
		assertFalse( "isDate:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 値が日付だが形式が正しくない場合
		 */
		// 実行
		boolean hasErrorTrueIllegalFormat = v.isInt(valueTrueIllegalFormat, fieldName);
		// 検証
		assertTrue( "isDate:異常系エラー：日付形式が正しくない場合", hasErrorTrueIllegalFormat );
		// 後処理

		/**
		 * 値が日付ではない場合
		 */
		// 実行
		boolean hasErrorTrueNotDate = v.isInt(valueTrueNotDate, fieldName);
		// 検証
		assertTrue( "isDate:異常系エラー：日付ではない場合", hasErrorTrueNotDate );
		// 後処理
	}

	@Test
	public void isHalfWidthCharactersTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "Ｋ";
		String valueFalseEng = "k";
		String valueFalseNum = "1";
		String fieldName = "test";

		/**
		 * 値が半角数字の場合
		 */
		// 実行
		boolean hasErrorFalseNum = v.isHalfWidthCharacters(valueFalseNum, fieldName);
		// 検証
		assertFalse( "isHalfWidthCharacters:正常系エラー:半角数字", hasErrorFalseNum );
		// 後処理

		/**
		 * 値が半角英字の場合
		 */
		// 実行
		boolean hasErrorFalseEng = v.isHalfWidthCharacters(valueFalseEng, fieldName);
		// 検証
		assertFalse( "isHalfWidthCharacters:正常系エラー:半角英字", hasErrorFalseEng );
		// 後処理

		/**
		 * 値が半角英数字でない場合
		 */
		// 実行
		boolean hasErrorTrue = v.isHalfWidthCharacters(valueTrue, fieldName);
		// 検証
		assertTrue( "isHalfWidthCharacters:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void isKatakanaTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrueHalfKana = "ｶ";
		String valueTrueEng = "test";
		String valueFalse = "テスト";
		String fieldName = "test";

		/**
		 * 値が全角カタカナの場合
		 */
		// 実行
		boolean hasErrorFalse = v.isKatakana(valueFalse, fieldName);
		// 検証
		assertFalse( "isKatakana:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 値が半角カタカナ場合
		 */
		// 実行
		boolean hasErrorTrueHalfKana = v.isKatakana(valueTrueHalfKana, fieldName);
		// 検証
		assertTrue( "isKatakana:異常系エラー：半角カタカナ", hasErrorTrueHalfKana );
		// 後処理

		/**
		 * 値が英字場合
		 */
		// 実行
		boolean hasErrorTrueEng = v.isKatakana(valueTrueEng, fieldName);
		// 検証
		assertTrue( "isKatakana:異常系エラー：半角英字", hasErrorTrueEng );
		// 後処理
	}
	
	@Test
	public void isZipCodeTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrueNotHaifun = "1111111";
		String valueTrueOverNuber = "111-11111";
		String valueTrueUnderNuber = "111-111";
		String valueTrueString = "a11-1111";
		String valueFalse = "111-1111";
		String fieldName = "test";

		/**
		 * 値がxxx-xxxxの場合
		 */
		// 実行
		boolean hasErrorFalse = v.isZipCode(valueFalse, fieldName);
		// 検証
		assertFalse( "isZipCode:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * ハイフンが無い場合
		 */
		// 実行
		boolean hasErrorTrueNotHaifun = v.isZipCode(valueTrueNotHaifun, fieldName);
		// 検証
		assertTrue( "isZipCode:異常系エラー：ハイフンが無い", hasErrorTrueNotHaifun );
		// 後処理

		/**
		 * 数字が多い場合
		 */
		// 実行
		boolean hasErrorTrueOverNuber = v.isZipCode(valueTrueOverNuber, fieldName);
		// 検証
		assertTrue( "isZipCode:異常系エラー：数字が多い", hasErrorTrueOverNuber );
		// 後処理

		/**
		 * 数字が少ない場合
		 */
		// 実行
		boolean hasErrorTrueUnderNuber = v.isZipCode(valueTrueUnderNuber, fieldName);
		// 検証
		assertTrue( "isZipCode:異常系エラー：数字が少ない", hasErrorTrueUnderNuber );
		// 後処理

		/**
		 * 文字が入っている場合
		 */
		// 実行
		boolean hasErrorTrueString = v.isZipCode(valueTrueString, fieldName);
		// 検証
		assertTrue( "isZipCode:異常系エラー：文字が入っている", hasErrorTrueString );
		// 後処理
	}

	@Test
	public void isPhoneNumberTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrueNotHaifun = "11111111111";
		String valueTrueString = "111-1111-a111";
		String valueFalse = "11-1111-1111";
		String fieldName = "test";

		/**
		 * 値が正しい場合
		 */
		// 実行
		boolean hasErrorFalse = v.isPhoneNumber(valueFalse, fieldName);
		// 検証
		assertFalse( "isPhoneNumber:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * ハイフンが無い場合
		 */
		// 実行
		boolean hasErrorTrueNotHaifun = v.isPhoneNumber(valueTrueNotHaifun, fieldName);
		// 検証
		assertTrue( "isPhoneNumber:異常系エラー：ハイフンが無い", hasErrorTrueNotHaifun );
		// 後処理

		/**
		 * 文字が入っている場合
		 */
		// 実行
		boolean hasErrorTrueString = v.isZipCode(valueTrueString, fieldName);
		// 検証
		assertTrue( "isZipCode:異常系エラー：文字が入っている", hasErrorTrueString );
		// 後処理
	}

	@Test
	public void correctPeriodTest() {
		// 初期化
		Validator v = new Validator();
		String valueFromFalse = "2014-07-01";
		String valueToFalse = "2014-07-31";
		String valueFromTrueIllegalFormat = "2014/07/01";
		String valueToTrueIllegalFormat = "2014/07/01";
		String valueFromTrueIllegalPeriod = "2014-07-01";
		String valueToTrueIllegalPeriod = "2014-06-01";

		String fieldName = "test";

		/**
		 * 値が正しい日付形式、かつ期間が正常な場合
		 */
		// 実行
		boolean hasErrorFalse = v.correctPeriod(valueFromFalse, fieldName, valueToFalse, fieldName);
		// 検証
		assertFalse( "correctPeriod:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 値が正しい日付形式ではない場合(From)
		 */
		// 実行
		boolean hasErrorTrueIllegalFrom = v.correctPeriod(valueFromTrueIllegalFormat, fieldName, valueToFalse, fieldName);
		// 検証
		assertTrue( "correctPeriod:異常系エラー：Fromの形式が異常", hasErrorTrueIllegalFrom );
		// 後処理

		/**
		 * 値が正しい日付形式ではない場合(To)
		 */
		// 実行
		boolean hasErrorTrueIllegalTo = v.correctPeriod(valueFromFalse, fieldName, valueToTrueIllegalFormat, fieldName);
		// 検証
		assertTrue( "correctPeriod:異常系エラー：Toの形式が異常", hasErrorTrueIllegalTo );
		// 後処理

		/**
		 * 値が正しい日付形式ではない場合(From/To)
		 */
		// 実行
		boolean hasErrorTrueIllegalFromTo = v.correctPeriod(valueFromTrueIllegalFormat, fieldName, valueToTrueIllegalFormat, fieldName);
		// 検証
		assertTrue( "correctPeriod:異常系エラー：FromToの形式が異常", hasErrorTrueIllegalFromTo );
		// 後処理

		/**
		 * 値が正しい日付形式だが、期間が異常な場合
		 */
		// 実行
		boolean hasErrorTrueIllegalPeriod = v.correctPeriod(valueFromTrueIllegalPeriod, fieldName, valueToTrueIllegalPeriod, fieldName);
		// 検証
		assertTrue( "correctPeriod:異常系エラー：期間が異常", hasErrorTrueIllegalPeriod );
		// 後処理
	}

	@Test
	public void maxSizeIntTest() {
		// 初期化
		Validator v = new Validator();
		int valueTrue = 5;
		int valueFalse = 1;
		int maxSize = 2;
		String fieldName = "test";

		/**
		 * 最大値を超えていない場合
		 */
		// 実行
		boolean hasErrorFalse = v.maxSizeInt(valueFalse, maxSize, fieldName);
		// 検証
		assertFalse( "maxSizeInt:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 最大値を超えている場合
		 */
		// 実行
		boolean hasErrorTrue = v.maxSizeInt(valueTrue, maxSize, fieldName);
		// 検証
		assertTrue( "maxSizeInt:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void maxSizeStringTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "aaa";
		String valueFalse = "a";
		int maxSize = 2;
		String fieldName = "test";

		/**
		 * 最大文字数を超えていない場合
		 */
		// 実行
		boolean hasErrorFalse = v.maxSizeString(valueFalse, maxSize, fieldName);
		// 検証
		assertFalse( "maxSizeString:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 最大文字数を超えている場合
		 */
		// 実行
		boolean hasErrorTrue = v.maxSizeString(valueTrue, maxSize, fieldName);
		// 検証
		assertTrue( "maxSizeString:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void maxSizeImageTest() {
		// 初期化
		Validator v = new Validator();
		long maxSize = 500000;
		String fieldName = "test";

		/**
		 * 画像データが最大サイズ以内の場合
		 */
		long withinMaxSize = 100;
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(withinMaxSize);
		replay(valueFalse);
		// 実行
		boolean hasErrorFalse = v.maxSizeImage(valueFalse.getSize(), maxSize, fieldName);
		// 検証
		assertFalse( "maxSizeImage:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 画像データが最大サイズ以上の場合
		 */
		long overMaxSize = 10000000;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(overMaxSize);
		replay(valueTrue);
		// 実行
		boolean hasErrorTrue = v.maxSizeImage(valueTrue.getSize(), maxSize, fieldName);
		// 検証
		assertTrue( "maxSizeImage:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void minSizeIntTest() {
		// 初期化
		Validator v = new Validator();
		int valueTrue = 1;
		int valueFalse = 5;
		int minSize = 2;
		String fieldName = "test";

		/**
		 * 最小値を超えていない場合
		 */
		// 実行
		boolean hasErrorFalse = v.minSizeInt(valueFalse, minSize, fieldName);
		// 検証
		assertFalse( "minSizeInt:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 最小値を超えている場合
		 */
		// 実行
		boolean hasErrorTrue = v.minSizeInt(valueTrue, minSize, fieldName);
		// 検証
		assertTrue( "minSizeInt:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void minSizeStringTest() {
		// 初期化
		Validator v = new Validator();
		String valueTrue = "a";
		String valueFalse = "aaa";
		int minSize = 2;
		String fieldName = "test";

		/**
		 * 最小文字数を超えていない場合
		 */
		// 実行
		boolean hasErrorFalse = v.minSizeString(valueFalse, minSize, fieldName);
		// 検証
		assertFalse( "minSizeString:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * 最小文字数を超えている場合
		 */
		// 実行
		boolean hasErrorTrue = v.minSizeString(valueTrue, minSize, fieldName);
		// 検証
		assertTrue( "minSizeString:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void compareToStringsTest() {
		// 初期化
		Validator v = new Validator();
		String value = "test";
		String valueTrue = "bad";
		String valueFalse = "test";
		String fieldName = "test";

		/**
		 * ２つの文字列が同じ場合
		 */
		// 実行
		boolean hasErrorFalse = v.compareToStrings(value, valueFalse, fieldName);
		// 検証
		assertFalse( "compareToStrings:正常系エラー", hasErrorFalse );
		// 後処理

		/**
		 * ２つの文字列が異なる場合
		 */
		// 実行
		boolean hasErrorTrue = v.compareToStrings(value, valueTrue, fieldName);
		// 検証
		assertTrue( "compareToStrings:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void withinExpireDateTimeTest() {
		// 初期化
		Validator v = new Validator();
		Date expireDate = DateConversionUtility.stringToDate("2014-07-01");
		Date valueTrue = DateConversionUtility.stringToDate("2014-07-02");
		Date valueFalseSameDate = DateConversionUtility.stringToDate("2014-07-01");
		Date valueFalseWithinExpire = DateConversionUtility.stringToDate("2014-06-20");
		String fieldName = "test";

		/**
		 * 期限を超えていない（同じ日付）場合
		 */
		// 実行
		boolean hasErrorFalseSameDate = v.withinExpireDateTime(expireDate, valueFalseSameDate, fieldName);
		// 検証
		assertFalse( "withinExpireDateTime:正常系エラー", hasErrorFalseSameDate );
		// 後処理

		/**
		 * 期限を超えていない場合
		 */
		// 実行
		boolean hasErrorFalseWithinExpire = v.withinExpireDateTime(expireDate, valueFalseWithinExpire, fieldName);
		// 検証
		assertFalse( "withinExpireDateTime:正常系エラー", hasErrorFalseWithinExpire );
		// 後処理
		
		/**
		 * 期限を超えている場合
		 */
		// 実行
		boolean hasErrorTrue = v.withinExpireDateTime(expireDate, valueTrue, fieldName);
		// 検証
		assertTrue( "withinExpireDateTime:異常系エラー", hasErrorTrue );
		// 後処理
	}

	@Test
	public void getMessageTest() {
		// 初期化
		Validator v = new Validator();
		String message = "{$1}{$2}";
		String value = "test";
		String expected = value+ value;

		/**
		 * 正常にメッセージが取得出来、置換も出来た場合
		 */
		// 実行
		String resultMessage = v.getMessage(message, value, value);
		// 検証
		assertThat( "getMessage:正常系エラー", resultMessage , is( expected ));
		// 後処理

		/**
		 * パラメータ超過
		 */
		// 実行
		String resultMessageTooManyParameters = v.getMessage(message, value, value, value);
		// 検証
		assertThat( "getMessage:異常系エラー：パラメータ不足", resultMessageTooManyParameters , is( expected ));
		// 後処理
	}

	@Test
	public void addErrorMessageListTest() {
		// 初期化
		Validator v = new Validator();
		String value = "test";
		List<String> expected = new ArrayList<String>();
		expected.add(value);

		/**
		 * 正常にメッセージの追加が出来た場合
		 */
		// 実行
		v.addErrorMessageList(value);
		List<String> resultMessageList = v.getErrorMessageList();
		// 検証
		assertThat( "addErrorMessageList:正常系エラー", resultMessageList , is( expected ));
		// 後処理
	}

	@Test
	public void getErrorMessageListTest() {
		// 初期化
		Validator v = new Validator();
		String value = "test";
		List<String> expected = new ArrayList<String>();

		/**
		 * メッセージが何も追加されていない場合
		 */
		// 実行
		List<String> resultMessageListNull = v.getErrorMessageList();
		// 検証
		assertThat( "getErrorMessageList:正常系エラー：メッセージ無", resultMessageListNull , is( expected ));
		// 後処理

		/**
		 * メッセージが追加された場合
		 */
		// 実行
		v.addErrorMessageList(value);
		List<String> resultMessageListNotNull = v.getErrorMessageList();
		expected.add(value);
		// 検証
		assertThat( "getErrorMessageList:正常系エラー：メッセージ有", resultMessageListNotNull , is( expected ));
		// 後処理
	}
}
