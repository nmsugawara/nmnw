package com.nmnw.admin.utility;

import java.util.Calendar;

public class DateConversionUtility {

	public DateConversionUtility() {
	}

	/**
	 * ���������t�ɕϊ��@�iAttention: �����̌`���`�F�b�N��Ɏg�p����K�v�L�j
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
