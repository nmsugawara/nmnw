package nmnw.service.Enum;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nmnw.service.Enum.ItemSortEnum;

import org.junit.Test;

public class ItemSortEnumTest {

	@Test
	public void getCategoryCodeTest() {
		// 初期化
		List<ItemSortEnum> itemCategoryList = new ArrayList<ItemSortEnum>(Arrays.asList(ItemSortEnum.values()));
		String expected = "sales_period_from";
		int num = itemCategoryList.size() -1;
		// 実行
		String actual = itemCategoryList.get(num).getSortCode();
		// 検証
		assertThat("getSortCode", actual, is(expected));
		// 後処理
	}

	@Test
	public void getEnumTest() {
		/**
		 * 存在するコードの場合
		 */
		// 初期化
		String expected = "古い順";
		String code = "sales_period_from";
		// 実行
		ItemSortEnum actual = ItemSortEnum.getEnum(code);
		// 検証
		assertThat("getEnum", actual.toString(), is(expected));
		// 後処理

		/**
		 * 存在しないコードの場合
		 */
		// 初期化
		String codeNotExist = "test";
		// 実行
		ItemSortEnum actualNotExist = ItemSortEnum.getEnum(codeNotExist);
		// 検証
		assertNull("getEnum:存在しないコード", actualNotExist);
		// 後処理
	}

}
