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
