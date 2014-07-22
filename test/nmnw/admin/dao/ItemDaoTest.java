package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.nmnw.admin.dao.Item;
import com.nmnw.admin.dao.ItemDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class ItemDaoTest {

	@Test
	public void selectByItemIdTest() throws Exception{
		/**
		 * ���݂��鏤�iID
		 */
		// ������
		int itemIdExist = 18;
		ItemDao itemDao = new ItemDao();
		Item expected = new Item();
		expected.setId(18);
		expected.setName("LADY2");
		expected.setPrice(12);
		expected.setCategory("2");
		expected.setImageUrl("item201407031948100132.jpg");
		expected.setExplanation("test");
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate("2014-07-08"));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate("2014-07-31"));
		expected.setStock(1);
		// ���s
		Item actual = itemDao.selectByItemId(itemIdExist);
		// ����
		assertThat("selectByItemId:itemId", actual.getId(), is(expected.getId()));
		assertThat("selectByItemId:itemName", actual.getName(), is(expected.getName()));
		assertThat("selectByItemId:itemPrice", actual.getPrice(), is(expected.getPrice()));
		assertThat("selectByItemId:itemCategory", actual.getCategory(), is(expected.getCategory()));
		assertThat("selectByItemId:itemImageUrl", actual.getImageUrl(), is(expected.getImageUrl()));
		assertThat("selectByItemId:itemExplanation", actual.getExplanation(), is(expected.getExplanation()));
		assertThat("selectByItemId:itemSalesPeriodFrom", actual.getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("selectByItemId:itemSalesPeriodTo", actual.getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("selectByItemId:itemStock", actual.getStock(), is(expected.getStock()));
		// �㏈��
		
		/**
		 * ���݂��Ȃ����iID
		 */
		// ������
		int itemIdNotExist = 9999;
		// ���s
		Item actualNotExist = itemDao.selectByItemId(itemIdNotExist);
		// ����
		assertThat("selectByItemId:itemId:0", actualNotExist.getId(), is(0));
		assertThat("selectByItemId:itemName:null", actualNotExist.getName(), is(""));
		assertThat("selectByItemId:itemPrice:0", actualNotExist.getPrice(), is(0));
		assertThat("selectByItemId:itemCategory:null", actualNotExist.getCategory(), is(""));
		assertThat("selectByItemId:itemImageUrl:null", actualNotExist.getImageUrl(), is(""));
		assertThat("selectByItemId:itemExplanation:null", actualNotExist.getExplanation(), is(""));
		assertNull("selectByItemId:itemSalesPeriodFrom:null", actualNotExist.getSalesPeriodFrom());
		assertNull("selectByItemId:itemSalesPeriodTo:null", actualNotExist.getSalesPeriodTo());
		assertThat("selectByItemId:itemStock:0", actualNotExist.getStock(), is(0));
		// �㏈��
	}

	@Test
	public void selectBySearchTest() throws Exception{
		/**
		 * ���������L:
		 */
		// ������
		int itemIdExist = 18;
		String itemNameExist = "DY";
		String itemCategoryExist = "2";
		String itemFromExist = "2014-07-12";
		String itemToExist = "2014-07-19";

		ItemDao itemDao = new ItemDao();
		Item expected = new Item();
		expected.setId(18);
		expected.setName("LADY2");
		expected.setPrice(12);
		expected.setCategory("2");
		expected.setImageUrl("item201407031948100132.jpg");
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate("2014-07-08"));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate("2014-07-31"));
		expected.setStock(1);
		// ���s
		List<Item> actual = itemDao.selectBySearch(itemIdExist, itemNameExist, itemCategoryExist, itemFromExist, itemToExist);
		// ����
		assertThat("selectBySearch:itemId", actual.get(0).getId(), is(expected.getId()));
		assertThat("selectBySearch:itemName", actual.get(0).getName(), is(expected.getName()));
		assertThat("selectBySearch:itemPrice", actual.get(0).getPrice(), is(expected.getPrice()));
		assertThat("selectBySearch:itemCategory", actual.get(0).getCategory(), is(expected.getCategory()));
		assertThat("selectBySearch:itemImageUrl", actual.get(0).getImageUrl(), is(expected.getImageUrl()));
		assertThat("selectBySearch:itemSalesPeriodFrom", actual.get(0).getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("selectBySearch:itemSalesPeriodTo", actual.get(0).getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("selectBySearch:itemStock", actual.get(0).getStock(), is(expected.getStock()));
		// �㏈��

		/**
		 * ����������:�󕶎�
		 */
		// ������
		int itemIdNotExist = -1;
		String itemNameNotExist = "";
		String itemCategoryNotExist = "0";
		String itemFromNotExist = "";
		String itemToNotExist = "";

		Connection connection = DbConnector.getConnection();
		String sql = "select count(*) as count from item";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		int expectedListCount = 0;
		while (result.next()) {
			expectedListCount = result.getInt("count");
		}

		// ���s
		List<Item> actualNotExsist = itemDao.selectBySearch(itemIdNotExist, itemNameNotExist, itemCategoryNotExist, itemFromNotExist, itemToNotExist);
		// ����
		assertThat("selectBySearch:������", actualNotExsist.size(), is(expectedListCount));

		/**
		 * ����������:null
		 */
		// ������
		int itemIdNull = -1;
		String itemNameNull = null;
		String itemCategoryNull = null;
		String itemFromNull = null;
		String itemToNull = null;

		// ���s
		List<Item> actualNull = itemDao.selectBySearch(itemIdNull, itemNameNull, itemCategoryNull, itemFromNull, itemToNull);
		// ����
		assertThat("selectBySearch:������:null", actualNull.size(), is(expectedListCount));

		/**
		 * ���������L�FFrom�̂�
		 */
		// ������
		int itemIdRuleFrom = -1;
		String itemNameRuleFrom = null;
		String itemCategoryRuleFrom = null;
		String itemFromRuleFrom = "2014-09-01";
		String itemToRuleFrom = null;

		String sqlRuleFrom = "select count(*) as count from item where sales_period_to >= '" + itemFromRuleFrom + "'";
		PreparedStatement statementRuleFrom = connection.prepareStatement(sqlRuleFrom);
		ResultSet resultRuleFrom = statementRuleFrom.executeQuery();
		int expectedListCountRuleFrom = 0;
		while (resultRuleFrom.next()) {
			expectedListCountRuleFrom = resultRuleFrom.getInt("count");
		}

		// ���s
		List<Item> actualRuleFrom = itemDao.selectBySearch(itemIdRuleFrom, itemNameRuleFrom, itemCategoryRuleFrom, itemFromRuleFrom, itemToRuleFrom);
		// ����
		assertThat("selectBySearch:�����L:From�̂�", actualRuleFrom.size(), is(expectedListCountRuleFrom));

		/**
		 * ���������L�FTo�̂�
		 */
		// ������
		int itemIdRuleTo = -1;
		String itemNameRuleTo = null;
		String itemCategoryRuleTo = null;
		String itemFromRuleTo = null;
		String itemToRuleTo = "2014-09-01";

		String sqlRuleTo = "select count(*) as count from item where sales_period_from <= '" + itemToRuleTo + "'";
		PreparedStatement statementRuleTo = connection.prepareStatement(sqlRuleTo);
		ResultSet resultRuleTo = statementRuleTo.executeQuery();
		int expectedListCountRuleTo = 0;
		while (resultRuleTo.next()) {
			expectedListCountRuleTo = resultRuleTo.getInt("count");
		}

		// ���s
		List<Item> actualRuleTo = itemDao.selectBySearch(itemIdRuleTo, itemNameRuleTo, itemCategoryRuleTo, itemFromRuleTo, itemToRuleTo);
		// ����
		assertThat("selectBySearch:�����L:To�̂�", actualRuleTo.size(), is(expectedListCountRuleTo));

	}

	@Test
	public void insertTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		ItemDao itemDao = new ItemDao();
		Item expected = new Item();
		expected.setName("test");
		expected.setPrice(1000);
		expected.setCategory("1");
		expected.setImageUrl("test.jpg");
		expected.setExplanation("test\ntest");
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate("2014-07-01"));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate("2014-07-02"));
		expected.setStock(10);
		// ���s
		int itemId = itemDao.insert(expected);
		Item actual = itemDao.selectByItemId(itemId);
		// ����
		assertThat("insert:itemName", actual.getName(), is(expected.getName()));
		assertThat("insert:itemPrice", actual.getPrice(), is(expected.getPrice()));
		assertThat("insert:itemCategory", actual.getCategory(), is(expected.getCategory()));
		assertThat("insert:itemExplanation", actual.getExplanation(), is(expected.getExplanation()));
		assertThat("insert:itemImageUrl", actual.getImageUrl(), is(expected.getImageUrl()));
		assertThat("insert:itemSalesPeriodFrom", actual.getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("insert:itemSalesPeriodTo", actual.getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("insert:itemStock", actual.getStock(), is(expected.getStock()));
		// �㏈��

		/**
		 * �ُ�n
		 */
		// ������
		Item expectedNoData = new Item();
		// ���s
		int itemIdNoData = itemDao.insert(expectedNoData);
		Item actualNoData = itemDao.selectByItemId(itemIdNoData);
		// ����
		assertThat("insert:itemName:NoData", actualNoData.getName(), is(expectedNoData.getName()));
		assertThat("insert:itemPrice:NoData", actualNoData.getPrice(), is(expectedNoData.getPrice()));
		assertThat("insert:itemCategory:NoData", actualNoData.getCategory(), is(expectedNoData.getCategory()));
		assertThat("insert:itemExplanation:NoData", actualNoData.getExplanation(), is(expectedNoData.getExplanation()));
		assertThat("insert:itemImageUrl:NoData", actualNoData.getImageUrl(), is(expectedNoData.getImageUrl()));
		assertThat("insert:itemSalesPeriodFrom:NoData", actualNoData.getSalesPeriodFrom(), is(expectedNoData.getSalesPeriodFrom()));
		assertThat("insert:itemSalesPeriodTo:NoData", actualNoData.getSalesPeriodTo(), is(expectedNoData.getSalesPeriodTo()));
		assertThat("insert:itemStock:NoData", actualNoData.getStock(), is(expectedNoData.getStock()));
		// �㏈��
	}

	@Test
	public void updateTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		ItemDao itemDao = new ItemDao();
		Random random = new Random();
		Item expected = new Item();
		expected.setId(21);
		expected.setName(String.valueOf(random.nextInt(50)));
		expected.setPrice(random.nextInt(50));
		expected.setCategory(String.valueOf(random.nextInt(50)));
		expected.setImageUrl(String.valueOf(random.nextInt(50)));
		expected.setExplanation(String.valueOf(random.nextInt(50)));
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate("2014-07-" + String.valueOf(random.nextInt(30))));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate("2014-07-" + String.valueOf(random.nextInt(30))));
		expected.setStock(random.nextInt(50));
		// ���s
		int itemId = itemDao.update(expected);
		Item actual = itemDao.selectByItemId(itemId);
		// ����
		assertThat("update:itemName", actual.getName(), is(expected.getName()));
		assertThat("update:itemPrice", actual.getPrice(), is(expected.getPrice()));
		assertThat("update:itemCategory", actual.getCategory(), is(expected.getCategory()));
		assertThat("update:itemExplanation", actual.getExplanation(), is(expected.getExplanation()));
		assertThat("update:itemImageUrl", actual.getImageUrl(), is(expected.getImageUrl()));
		assertThat("update:itemSalesPeriodFrom", actual.getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("update:itemSalesPeriodTo", actual.getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("update:itemStock", actual.getStock(), is(expected.getStock()));
		// �㏈��
	}
}
