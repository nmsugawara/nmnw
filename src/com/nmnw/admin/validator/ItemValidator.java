package com.nmnw.admin.validator;

import java.util.List;

import javax.servlet.http.Part;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.validator.Validator;

public class ItemValidator {
	private static final String FIELD_ID = "���iID";
	private static final String FIELD_NAME = "���i��";
	private static final String FIELD_PRICE = "���i�P��";
	private static final String FIELD_CATEGORY = "���i�W������";
	private static final String FIELD_IMAGE = "���i�摜";
	private static final String FIELD_EXPLANATION = "���i����";
	private static final String FIELD_SALES_PERIOD_FROM = "�̔��J�n��";
	private static final String FIELD_SALES_PERIOD_TO = "�̔��I����";
	private static final String FIELD_STOCK = "�݌ɐ�";

	private static final int MAX_SIZE_NAME = 200;
	private static final long MIN_SIZE_PRICE = 0;
	private static final long MAX_SIZE_PRICE = 10000;
	private static final int MAX_SIZE_EXPLANATION = 5000;
	private static final long MIN_SIZE_STOCK = 0;
	private static final long MAX_SIZE_STOCK = 100;

	private Validator v;
	
	/**
	 * Construct
	 */
	public ItemValidator() {
		v = new Validator();
	}

	public void checkName(String value) {
		if (!v.required(value, FIELD_NAME)) {
			v.maxSizeString(value, MAX_SIZE_NAME, FIELD_NAME);
		}
	}

	public void checkPrice(String value) {
		if (!v.required(value, FIELD_PRICE)) {
			if (!v.isInt(value, FIELD_PRICE)) {
				if (!v.minSizeLong(Long.parseLong(value), MIN_SIZE_PRICE, FIELD_PRICE)) {
					v.maxSizeLong(Long.parseLong(value), MAX_SIZE_PRICE, FIELD_PRICE);
				}
			}
		}
	}

	public void checkCategory(String value) {
		v.requiredSelect(value, FIELD_CATEGORY);
	}

	public void checkExplanation(String value) {
		if (!v.required(value, FIELD_EXPLANATION)) {
			v.maxSizeString(value, MAX_SIZE_EXPLANATION, FIELD_EXPLANATION);
		}
	}

	public void checkSalesPeriod(String from, String to) {
		if (!v.required(from, FIELD_SALES_PERIOD_FROM) && !v.required(to, FIELD_SALES_PERIOD_TO)) {
			v.correctPeriod(from, FIELD_SALES_PERIOD_FROM, to, FIELD_SALES_PERIOD_TO);
		}
	}

	public void checkStock(String value) {
		if (!v.required(value, FIELD_STOCK)) {
			if (!v.isInt(value, FIELD_STOCK)) {
				if (!v.minSizeLong(Long.parseLong(value), MIN_SIZE_STOCK, FIELD_STOCK)) {
					v.maxSizeLong(Long.parseLong(value), MAX_SIZE_STOCK, FIELD_STOCK);
				}
			}
		}
	}

	public void checkImage(Part image) {
		if (!v.requiredImage(image, FIELD_IMAGE)) {
			v.maxSizeImage(image.getSize(), ConfigConstants.MAX_IMAGE_FILE_SIZE, FIELD_IMAGE);
		}
	}

	public List<String> getValidationList() {
		return v.getErrorMessageList();
	}
}