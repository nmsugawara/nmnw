package com.nmnw.admin.validator;

import java.util.*;

import javax.servlet.http.Part;

import com.nmnw.admin.constant.ValidatorConstants;
import com.nmnw.admin.utility.DateConversionUtility;

public class Validator {
	private static final String REQUIRED_SELECT = "0";
	private static final String MATCH_NUMBER = "^[0-9]+$";
	private static final String MATCH_DATE = "^[0-9]{4}[-][01]?[0-9][-][0123]?[0-9]$";
	private List<String> _errorMassageList = new ArrayList<String>();

	/**
	 * �K�{�`�F�b�N�i���́j
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
	 * �K�{�`�F�b�N�i�v���_�E���j
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
	 * �K�{�`�F�b�N�i�摜�A�b�v���[�h�j
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
	 * �`���`�F�b�N�i�����j
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
	 * �`���`�F�b�N�i���t�j�iYYYY-MM-DD�j
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
	 * ���Ԑ������`�F�b�N
	 * @param from
	 * @param fieldNameFrom
	 * @param to
	 * @param fieldNameTo
	 * @return hasError
	 */
	public boolean correctPeriod(String from, String fieldNameFrom, String to, String fieldNameTo) {
		boolean hasError = false;
		// from�Ato�����t�`����
		if (isDate(from, fieldNameFrom) || isDate(to, fieldNameTo)) {
			String message = getMessage(ValidatorConstants.MESSAGE_CORRECT_PERIOD, fieldNameFrom, fieldNameTo);
			addErrorMessageList(message);
			hasError = true;
		} else {
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
	 * �ő�l�`�F�b�N�i�����j
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
	 * �ő�l�`�F�b�N�i������j
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
	 * �ő�l�`�F�b�N�i�摜�T�C�Y�j
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
	 * �ŏ��l�`�F�b�N�i�����j
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
	 * �ŏ��l�`�F�b�N�i������j
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
	 * �`�F�b�N�G���[���b�Z�[�W����/�擾
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
	 * �G���[���b�Z�[�W�i�[�p�k�������ւ̃��b�Z�[�W�ǉ�
	 * @param e
	 */
	public void addErrorMessageList(String e) {
		_errorMassageList.add(e);
	}

	/**
	 * �G���[���b�Z�[�W���X�g�擾
	 * @return
	 */
	public List<String> getErrorMessageList() {
		return _errorMassageList;
	}
}