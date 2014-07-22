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
		// ������
		List<OrderPeriodEnum> itemCategoryList = new ArrayList<OrderPeriodEnum>(Arrays.asList(OrderPeriodEnum.values()));
		String expected = "-365";
		int num = itemCategoryList.size() -1;
		// ���s
		String actual = itemCategoryList.get(num).getPeriodCode();
		// ����
		assertThat("getPeriodCode", actual, is(expected));
		// �㏈��
	}

	@Test
	public void getEnumTest() {
		/**
		 * ���݂���R�[�h�̏ꍇ
		 */
		// ������
		String expected = "�ߋ�1�N�ȓ��̒���";
		String code = "-365";
		// ���s
		OrderPeriodEnum actual = OrderPeriodEnum.getEnum(code);
		// ����
		assertThat("getEnum", actual.toString(), is(expected));
		// �㏈��

		/**
		 * ���݂��Ȃ��R�[�h�̏ꍇ
		 */
		// ������
		String codeNotExist = "test";
		// ���s
		OrderPeriodEnum actualNotExist = OrderPeriodEnum.getEnum(codeNotExist);
		// ����
		assertNull("getEnum:���݂��Ȃ��R�[�h", actualNotExist);
		// �㏈��
	}

}
