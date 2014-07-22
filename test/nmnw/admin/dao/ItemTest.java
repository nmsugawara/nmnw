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
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		item.setId(expected);
		// 検証
		assertEquals("setId:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getIdTest() throws Exception{
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_id");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("getId:正常系エラー", item.getId(), expected);
		// 後処理
	}

	@Test
	public void setNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		item.setName(expected);
		// 検証
		assertEquals("setName:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getNameTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("getName:正常系エラー", item.getName(), expected);
		// 後処理
	}

	@Test
	public void getNameConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_name");
		field.setAccessible(true);
		field.set(item, value);
		// 検証
		assertEquals("getNameConvertedHtml:正常系エラー", item.getNameConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_price");
		field.setAccessible(true);
		item.setPrice(expected);
		// 検証
		assertEquals("setPrice:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getPriceTest() throws Exception{
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_price");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertThat("getPrice:正常系エラー", item.getPrice(), is(expected));
		// 後処理
	}

	@Test
	public void setCategoryTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_category");
		field.setAccessible(true);
		item.setCategory(expected);
		// 検証
		assertEquals("setCategory:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getCategoryTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_category");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("getCategory:正常系エラー", item.getCategory(), expected);
		// 後処理
	}

	@Test
	public void setImageUrlTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_imageUrl");
		field.setAccessible(true);
		item.setImageUrl(expected);
		// 検証
		assertEquals("setImageUrl:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getImageUrlTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_imageUrl");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("setImageUrl:正常系エラー", item.getImageUrl(), expected);
		// 後処理
	}

	@Test
	public void setExplanationTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		item.setExplanation(expected);
		// 検証
		assertEquals("setExplanation:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getExplanationTest() throws Exception{
		// 初期化
		String expected = "test";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("setExplanation:正常系エラー", item.getExplanation(), expected);
		// 後処理
	}

	@Test
	public void getExplanationConvertedHtmlTest() throws Exception{
		// 初期化
		String expected = "test<br>test";
		String value = "test\ntest";
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_explanation");
		field.setAccessible(true);
		field.set(item, value);
		// 検証
		assertEquals("getExplanationConvertedHtml:正常系エラー", item.getExplanationConvertedHtml(), expected);
		// 後処理
	}

	@Test
	public void setSalesPeriodFromTest() throws Exception{
		// 初期化
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_salesPeriodFrom");
		field.setAccessible(true);
		item.setSalesPeriodFrom(expected);
		// 検証
		assertEquals("setSalesPeriodFrom:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getSalesPeriodFromTest() throws Exception{
		// 初期化
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_salesPeriodFrom");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("setSalesPeriodFrom:正常系エラー", item.getSalesPeriodFrom(), expected);
		// 後処理
	}

	@Test
	public void setSalesPeriodToTest() throws Exception{
		// 初期化
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_salesPeriodTo");
		field.setAccessible(true);
		item.setSalesPeriodTo(expected);
		// 検証
		assertEquals("setSalesPeriodTo:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getSalesPeriodToTest() throws Exception{
		// 初期化
		String value = "2014-07-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_salesPeriodTo");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("setSalesPeriodTo:正常系エラー", item.getSalesPeriodTo(), expected);
		// 後処理
	}

	@Test
	public void setStockTest() throws Exception{
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_stock");
		field.setAccessible(true);
		item.setStock(expected);
		// 検証
		assertEquals("setStock:正常系エラー", field.get(item), expected);
		// 後処理
	}

	@Test
	public void getStockTest() throws Exception{
		// 初期化
		int expected = 1;
		Item item = new Item();
		// 実行
		Field field = item.getClass().getDeclaredField("_stock");
		field.setAccessible(true);
		field.set(item, expected);
		// 検証
		assertEquals("getStock:正常系エラー", item.getStock(), expected);
		// 後処理
	}
}
