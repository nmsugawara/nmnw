package nmnw.admin.utility;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.nmnw.admin.utility.DateConversionUtility;

public class DateConversionUtilityTest {
	public static final String DATE_STRING = "2014-07-05";
	public static final int DATE_YEAR = 2014;
	public static final int DATE_MONTH = 6;
	public static final int DATE_DAY = 5;
	public static final String DATE_TIME_STRING = "2014-07-05 11:11:11";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final int DAY_AFTER = 3;
	public static final int MONTH_AGO = 2;
	public static final String LAST_MONTH_FIRST = "2014-07-01";
	public static final String LAST_MONTH_END = "2014-07-31";
	public static final String BEFORE_LAST_MONTH_FIRST = "2014-06-01";
	public static final String BEFORE_LAST_MONTH_END = "2014-06-30";
	
	@Test
	public void dateConversionUtilityTest() {
	}

	@Test
	public void stringToCalendarTest() {
		/**
		 * 正常系
		 */
		// 初期化
		String value = DATE_STRING;
		Calendar expected = Calendar.getInstance();
		expected.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		// 実行
		Calendar actual = DateConversionUtility.stringToCalendar(value);
		// 検証
		assertThat("stringToCalendar:正常系", expected.getTime().toString(), is(actual.getTime().toString()));
		// 後処理
	}

	@Test
	public void stringToDateTest() throws Exception{
		/**
		 * 正常系
		 */
		// 初期化
		String value = DATE_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// 実行
		Date actual = DateConversionUtility.stringToDate(value);
		// 検証
		assertThat("stringToCalendar:正常系", expected.toString(), is(actual.toString()));
		// 後処理

		/**
		 * 異常系：null
		 */
		// 初期化
		String valueNull = null;
		Date expectedNull = null;
		// 実行
		Date actualNull = DateConversionUtility.stringToDate(valueNull);
		// 検証
		assertThat("stringToCalendar:異常系：null", expectedNull, is(actualNull));
		// 後処理

		/**
		 * 異常系：空文字
		 */
		// 初期化
		String valueNoData = "";
		Date expectedNoData = null;
		// 実行
		Date actualNoData = DateConversionUtility.stringToDate(valueNoData);
		// 検証
		assertThat("stringToCalendar:異常系：空文字", expectedNoData, is(actualNoData));
		// 後処理
	}

	@Test
	public void stringToDateTimeTest() throws Exception{
		/**
		 * 正常系
		 */
		// 初期化
		String value = DATE_TIME_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// 実行
		Date actual = DateConversionUtility.stringToDateTime(value);
		// 検証
		assertThat("stringToDateTime:正常系", expected.toString(), is(actual.toString()));
		// 後処理

		/**
		 * 異常系：null
		 */
		// 初期化
		String valueNull = null;
		Date expectedNull = null;
		// 実行
		Date actualNull = DateConversionUtility.stringToDateTime(valueNull);
		// 検証
		assertThat("stringToDateTime:異常系：null", expectedNull, is(actualNull));
		// 後処理

		/**
		 * 異常系：空文字
		 */
		// 初期化
		String valueNoData = "";
		Date expectedNoData = null;
		// 実行
		Date actualNoData = DateConversionUtility.stringToDateTime(valueNoData);
		// 検証
		assertThat("stringToDateTime:異常系：空文字", expectedNoData, is(actualNoData));
		// 後処理
	}

	@Test
	public void getCurrentDateStringTest() {
		/**
		 * 正常系
		 */
		// 初期化
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		String expected = dateFormat.format(calendar.getTime());
		// 実行
		String actual = DateConversionUtility.getCurrentDateString();
		// 検証
		assertThat("stringToDateTime:正常系", expected, is(actual));
		// 後処理
	}

	@Test
	public void timestampToDateTest() {
		/**
		 * 正常系
		 */
		// 初期化
		Date expected = new Date();
		Timestamp timeStamp = new Timestamp(expected.getTime());

		// 実行
		Date actual = DateConversionUtility.timestampToDate(timeStamp);
		// 検証
		assertThat("timestampToDate:正常系", String.valueOf(expected), is(String.valueOf(actual)));
		// 後処理
	}

	@Test
	public void dateTimeToStringTest() {
		/**
		 * 正常系
		 */
		// 初期化
		String expected = DATE_TIME_STRING;
		Date value = DateConversionUtility.stringToDateTime(expected);
		// 実行
		String actual = DateConversionUtility.dateTimeToString(value);
		// 検証
		assertThat("dateTimeToString:正常系", expected, is(actual));
		// 後処理
	}

	@Test
	public void dateToStringTest() {
		/**
		 * 正常系
		 */
		// 初期化
		String expected = DATE_STRING;
		Date value = DateConversionUtility.stringToDate(expected);
		// 実行
		String actual = DateConversionUtility.dateToString(value);
		// 検証
		assertThat("dateToString:正常系", expected, is(actual));
		// 後処理
	}

	@Test
	public void getLastBeginningAndEndOfMonthListTest() {
		/**
		 * 正常系
		 */
		// 初期化
		int monthAgo = MONTH_AGO;
		Calendar cal = Calendar.getInstance();
		cal.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		
		// 実行
		List<Map<String, String>> actual = DateConversionUtility.getLastBeginningAndEndOfMonthList(cal, monthAgo);
		// 検証
		assertThat("getLastBeginningAndEndOfMonthList:正常系:先月月初", LAST_MONTH_FIRST, is(actual.get(0).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:正常系:先月月末", LAST_MONTH_END, is(actual.get(0).get("to")));		
		assertThat("getLastBeginningAndEndOfMonthList:正常系:先々月月初", BEFORE_LAST_MONTH_FIRST, is(actual.get(1).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:正常系:先々月月末", BEFORE_LAST_MONTH_END, is(actual.get(1).get("to")));
		// 後処理
	}

	@Test
	public void getdaysAfterStringTest() {
		/**
		 * 正常系
		 */
		// 初期化
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		String expected = dateFormat.format(cal.getTime());
		// 実行
		String actual = DateConversionUtility.getdaysAfterString(daysAfter);
		// 検証
		assertThat("getdaysAfterString:正常系", expected, is(actual));
		// 後処理
	}

	@Test
	public void getdaysAfterDateTest() {
		/**
		 * 正常系
		 */
		// 初期化
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		Date expected = cal.getTime();
		// 実行
		Date actual = DateConversionUtility.getdaysAfterDate(daysAfter);
		// 検証
		assertThat("getdaysAfterDate:正常系", expected.toString(), is(actual.toString()));
		// 後処理
	}
}
