package com.nmnw.admin.Enum;

public enum ItemCategoryEnum {
	�I�����Ă�������("0"),
	�M�yPOP("1"),
	�M�yHIPHOP("2"),
	�M�y���Q�G("3"),
	�M�y���~�b�N�X("4"),
	�m�yHIPHOP("5"),
	�m�y���Q�G("6"),
	�m�y���~�b�N�X("7"),
	���̑�("99");

	private final String code;

	ItemCategoryEnum(String code) {
		this.code = code;
	}

	public String getCategoryCode() {
		return code;
	}

	public static ItemCategoryEnum getEnum(String str) {
		ItemCategoryEnum[] enumArray = ItemCategoryEnum.values();
		for(ItemCategoryEnum enumStr : enumArray) {
			// ������enum�^�̕�������r
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}