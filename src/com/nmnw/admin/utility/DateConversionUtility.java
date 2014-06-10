package com.nmnw.admin.utility;

import java.util.Calendar;

public class DateConversionUtility {

	public DateConversionUtility() {
	}

	/**
	 * 文字列を日付に変換　（Attention: 引数の形式チェック後に使用する必要有）
	 * @param value
	 * @return cal
	 */
	public static Calendar toDate (String value) {
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
}
