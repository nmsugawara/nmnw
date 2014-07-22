package nmnw.service.utility;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.nmnw.service.utility.DateConversionUtility;
import com.nmnw.service.utility.HtmlHelper;

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
		 * ³íŒn
		 */
		// ‰Šú‰»
		String value = DATE_STRING;
		Calendar expected = Calendar.getInstance();
		expected.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		// Às
		Calendar actual = DateConversionUtility.stringToCalendar(value);
		// ŒŸØ
		assertThat("stringToCalendar:³íŒn", expected.getTime().toString(), is(actual.getTime().toString()));
		// Œãˆ—
	}

	@Test
	public void stringToDateTest() throws Exception{
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		String value = DATE_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// Às
		Date actual = DateConversionUtility.stringToDate(value);
		// ŒŸØ
		assertThat("stringToCalendar:³íŒn", expected.toString(), is(actual.toString()));
		// Œãˆ—

		/**
		 * ˆÙíŒnFnull
		 */
		// ‰Šú‰»
		String valueNull = null;
		Date expectedNull = null;
		// Às
		Date actualNull = DateConversionUtility.stringToDate(valueNull);
		// ŒŸØ
		assertThat("stringToCalendar:ˆÙíŒnFnull", expectedNull, is(actualNull));
		// Œãˆ—

		/**
		 * ˆÙíŒnF‹ó•¶š
		 */
		// ‰Šú‰»
		String valueNoData = "";
		Date expectedNoData = null;
		// Às
		Date actualNoData = DateConversionUtility.stringToDate(valueNoData);
		// ŒŸØ
		assertThat("stringToCalendar:ˆÙíŒnF‹ó•¶š", expectedNoData, is(actualNoData));
		// Œãˆ—
	}

	@Test
	public void stringToDateTimeTest() throws Exception{
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		String value = DATE_TIME_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// Às
		Date actual = DateConversionUtility.stringToDateTime(value);
		// ŒŸØ
		assertThat("stringToDateTime:³íŒn", expected.toString(), is(actual.toString()));
		// Œãˆ—

		/**
		 * ˆÙíŒnFnull
		 */
		// ‰Šú‰»
		String valueNull = null;
		Date expectedNull = null;
		// Às
		Date actualNull = DateConversionUtility.stringToDateTime(valueNull);
		// ŒŸØ
		assertThat("stringToDateTime:ˆÙíŒnFnull", expectedNull, is(actualNull));
		// Œãˆ—

		/**
		 * ˆÙíŒnF‹ó•¶š
		 */
		// ‰Šú‰»
		String valueNoData = "";
		Date expectedNoData = null;
		// Às
		Date actualNoData = DateConversionUtility.stringToDateTime(valueNoData);
		// ŒŸØ
		assertThat("stringToDateTime:ˆÙíŒnF‹ó•¶š", expectedNoData, is(actualNoData));
		// Œãˆ—
	}

	@Test
	public void getCurrentDateStringTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		String expected = dateFormat.format(calendar.getTime());
		// Às
		String actual = DateConversionUtility.getCurrentDateString();
		// ŒŸØ
		assertThat("stringToDateTime:³íŒn", expected, is(actual));
		// Œãˆ—
	}

	@Test
	public void timestampToDateTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		Date expected = new Date();
		Timestamp timeStamp = new Timestamp(expected.getTime());

		// Às
		Date actual = DateConversionUtility.timestampToDate(timeStamp);
		// ŒŸØ
		assertThat("timestampToDate:³íŒn", String.valueOf(expected), is(String.valueOf(actual)));
		// Œãˆ—
	}

	@Test
	public void dateTimeToStringTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		String expected = DATE_TIME_STRING;
		Date value = DateConversionUtility.stringToDateTime(expected);
		// Às
		String actual = DateConversionUtility.dateTimeToString(value);
		// ŒŸØ
		assertThat("dateTimeToString:³íŒn", expected, is(actual));
		// Œãˆ—
	}

	@Test
	public void dateToStringTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		String expected = DATE_STRING;
		Date value = DateConversionUtility.stringToDate(expected);
		// Às
		String actual = DateConversionUtility.dateToString(value);
		// ŒŸØ
		assertThat("dateToString:³íŒn", expected, is(actual));
		// Œãˆ—
	}

	@Test
	public void getLastBeginningAndEndOfMonthListTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		int monthAgo = MONTH_AGO;
		Calendar cal = Calendar.getInstance();
		cal.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		
		// Às
		List<Map<String, String>> actual = DateConversionUtility.getLastBeginningAndEndOfMonthList(cal, monthAgo);
		// ŒŸØ
		assertThat("getLastBeginningAndEndOfMonthList:³íŒn:æŒŒ‰", LAST_MONTH_FIRST, is(actual.get(0).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:³íŒn:æŒŒ––", LAST_MONTH_END, is(actual.get(0).get("to")));		
		assertThat("getLastBeginningAndEndOfMonthList:³íŒn:æXŒŒ‰", BEFORE_LAST_MONTH_FIRST, is(actual.get(1).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:³íŒn:æXŒŒ––", BEFORE_LAST_MONTH_END, is(actual.get(1).get("to")));
		// Œãˆ—
	}

	@Test
	public void getdaysAfterStringTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		String expected = dateFormat.format(cal.getTime());
		// Às
		String actual = DateConversionUtility.getdaysAfterString(daysAfter);
		// ŒŸØ
		assertThat("getdaysAfterString:³íŒn", expected, is(actual));
		// Œãˆ—
	}

	@Test
	public void getdaysAfterDateTest() {
		/**
		 * ³íŒn
		 */
		// ‰Šú‰»
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		Date expected = cal.getTime();
		// Às
		Date actual = DateConversionUtility.getdaysAfterDate(daysAfter);
		// ŒŸØ
		assertThat("getdaysAfterDate:³íŒn", expected.toString(), is(actual.toString()));
		// Œãˆ—
	}
}
