package com.nmnw.service.validator;

import java.util.List;

import com.nmnw.service.validator.Validator;

public class AccountValidator {
	private static final String FIELD_ID = "会員ID";
	private static final String FIELD_NAME = "会員名";
	private static final String FIELD_NAME_KANA = "会員名カナ";
	private static final String FIELD_MAIL = "メールアドレス";
	private static final String FIELD_PASS_WORD = "パスワード";
	private static final String FIELD_ZIP_CODE = "郵便番号";
	private static final String FIELD_ADDRESS = "住所";
	private static final String FIELD_PHONE_NUMBER = "電話番号";
	private static final String FIELD_DEL_FLG = "削除フラグ";
	private static final String FIELD_TOKEN = "トークン";

	private static final int MAX_SIZE_NAME = 200;
	private static final int MAX_SIZE_NAME_KANA = 200;
	private static final int MAX_SIZE_MAIL = 200;
	private static final int MIN_SIZE_PASS_WORD = 8;
	private static final int MAX_SIZE_PASS_WORD = 50;
	private static final int MAX_SIZE_ADDRESS = 200;

	private Validator v;
	
	/**
	 * Construct
	 */
	public AccountValidator() {
		v = new Validator();
	}

	public void checkName(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_NAME)) {
			// 最大値チェック
			v.maxSizeString(value, MAX_SIZE_NAME, FIELD_NAME);
		}
	}

	public void checkNameKana(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_NAME_KANA)) {
			// 形式チェック
			if (!v.isKatakana(value, FIELD_NAME_KANA)) {
				// 最大値チェック
				v.maxSizeString(value, MAX_SIZE_NAME_KANA, FIELD_NAME_KANA);
			}
		}
	}

	public void checkMail(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_MAIL)) {
			// 最大値チェック
			v.maxSizeString(value, MAX_SIZE_MAIL, FIELD_MAIL);
		}
	}

	public void checkPassWord(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_PASS_WORD)) {
			// 形式チェック
			if (!v.isHalfWidthCharacters(value, FIELD_PASS_WORD)) {
				// 最小値チェック
				if (!v.minSizeString(value, MIN_SIZE_PASS_WORD, FIELD_PASS_WORD)) {
					// 最大値チェック
					v.maxSizeString(value, MAX_SIZE_PASS_WORD, FIELD_PASS_WORD);
				}
			}
		}
	}

	public void checkZipCode(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_ZIP_CODE)) {
			// 形式チェック
			v.isZipCode(value, FIELD_ZIP_CODE);
		}
	}

	public void checkAddress(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_ADDRESS)) {
			// 最大値チェック
			v.maxSizeString(value, MAX_SIZE_ADDRESS, FIELD_ADDRESS);
		}
	}

	public void checkPhoneNumber(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_PHONE_NUMBER)) {
			// 形式チェック
			v.isPhoneNumber(value, FIELD_PHONE_NUMBER);
		}
	}

	public void checkDelFlg(String value) {
		// 必須チェック
		if (!v.required(value, FIELD_DEL_FLG)) {
			// 形式チェック
			v.isBoolean(value, FIELD_DEL_FLG);
		}
	}

	public List<String> getValidationList() {
		return v.getErrorMessageList();
	}
}