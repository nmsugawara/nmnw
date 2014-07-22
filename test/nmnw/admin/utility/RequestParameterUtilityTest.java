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
		// 初期化
		String valueTrueNull = null;
		String valueTrueNoString = "";
		String valueFalse = "test";

		/**
		 * 値が空でない場合
		 */
		// 実行
		boolean actualFalse = RequestParameterUtility.isEmptyParam(valueFalse);
		// 検証
		assertFalse( "isEmptyParam:正常系エラー:nullではない", actualFalse );
		// 後処理

		/**
		 * 値が0文字の場合
		 */
		// 実行
		boolean actualTrueNoString = RequestParameterUtility.isEmptyParam(valueTrueNoString);
		// 検証
		assertTrue( "isEmptyParam:正常系エラー:0文字", actualTrueNoString );
		// 後処理

		/**
		 * 値がnullの場合
		 */
		// 実行
		boolean actualTrueNull = RequestParameterUtility.isEmptyParam(valueTrueNull);
		// 検証
		assertTrue( "isEmptyParam:正常系エラー:null", actualTrueNull );
		// 後処理
	}

	@Test
	public void isEmptyImageTest() {

		/**
		 * 画像データが無い場合
		 */
		// 初期化
		long notExistSize = 0;
		Part valueTrue = createMock(Part.class);
		expect(valueTrue.getSize()).andReturn(notExistSize);
		replay(valueTrue);
		// 実行
		boolean actualTrue = RequestParameterUtility.isEmptyImage(valueTrue);
		// 検証
		assertTrue( "isEmptyImage:正常系エラー:画像無", actualTrue );
		// 後処理

		/**
		 * 画像データが有る場合
		 */
		// 初期化
		long existSize = 1;		
		Part valueFalse = createMock(Part.class);
		expect(valueFalse.getSize()).andReturn(existSize);
		replay(valueFalse);
		// 実行
		boolean actualFalse = RequestParameterUtility.isEmptyImage(valueFalse);
		// 検証
		assertFalse( "isEmptyImage:正常系エラー:画像有", actualFalse );
		// 後処理
	}
}
