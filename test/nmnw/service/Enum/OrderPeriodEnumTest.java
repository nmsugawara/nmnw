package nmnw.service.Enum;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nmnw.service.Enum.OrderPeriodEnum;

import org.junit.Test;

public class OrderPeriodEnumTest {

	@Test
	public void getCategoryCodeTest() {
		// 初期化
		List<OrderPeriodEnum> itemCategoryList = new ArrayList<OrderPeriodEnum>(Arrays.asList(OrderPeriodEnum.values()));
		String expected = "-365";
		int num = itemCategoryList.size() -1;
		// 実行
		String actual = itemCategoryList.get(num).getPeriodCode();
		// 検証
		assertThat("getPeriodCode", actual, is(expected));
		// 後処理
	}

	@Test
	public void getEnumTest() {
		/**
		 * 存在するコードの場合
		 */
		// 初期化
		String expected = "過去1年以内の注文";
		String code = "-365";
		// 実行
		OrderPeriodEnum actual = OrderPeriodEnum.getEnum(code);
		// 検証
		assertThat("getEnum", actual.toString(), is(expected));
		// 後処理

		/**
		 * 存在しないコードの場合
		 */
		// 初期化
		String codeNotExist = "test";
		// 実行
		OrderPeriodEnum actualNotExist = OrderPeriodEnum.getEnum(codeNotExist);
		// 検証
		assertNull("getEnum:存在しないコード", actualNotExist);
		// 後処理
	}

}
