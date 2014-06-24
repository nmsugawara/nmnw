package com.nmnw.service.validator;

import java.util.*;

import javax.servlet.http.Part;

import com.nmnw.service.constant.ValidatorConstants;
import com.nmnw.service.utility.DateConversionUtility;

public class Validator {
	private static final String REQUIRED_SELECT = "0";
	private static final String MATCH_NUMBER = "^[0-9]+$";
	private static final String MATCH_DATE = "^[0-9]{4}[-][01]?[0-9][-][0123]?[0-9]$";
	private static final String MATCH_HALF_WIDTH_CHARACTERS = "^[a-zA-Z0-9]+$";
	private static final String MATCH_KATAKANA = "^[\\u30A0-\\u30FF\\u3000]+$";
	private static final String MATCH_ZIP_CODE = "^[0-9]{3}[-][0-9]{4}$";
	private static final String MATCH_PHONE_NUMBER = "^[0-9]+[-][0-9]+[-][0-9]+$";
	private static final String MATCH_BOOLEAN = "^true|false$";
	private List<String> _errorMassageList = new ArrayList<String>();

	/**
	 * 必須チェック（入力）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean required(String value, String fieldName) {
		boolean hasError = false;
		if(value == null || value.length() == 0) {
			String message = getMessage(ValidatorConstants.MESSAGE_REQUIRED, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 必須チェック（プルダウン）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean requiredSelect(String value, String fieldName) {
		boolean hasError = false;
		if(REQUIRED_SELECT.equals(value)) {
			String message = getMessage(ValidatorConstants.MESSAGE_REQUIRED_SELECT, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 必須チェック（画像アップロード）
	 * @param image
	 * @param fieldName
	 * @return hasError
	 */
	public boolean requiredImage(Part image, String fieldName) {
		boolean hasError = false;
		long emptyValue = 0;
		if(image.getSize() == emptyValue) {
			String message = getMessage(ValidatorConstants.MESSAGE_REQUIRED_IMAGE, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（数字）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isInt(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_NUMBER)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_INT, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（boolean）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isBoolean(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_BOOLEAN)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_BOOLEAN, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（日付）（YYYY-MM-DD）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isDate(String value, String fieldName) {
		boolean hasError = false;		
		try {
			if (!value.matches(MATCH_DATE)) {
				throw new IllegalArgumentException();
			} else {
				String[] dateStrings = value.split("-");
				int year = Integer.valueOf(dateStrings[0]);
				int month = Integer.valueOf(dateStrings[1])-1;
				int day = Integer.valueOf(dateStrings[2]);
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				cal.set(year, month, day);
				cal.getTime();
				return hasError;
			}
		} catch (IllegalArgumentException e) {
				String message = getMessage(ValidatorConstants.MESSAGE_IS_DATE, fieldName);
				addErrorMessageList(message);
				hasError = true;
				return hasError;
		}
	}

	/**
	 * 形式チェック（半角英数字）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isHalfWidthCharacters(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_HALF_WIDTH_CHARACTERS)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_HALF_WIDTH_CHARACTERS, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（全角カタカナ）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isKatakana(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_KATAKANA)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_KATAKANA, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（郵便番号）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isZipCode(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_ZIP_CODE)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_ZIP_CODE, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 形式チェック（電話番号）
	 * @param value
	 * @param fieldName
	 * @return hasError
	 */
	public boolean isPhoneNumber(String value, String fieldName) {
		boolean hasError = false;
		if (!value.matches(MATCH_PHONE_NUMBER)) {
			String message = getMessage(ValidatorConstants.MESSAGE_IS_PHONE_NUMBER, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 期間整合性チェック
	 * @param from
	 * @param fieldNameFrom
	 * @param to
	 * @param fieldNameTo
	 * @return hasError
	 */
	public boolean correctPeriod(String from, String fieldNameFrom, String to, String fieldNameTo) {
		boolean hasError = false;
		// from、toが日付形式か
		if (!isDate(from, fieldNameFrom) && !isDate(to, fieldNameTo)) {
			Calendar calFrom = DateConversionUtility.stringToCalendar(from);
			Calendar calTo = DateConversionUtility.stringToCalendar(to);
			int judge = calFrom.compareTo(calTo);
			if ( judge > 0) {
				String message = getMessage(ValidatorConstants.MESSAGE_CORRECT_PERIOD, fieldNameFrom, fieldNameTo);
				addErrorMessageList(message);
				hasError = true;
			}
		}
		return hasError;
	}
	/**
	 * 最大値チェック（数字）
	 * @param value
	 * @param maxSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean maxSizeInt(int value, int maxSize, String fieldName) {
		boolean hasError = false;
		if (value > maxSize) {
			String message = getMessage(ValidatorConstants.MESSAGE_MAXSIZE_INT, fieldName, String.valueOf(maxSize));
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 最大値チェック（文字列）
	 * @param value
	 * @param maxSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean maxSizeString(String value, int maxSize, String fieldName) {
		boolean hasError = false;
		if (value.length() > maxSize) {
			String message = getMessage(ValidatorConstants.MESSAGE_MAXSIZE_STRING, fieldName, String.valueOf(maxSize));
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 最大値チェック（画像サイズ）
	 * @param imageSize
	 * @param maxSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean maxSizeImage(long imageSize, long maxSize, String fieldName) {
		boolean hasError = false;
		if (imageSize > maxSize) {
			String message = getMessage(ValidatorConstants.MESSAGE_MAXSIZE_IMAGE, fieldName, String.valueOf(maxSize));
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 最小値チェック（数字）
	 * @param value
	 * @param minSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean minSizeInt(int value, int minSize, String fieldName) {
		boolean hasError = false;
		if (value < minSize) {
			String message = getMessage(ValidatorConstants.MESSAGE_MINSIZE_INT, fieldName, String.valueOf(minSize));
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 最小値チェック（文字列）
	 * @param value
	 * @param minSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean minSizeString(String value, int minSize, String fieldName) {
		boolean hasError = false;
		if (value.length() < minSize) {
			String message = getMessage(ValidatorConstants.MESSAGE_MINSIZE_STRING, fieldName, String.valueOf(minSize));
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 同値チェック（文字列）
	 * @param value
	 * @param minSize
	 * @param fieldName
	 * @return hasError
	 */
	public boolean compareToStrings(String valueA, String valueB, String fieldName) {
		boolean hasError = false;
		if (!valueA.equals(valueB)) {
			String message = getMessage(ValidatorConstants.MESSAGE_COMPARE_STRING, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}

	/**
	 * 有効期限チェック
	 * @param expireDate　期限日
	 * @param date　チェック対象日
	 * @param fieldName
	 * @return
	 */
	public boolean withinExpireDateTime(Date expireDate, Date date, String fieldName) {
		boolean hasError = false;
		if (expireDate.before(date)) {
			String message = getMessage(ValidatorConstants.MESSAGE_EXPIRE_DATE, fieldName);
			addErrorMessageList(message);
			hasError = true;
		}
		return hasError;
	}
	
	/**
	 * チェックエラーメッセージ生成/取得
	 * @param message
	 * @param args
	 * @return message
	 */
	public String getMessage(String message, String...args) {
		for (int i = 0; i < args.length; i++) {
			message = message.replace("{$" + (i+1) + "}", args[i]);
		}
		return message;
	}

	/**
	 * エラーメッセージ格納用Ｌｉｓｔへのメッセージ追加
	 * @param e
	 */
	public void addErrorMessageList(String e) {
		_errorMassageList.add(e);
	}

	/**
	 * エラーメッセージリスト取得
	 * @return
	 */
	public List<String> getErrorMessageList() {
		return _errorMassageList;
	}
}