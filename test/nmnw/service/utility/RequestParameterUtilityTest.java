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
		 * 値がある場合
		 */
		// 初期化
		String value = "test";
		// 実行
		Boolean actual = RequestParameterUtility.isEmptyParam(value);
		// 検証
		assertFalse("isEmptyParam:値がある場合", actual);
		// 後処理

		/**
		 * 値が無い場合
		 */
		// 初期化
		String valueNoData = "";
		// 実行
		Boolean actualNoData = RequestParameterUtility.isEmptyParam(valueNoData);
		// 検証
		assertTrue("isEmptyParam:値が無い場合", actualNoData);
		// 後処理

		/**
		 * 値がnullの場合
		 */
		// 初期化
		String valueNull = null;
		// 実行
		Boolean actualNull = RequestParameterUtility.isEmptyParam(valueNull);
		// 検証
		assertTrue("isEmptyParam:値がnullの場合", actualNull);
		// 後処理
	}

	@Test
	public void isEmptyImageTest() {
		/**
		 * 画像がある場合
		 */
		// 初期化
		Part value = createMock(Part.class);
		expect(value.getSize()).andReturn(IMAGE_EXIST);
		replay(value);
		// 実行
		Boolean actual = RequestParameterUtility.isEmptyImage(value);
		// 検証
		assertFalse("isEmptyImage:画像がある場合", actual);
		// 後処理

		/**
		 * 画像が無い場合
		 */
		// 初期化
		Part valueNoImage = createMock(Part.class);
		expect(valueNoImage.getSize()).andReturn(IMAGE_NOT_EXIST);
		replay(valueNoImage);
		// 実行
		Boolean actualNoImage = RequestParameterUtility.isEmptyImage(valueNoImage);
		// 検証
		assertTrue("isEmptyImage:画像が無い場合", actualNoImage);
		// 後処理
	}
}
