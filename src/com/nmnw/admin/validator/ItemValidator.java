package com.nmnw.admin.validator;

import java.util.List;

import javax.servlet.http.Part;

import com.nmnw.admin.constant.ConfigConstants;
import com.nmnw.admin.validator.Validator;

public class ItemValidator {
	private static final String FIELD_ID = "商品ID";
	private static final String FIELD_NAME = "商品名";
	private static final String FIELD_PRICE = "商品単価";
	private static final String FIELD_CATEGORY = "商品ジャンル";
	private static final String FIELD_IMAGE = "商品画像";
	private static final String FIELD_EXPLANATION = "商品説明";
	private static final String FIELD_SALES_PERIOD_FROM = "販売開始日";
	private static final String FIELD_SALES_PERIOD_TO = "販売終了日";
	private static final String FIELD_STOCK = "在庫数";
	private static final String SEARCH_FIELD_ID = FIELD_ID;
	private static final String SEARCH_FIELD_NAME = FIELD_NAME;
	private static final String SEARCH_FIELD_CATEGORY = FIELD_CATEGORY;
	private static final String SEARCH_FIELD_SALES_PERIOD_FROM = FIELD_SALES_PERIOD_FROM;
	private static final String SEARCH_FIELD_SALES_PERIOD_TO = FIELD_SALES_PERIOD_TO;

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

	public void checkId(String value) {
		if (!v.required(value, FIELD_NAME)) {
			v.maxSizeString(value, MAX_SIZE_NAME, FIELD_NAME);
		}
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

	public void checkSearchId(String value) {
		v.isInt(value, SEARCH_FIELD_ID);
	}

	public void checkSearchDateFrom(String value) {
		v.isDate(value, SEARCH_FIELD_SALES_PERIOD_FROM);
	}

	public void checkSearchDateTo(String value) {
		v.isDate(value, SEARCH_FIELD_SALES_PERIOD_TO);
	}

	public List<String> getValidationList() {
		return v.getErrorMessageList();
	}
}