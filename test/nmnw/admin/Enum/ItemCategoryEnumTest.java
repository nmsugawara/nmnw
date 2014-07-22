package nmnw.admin.Enum;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nmnw.admin.Enum.ItemCategoryEnum;

import org.junit.Test;

public class ItemCategoryEnumTest {

	@Test
	public void getCategoryCodeTest() {
		// 初期化
		List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
		String expected = "99";
		int num = itemCategoryList.size() -1;
		// 実行
		String actual = itemCategoryList.get(num).getCategoryCode();
		// 検証
		assertThat("getCategoryCode", actual, is(expected));
		// 後処理
	}

	@Test
	public void getEnumTest() {
		/**
		 * 存在するコードの場合
		 */
		// 初期化
		String expected = "その他";
		String code = "99";
		// 実行
		ItemCategoryEnum actual = ItemCategoryEnum.getEnum(code);
		// 検証
		assertThat("getEnum", actual.toString(), is(expected));
		// 後処理

		/**
		 * 存在しないコードの場合
		 */
		// 初期化
		String codeNotExist = "test";
		// 実行
		ItemCategoryEnum actualNotExist = ItemCategoryEnum.getEnum(codeNotExist);
		// 検証
		assertNull("getEnum:存在しないコード", actualNotExist);
		// 後処理
	}

}
