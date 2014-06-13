package com.nmnw.service.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	 * 日付(Date型）を文字列に変換
	 * @param value
	 * @return date
	 */
	public static String dateToString (Date value) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(value);
		} catch (Exception e) {
			return null;
		}
	}
}
