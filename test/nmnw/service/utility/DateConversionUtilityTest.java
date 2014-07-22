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
		 * ����n
		 */
		// ������
		String value = DATE_STRING;
		Calendar expected = Calendar.getInstance();
		expected.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		// ���s
		Calendar actual = DateConversionUtility.stringToCalendar(value);
		// ����
		assertThat("stringToCalendar:����n", expected.getTime().toString(), is(actual.getTime().toString()));
		// �㏈��
	}

	@Test
	public void stringToDateTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		String value = DATE_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// ���s
		Date actual = DateConversionUtility.stringToDate(value);
		// ����
		assertThat("stringToCalendar:����n", expected.toString(), is(actual.toString()));
		// �㏈��

		/**
		 * �ُ�n�Fnull
		 */
		// ������
		String valueNull = null;
		Date expectedNull = null;
		// ���s
		Date actualNull = DateConversionUtility.stringToDate(valueNull);
		// ����
		assertThat("stringToCalendar:�ُ�n�Fnull", expectedNull, is(actualNull));
		// �㏈��

		/**
		 * �ُ�n�F�󕶎�
		 */
		// ������
		String valueNoData = "";
		Date expectedNoData = null;
		// ���s
		Date actualNoData = DateConversionUtility.stringToDate(valueNoData);
		// ����
		assertThat("stringToCalendar:�ُ�n�F�󕶎�", expectedNoData, is(actualNoData));
		// �㏈��
	}

	@Test
	public void stringToDateTimeTest() throws Exception{
		/**
		 * ����n
		 */
		// ������
		String value = DATE_TIME_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		Date expected = dateFormat.parse(value);
		// ���s
		Date actual = DateConversionUtility.stringToDateTime(value);
		// ����
		assertThat("stringToDateTime:����n", expected.toString(), is(actual.toString()));
		// �㏈��

		/**
		 * �ُ�n�Fnull
		 */
		// ������
		String valueNull = null;
		Date expectedNull = null;
		// ���s
		Date actualNull = DateConversionUtility.stringToDateTime(valueNull);
		// ����
		assertThat("stringToDateTime:�ُ�n�Fnull", expectedNull, is(actualNull));
		// �㏈��

		/**
		 * �ُ�n�F�󕶎�
		 */
		// ������
		String valueNoData = "";
		Date expectedNoData = null;
		// ���s
		Date actualNoData = DateConversionUtility.stringToDateTime(valueNoData);
		// ����
		assertThat("stringToDateTime:�ُ�n�F�󕶎�", expectedNoData, is(actualNoData));
		// �㏈��
	}

	@Test
	public void getCurrentDateStringTest() {
		/**
		 * ����n
		 */
		// ������
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		String expected = dateFormat.format(calendar.getTime());
		// ���s
		String actual = DateConversionUtility.getCurrentDateString();
		// ����
		assertThat("stringToDateTime:����n", expected, is(actual));
		// �㏈��
	}

	@Test
	public void timestampToDateTest() {
		/**
		 * ����n
		 */
		// ������
		Date expected = new Date();
		Timestamp timeStamp = new Timestamp(expected.getTime());

		// ���s
		Date actual = DateConversionUtility.timestampToDate(timeStamp);
		// ����
		assertThat("timestampToDate:����n", String.valueOf(expected), is(String.valueOf(actual)));
		// �㏈��
	}

	@Test
	public void dateTimeToStringTest() {
		/**
		 * ����n
		 */
		// ������
		String expected = DATE_TIME_STRING;
		Date value = DateConversionUtility.stringToDateTime(expected);
		// ���s
		String actual = DateConversionUtility.dateTimeToString(value);
		// ����
		assertThat("dateTimeToString:����n", expected, is(actual));
		// �㏈��
	}

	@Test
	public void dateToStringTest() {
		/**
		 * ����n
		 */
		// ������
		String expected = DATE_STRING;
		Date value = DateConversionUtility.stringToDate(expected);
		// ���s
		String actual = DateConversionUtility.dateToString(value);
		// ����
		assertThat("dateToString:����n", expected, is(actual));
		// �㏈��
	}

	@Test
	public void getLastBeginningAndEndOfMonthListTest() {
		/**
		 * ����n
		 */
		// ������
		int monthAgo = MONTH_AGO;
		Calendar cal = Calendar.getInstance();
		cal.set(DATE_YEAR, DATE_MONTH, DATE_DAY);
		
		// ���s
		List<Map<String, String>> actual = DateConversionUtility.getLastBeginningAndEndOfMonthList(cal, monthAgo);
		// ����
		assertThat("getLastBeginningAndEndOfMonthList:����n:�挎����", LAST_MONTH_FIRST, is(actual.get(0).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:����n:�挎����", LAST_MONTH_END, is(actual.get(0).get("to")));		
		assertThat("getLastBeginningAndEndOfMonthList:����n:��X������", BEFORE_LAST_MONTH_FIRST, is(actual.get(1).get("from")));
		assertThat("getLastBeginningAndEndOfMonthList:����n:��X������", BEFORE_LAST_MONTH_END, is(actual.get(1).get("to")));
		// �㏈��
	}

	@Test
	public void getdaysAfterStringTest() {
		/**
		 * ����n
		 */
		// ������
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		String expected = dateFormat.format(cal.getTime());
		// ���s
		String actual = DateConversionUtility.getdaysAfterString(daysAfter);
		// ����
		assertThat("getdaysAfterString:����n", expected, is(actual));
		// �㏈��
	}

	@Test
	public void getdaysAfterDateTest() {
		/**
		 * ����n
		 */
		// ������
		int daysAfter = DAY_AFTER;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysAfter);
		Date expected = cal.getTime();
		// ���s
		Date actual = DateConversionUtility.getdaysAfterDate(daysAfter);
		// ����
		assertThat("getdaysAfterDate:����n", expected.toString(), is(actual.toString()));
		// �㏈��
	}
}
