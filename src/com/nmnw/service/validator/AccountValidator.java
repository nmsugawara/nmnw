package com.nmnw.service.validator;

import java.util.List;

import com.nmnw.service.validator.Validator;

public class AccountValidator {
	private static final String FIELD_ID = "���ID";
	private static final String FIELD_NAME = "�����";
	private static final String FIELD_NAME_KANA = "������J�i";
	private static final String FIELD_MAIL = "���[���A�h���X";
	private static final String FIELD_PASS_WORD = "�p�X���[�h";
	private static final String FIELD_ZIP_CODE = "�X�֔ԍ�";
	private static final String FIELD_ADDRESS = "�Z��";
	private static final String FIELD_PHONE_NUMBER = "�d�b�ԍ�";
	private static final String FIELD_DEL_FLG = "�폜�t���O";
	private static final String FIELD_TOKEN = "�g�[�N��";

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
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_NAME)) {
			// �ő�l�`�F�b�N
			v.maxSizeString(value, MAX_SIZE_NAME, FIELD_NAME);
		}
	}

	public void checkNameKana(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_NAME_KANA)) {
			// �`���`�F�b�N
			if (!v.isKatakana(value, FIELD_NAME_KANA)) {
				// �ő�l�`�F�b�N
				v.maxSizeString(value, MAX_SIZE_NAME_KANA, FIELD_NAME_KANA);
			}
		}
	}

	public void checkMail(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_MAIL)) {
			// �ő�l�`�F�b�N
			v.maxSizeString(value, MAX_SIZE_MAIL, FIELD_MAIL);
		}
	}

	public void checkPassWord(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_PASS_WORD)) {
			// �`���`�F�b�N
			if (!v.isHalfWidthCharacters(value, FIELD_PASS_WORD)) {
				// �ŏ��l�`�F�b�N
				if (!v.minSizeString(value, MIN_SIZE_PASS_WORD, FIELD_PASS_WORD)) {
					// �ő�l�`�F�b�N
					v.maxSizeString(value, MAX_SIZE_PASS_WORD, FIELD_PASS_WORD);
				}
			}
		}
	}

	public void checkZipCode(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_ZIP_CODE)) {
			// �`���`�F�b�N
			v.isZipCode(value, FIELD_ZIP_CODE);
		}
	}

	public void checkAddress(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_ADDRESS)) {
			// �ő�l�`�F�b�N
			v.maxSizeString(value, MAX_SIZE_ADDRESS, FIELD_ADDRESS);
		}
	}

	public void checkPhoneNumber(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_PHONE_NUMBER)) {
			// �`���`�F�b�N
			v.isPhoneNumber(value, FIELD_PHONE_NUMBER);
		}
	}

	public void checkDelFlg(String value) {
		// �K�{�`�F�b�N
		if (!v.required(value, FIELD_DEL_FLG)) {
			// �`���`�F�b�N
			v.isBoolean(value, FIELD_DEL_FLG);
		}
	}

	public List<String> getValidationList() {
		return v.getErrorMessageList();
	}
}