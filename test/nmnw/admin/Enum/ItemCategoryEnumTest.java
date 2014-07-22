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
		// ������
		List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
		String expected = "99";
		int num = itemCategoryList.size() -1;
		// ���s
		String actual = itemCategoryList.get(num).getCategoryCode();
		// ����
		assertThat("getCategoryCode", actual, is(expected));
		// �㏈��
	}

	@Test
	public void getEnumTest() {
		/**
		 * ���݂���R�[�h�̏ꍇ
		 */
		// ������
		String expected = "���̑�";
		String code = "99";
		// ���s
		ItemCategoryEnum actual = ItemCategoryEnum.getEnum(code);
		// ����
		assertThat("getEnum", actual.toString(), is(expected));
		// �㏈��

		/**
		 * ���݂��Ȃ��R�[�h�̏ꍇ
		 */
		// ������
		String codeNotExist = "test";
		// ���s
		ItemCategoryEnum actualNotExist = ItemCategoryEnum.getEnum(codeNotExist);
		// ����
		assertNull("getEnum:���݂��Ȃ��R�[�h", actualNotExist);
		// �㏈��
	}

}
