package com.nmnw.admin.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateConversionUtility {
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	public DateConversionUtility() {
	}

	/**
	 * 文字列を日付(Calendar型）に変換　（Attention: 引数の形式チェック後に使用する必要有）
	 * @param value
	 * @return cal
	 */
	public static Calendar stringToCalendar (String value) {
		String[] dateStrings = value.split("-");
		int year = Integer.valueOf(dateStrings[0]);
		int month = Integer.valueOf(dateStrings[1])-1;
		int day = Integer.valueOf(dateStrings[2]);
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(year, month, day);
		cal.getTime();
		return cal;
	}

	/**
	 * 文字列を日付(Date型）に変換　（Attention: 引数の形式チェック後に使用する必要有）
	 * @param value
	 * @return date
	 */
	public static Date stringToDate (String value) {
		if (value == null || value == "") {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 文字列を日付(Date型）に変換
	 * @param value
	 * @return date
	 */
	public static Date stringToDateTime (String value) {
		if (value == null || value == "") {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 現在時刻(String型)を取得
	 */
	public static String getCurrentDateString () {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		return dateFormat.format(calendar.getTime());
	}

	public static Date timestampToDate(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		try {
			String date = dateFormat.format(timestamp);
			return stringToDateTime(date);
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * 時間日付(Date型）を文字列に変換
	 * @param value
	 * @return date
	 */
	public static String dateTimeToString (Date value) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日付(Date型）を文字列に変換
	 * @param value
	 * @return date
	 */
	public static String dateToString (Date value) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 第1引数の日付から過去第2引数ヶ月分の月初、月末の日付リストを取得
	 * @param cal
	 * @param months
	 * @return
	 */
	public static List<Map<String, String>> getLastBeginningAndEndOfMonthList (Calendar cal, int months) {
		List<Map<String, String>> dateList = new ArrayList<Map<String, String>>();
		try {
			for (int i = 0; i < months; i++) {
				Calendar calc = (Calendar)cal.clone();
				calc.add(Calendar.MONTH, -i);
				String from = getBeginningOfMonth(calc);
				String to = getEndOfMonth(calc);
				Map<String, String> map = new HashMap<String, String>();
				map.put("from", from);
				map.put("to", to);
				dateList.add(map);
			}
			return dateList;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 月初の日付取得
	 * @param cal
	 * @return
	 */
	private static String getBeginningOfMonth (Calendar cal) {
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		return sdf.format(cal.getTime());
	}

	/**
	 * 月末の日付取得
	 * @param cal
	 * @return
	 */
	private static String getEndOfMonth (Calendar cal) {
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		return sdf.format(cal.getTime());
	}

	/**
	 * n日後の日付を文字列で返却
	 * @param value
	 * @return date
	 */
	public static String getdaysAgoString (int daysAgo) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -daysAgo);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(cal.getTime());
		} catch (Exception e) {
			return null;
		}
	}
}
