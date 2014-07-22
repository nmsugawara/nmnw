package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.nmnw.admin.dao.Item;

public class ItemTest {

	@Test
	public void itemTest() {
	}

	@Test
	public void setIdTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		item.setId(expected);
		// ����
		assertEquals("setId:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getIdTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("getId:����n�G���[", item.getId(), expected);
		// �㏈��
	}

	@Test
	public void setNameTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		item.setName(expected);
		// ����
		assertEquals("setName:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getNameTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("getName:����n�G���[", item.getName(), expected);
		// �㏈��
	}

	@Test
	public void getNameConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(item, value);
		// ����
		assertEquals("getNameConvertedHtml:����n�G���[", item.getNameConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setPriceTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_price");
		field.setAccessible(true);
		item.setPrice(expected);
		// ����
		assertEquals("setPrice:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getPriceTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_price");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertThat("getPrice:����n�G���[", item.getPrice(), is(expected));
		// �㏈��
	}

	@Test
	public void setCategoryTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_category");
		field.setAccessible(true);
		item.setCategory(expected);
		// ����
		assertEquals("setCategory:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getCategoryTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_category");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("getCategory:����n�G���[", item.getCategory(), expected);
		// �㏈��
	}

	@Test
	public void setImageUrlTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_imageUrl");
		field.setAccessible(true);
		item.setImageUrl(expected);
		// ����
		assertEquals("setImageUrl:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getImageUrlTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_imageUrl");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("setImageUrl:����n�G���[", item.getImageUrl(), expected);
		// �㏈��
	}

	@Test
	public void setExplanationTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		item.setExplanation(expected);
		// ����
		assertEquals("setExplanation:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getExplanationTest() throws Exception{
		// ������
		String expected = "test";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("setExplanation:����n�G���[", item.getExplanation(), expected);
		// �㏈��
	}

	@Test
	public void getExplanationConvertedHtmlTest() throws Exception{
		// ������
		String expected = "test<br>test";
		String value = "test\ntest";
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		field.set(item, value);
		// ����
		assertEquals("getExplanationConvertedHtml:����n�G���[", item.getExplanationConvertedHtml(), expected);
		// �㏈��
	}

	@Test
	public void setSalesPeriodFromTest() throws Exception{
		// ������
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_salesPeriodFrom");
		field.setAccessible(true);
		item.setSalesPeriodFrom(expected);
		// ����
		assertEquals("setSalesPeriodFrom:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getSalesPeriodFromTest() throws Exception{
		// ������
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_salesPeriodFrom");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("setSalesPeriodFrom:����n�G���[", item.getSalesPeriodFrom(), expected);
		// �㏈��
	}

	@Test
	public void setSalesPeriodToTest() throws Exception{
		// ������
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_salesPeriodTo");
		field.setAccessible(true);
		item.setSalesPeriodTo(expected);
		// ����
		assertEquals("setSalesPeriodTo:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getSalesPeriodToTest() throws Exception{
		// ������
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_salesPeriodTo");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("setSalesPeriodTo:����n�G���[", item.getSalesPeriodTo(), expected);
		// �㏈��
	}

	@Test
	public void setStockTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_stock");
		field.setAccessible(true);
		item.setStock(expected);
		// ����
		assertEquals("setStock:����n�G���[", field.get(item), expected);
		// �㏈��
	}

	@Test
	public void getStockTest() throws Exception{
		// ������
		int expected = 1;
		Item item = new Item();
		// ���s
		Field field = item.getClass().getDeclaredField("_stock");
		field.setAccessible(true);
		field.set(item, expected);
		// ����
		assertEquals("getStock:����n�G���[", item.getStock(), expected);
		// �㏈��
	}
}
