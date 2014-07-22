package nmnw.service.dao;

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

import com.nmnw.service.constant.ConfigConstants;
import com.nmnw.service.dao.Item;
import com.nmnw.service.dao.ItemDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class ItemDaoTest {
	public static final int ID = 18;
	public static final String NAME = "LADY2";
	public static final int PRICE = 12;
	public static final String CATEGORY = "2";
	public static final String IMAGE_URL = "item201407031948100132.jpg";
	public static final String EXPLANATION = "test";
	public static final String SALES_PERIOD_FROM = "2014-07-08";
	public static final String SALES_PERIOD_TO = "2014-07-31";
	public static final int STOCK = 1;

	public static final int NOT_EXIST_ID = 9999;
	public static final String SORT = "sales_period_from desc";

	public static final String NAME_NO_INPUT = "";
	public static final String CATEGORY_NO_INPUT = "0";
	public static final String SALES_PERIOD_FROM_NO_INPUT = "";
	public static final String SALES_PERIOD_TO_NO_INPUT = "";
	public static final String SORT_NOT_SELECT = "0";

	public static final String NAME_NULL = null;
	public static final String CATEGORY_NULL = null;
	public static final String SALES_PERIOD_FROM_NULL = null;
	public static final String SALES_PERIOD_TO_NULL = null;
	public static final String SORT_NULL = null;

	
	@Test
	public void selectByItemIdTest() throws Exception{
		/**
		 * 存在する商品ID
		 */
		// 初期化
		int itemIdExist = ID;
		ItemDao itemDao = new ItemDao();
		Item expected = new Item();
		expected.setId(ID);
		expected.setName(NAME);
		expected.setPrice(PRICE);
		expected.setCategory(CATEGORY);
		expected.setImageUrl(IMAGE_URL);
		expected.setExplanation(EXPLANATION);
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate(SALES_PERIOD_FROM));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate(SALES_PERIOD_TO));
		expected.setStock(STOCK);
		// 実行
		Item actual = itemDao.selectByItemId(itemIdExist);
		// 検証
		assertThat("selectByItemId:itemId", actual.getId(), is(expected.getId()));
		assertThat("selectByItemId:itemName", actual.getName(), is(expected.getName()));
		assertThat("selectByItemId:itemPrice", actual.getPrice(), is(expected.getPrice()));
		assertThat("selectByItemId:itemCategory", actual.getCategory(), is(expected.getCategory()));
		assertThat("selectByItemId:itemImageUrl", actual.getImageUrl(), is(expected.getImageUrl()));
		assertThat("selectByItemId:itemExplanation", actual.getExplanation(), is(expected.getExplanation()));
		assertThat("selectByItemId:itemSalesPeriodFrom", actual.getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("selectByItemId:itemSalesPeriodTo", actual.getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("selectByItemId:itemStock", actual.getStock(), is(expected.getStock()));
		// 後処理
		
		/**
		 * 存在しない商品ID
		 */
		// 初期化
		int itemIdNotExist = NOT_EXIST_ID;
		// 実行
		Item actualNotExist = itemDao.selectByItemId(itemIdNotExist);
		// 検証
		assertThat("selectByItemId:itemId:0", actualNotExist.getId(), is(ConfigConstants.NULL_INT));
		assertThat("selectByItemId:itemName:null", actualNotExist.getName(), is(""));
		assertThat("selectByItemId:itemPrice:0", actualNotExist.getPrice(), is(0));
		assertThat("selectByItemId:itemCategory:null", actualNotExist.getCategory(), is(""));
		assertThat("selectByItemId:itemImageUrl:null", actualNotExist.getImageUrl(), is(""));
		assertThat("selectByItemId:itemExplanation:null", actualNotExist.getExplanation(), is(""));
		assertNull("selectByItemId:itemSalesPeriodFrom:null", actualNotExist.getSalesPeriodFrom());
		assertNull("selectByItemId:itemSalesPeriodTo:null", actualNotExist.getSalesPeriodTo());
		assertThat("selectByItemId:itemStock:0", actualNotExist.getStock(), is(0));
		// 後処理
	}

	@Test
	public void selectBySearchTest() throws Exception{
		/**
		 * 検索条件有:
		 */
		// 初期化
		String itemNameExist = NAME;
		String itemCategoryExist = CATEGORY;
		String itemFromExist = SALES_PERIOD_FROM;
		String itemToExist = SALES_PERIOD_TO;

		ItemDao itemDao = new ItemDao();
		Item expected = new Item();
		expected.setId(ID);
		expected.setName(itemNameExist);
		expected.setPrice(PRICE);
		expected.setCategory(itemCategoryExist);
		expected.setImageUrl(IMAGE_URL);
		expected.setExplanation(EXPLANATION);
		expected.setSalesPeriodFrom(DateConversionUtility.stringToDate(itemFromExist));
		expected.setSalesPeriodTo(DateConversionUtility.stringToDate(itemToExist));
		expected.setStock(STOCK);
		// 実行
		List<Item> actual = itemDao.selectBySearch(itemNameExist, itemCategoryExist, itemFromExist, itemToExist, SORT);
		// 検証
		assertThat("selectBySearch:itemId", actual.get(0).getId(), is(expected.getId()));
		assertThat("selectBySearch:itemName", actual.get(0).getName(), is(expected.getName()));
		assertThat("selectBySearch:itemPrice", actual.get(0).getPrice(), is(expected.getPrice()));
		assertThat("selectBySearch:itemCategory", actual.get(0).getCategory(), is(expected.getCategory()));
		assertThat("selectBySearch:itemImageUrl", actual.get(0).getImageUrl(), is(expected.getImageUrl()));
		assertThat("selectBySearch:itemSalesPeriodFrom", actual.get(0).getSalesPeriodFrom(), is(expected.getSalesPeriodFrom()));
		assertThat("selectBySearch:itemSalesPeriodTo", actual.get(0).getSalesPeriodTo(), is(expected.getSalesPeriodTo()));
		assertThat("selectBySearch:itemStock", actual.get(0).getStock(), is(expected.getStock()));
		// 後処理

		/**
		 * 検索条件無:空文字
		 */
		// 初期化
		String itemNameNotExist = NAME_NO_INPUT;
		String itemCategoryNotExist = CATEGORY_NO_INPUT;
		String itemFromNotExist = SALES_PERIOD_FROM_NO_INPUT;
		String itemToNotExist = SALES_PERIOD_TO_NO_INPUT;

		Connection connection = DbConnector.getConnection();
		String sql = "select count(*) as count from item";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		int expectedListCount = 0;
		while (result.next()) {
			expectedListCount = result.getInt("count");
		}

		// 実行
		List<Item> actualNotExsist = itemDao.selectBySearch(itemNameNotExist, itemCategoryNotExist, itemFromNotExist, itemToNotExist, SORT_NOT_SELECT);
		// 検証
		assertThat("selectBySearch:条件無", actualNotExsist.size(), is(expectedListCount));

		/**
		 * 検索条件無:null
		 */
		// 初期化
		String itemNameNull = NAME_NO_INPUT;
		String itemCategoryNull = CATEGORY_NO_INPUT;
		String itemFromNull = SALES_PERIOD_FROM_NO_INPUT;
		String itemToNull = SALES_PERIOD_TO_NO_INPUT;

		// 実行
		List<Item> actualNull = itemDao.selectBySearch(itemNameNull, itemCategoryNull, itemFromNull, itemToNull, SORT_NULL);
		// 検証
		assertThat("selectBySearch:条件無:null", actualNull.size(), is(expectedListCount));

		/**
		 * 検索条件有：Fromのみ
		 */
		// 初期化
		String itemNameRuleFrom = NAME_NULL;
		String itemCategoryRuleFrom =CATEGORY_NULL;
		String itemFromRuleFrom = SALES_PERIOD_FROM;
		String itemToRuleFrom = SALES_PERIOD_TO_NULL;

		String sqlRuleFrom = "select count(*) as count from item where sales_period_to >= '" + itemFromRuleFrom + "'";
		PreparedStatement statementRuleFrom = connection.prepareStatement(sqlRuleFrom);
		ResultSet resultRuleFrom = statementRuleFrom.executeQuery();
		int expectedListCountRuleFrom = 0;
		while (resultRuleFrom.next()) {
			expectedListCountRuleFrom = resultRuleFrom.getInt("count");
		}

		// 実行
		List<Item> actualRuleFrom = itemDao.selectBySearch(itemNameRuleFrom, itemCategoryRuleFrom, itemFromRuleFrom, itemToRuleFrom, SORT_NULL);
		// 検証
		assertThat("selectBySearch:条件有:Fromのみ", actualRuleFrom.size(), is(expectedListCountRuleFrom));

		/**
		 * 検索条件有：Toのみ
		 */
		// 初期化
		String itemNameRuleTo = NAME_NULL;
		String itemCategoryRuleTo = CATEGORY_NULL;
		String itemFromRuleTo = SALES_PERIOD_FROM_NULL;
		String itemToRuleTo = SALES_PERIOD_TO;

		String sqlRuleTo = "select count(*) as count from item where sales_period_from <= '" + itemToRuleTo + "'";
		PreparedStatement statementRuleTo = connection.prepareStatement(sqlRuleTo);
		ResultSet resultRuleTo = statementRuleTo.executeQuery();
		int expectedListCountRuleTo = 0;
		while (resultRuleTo.next()) {
			expectedListCountRuleTo = resultRuleTo.getInt("count");
		}

		// 実行
		List<Item> actualRuleTo = itemDao.selectBySearch(itemNameRuleTo, itemCategoryRuleTo, itemFromRuleTo, itemToRuleTo, SORT_NULL);
		// 検証
		assertThat("selectBySearch:条件有:Toのみ", actualRuleTo.size(), is(expectedListCountRuleTo));

	}
}
