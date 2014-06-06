package com.nmnw.admin.validator;

import java.util.List;

import javax.servlet.http.Part;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.validator.Validator;

public class ItemValidator {
	private static final String FIELD_ID = "¤iID";
	private static final String FIELD_NAME = "¤i¼";
	private static final String FIELD_PRICE = "¤iP¿";
	private static final String FIELD_CATEGORY = "¤iW";
	private static final String FIELD_IMAGE = "¤iæ";
	private static final String FIELD_EXPLANATION = "¤ià¾";
	private static final String FIELD_SALES_PERIOD_FROM = "ÌJnú";
	private static final String FIELD_SALES_PERIOD_TO = "ÌI¹ú";
	private static final String FIELD_STOCK = "ÝÉ";

	private static final int MAX_SIZE_NAME = 200;
	private static final int MIN_SIZE_PRICE = 0;
	private static final int MAX_SIZE_PRICE = 10000;
	private static final int MAX_SIZE_EXPLANATION = 5000;
	private static final int MIN_SIZE_STOCK = 0;
	private static final int MAX_SIZE_STOCK = 100;

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
				if (!v.minSizeInt(Integer.parseInt(value), MIN_SIZE_PRICE, FIELD_PRICE)) {
					v.maxSizeInt(Integer.parseInt(value), MAX_SIZE_PRICE, FIELD_PRICE);	
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

	public void checkSalesPeriodFrom(String value) {
		if (!v.required(value, FIELD_SALES_PERIOD_FROM)) {
			v.isDate(value, FIELD_SALES_PERIOD_FROM);
		}
	}

	public void checkSalesPeriodTo(String value) {
		if (!v.required(value, FIELD_SALES_PERIOD_TO)) {
			v.isDate(value, FIELD_SALES_PERIOD_TO);
		}
	}

	public void checkStock(String value) {
		if (!v.required(value, FIELD_STOCK)) {
			if (!v.isInt(value, FIELD_STOCK)) {
				if (!v.minSizeInt(Integer.parseInt(value), MIN_SIZE_STOCK, FIELD_STOCK)) {
					v.maxSizeInt(Integer.parseInt(value), MAX_SIZE_STOCK, FIELD_STOCK);
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