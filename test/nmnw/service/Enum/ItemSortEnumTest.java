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
		// ������
		List<ItemSortEnum> itemCategoryList = new ArrayList<ItemSortEnum>(Arrays.asList(ItemSortEnum.values()));
		String expected = "sales_period_from";
		int num = itemCategoryList.size() -1;
		// ���s
		String actual = itemCategoryList.get(num).getSortCode();
		// ����
		assertThat("getSortCode", actual, is(expected));
		// �㏈��
	}

	@Test
	public void getEnumTest() {
		/**
		 * ���݂���R�[�h�̏ꍇ
		 */
		// ������
		String expected = "�Â���";
		String code = "sales_period_from";
		// ���s
		ItemSortEnum actual = ItemSortEnum.getEnum(code);
		// ����
		assertThat("getEnum", actual.toString(), is(expected));
		// �㏈��

		/**
		 * ���݂��Ȃ��R�[�h�̏ꍇ
		 */
		// ������
		String codeNotExist = "test";
		// ���s
		ItemSortEnum actualNotExist = ItemSortEnum.getEnum(codeNotExist);
		// ����
		assertNull("getEnum:���݂��Ȃ��R�[�h", actualNotExist);
		// �㏈��
	}

}
