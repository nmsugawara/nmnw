package com.nmnw.service.Enum;

public enum ItemSortEnum {
	�I�����Ă�������("0"),
	���i��������("price"),
	���i��������("price desc"),
	�V����("sales_period_from desc"),
	�Â���("sales_period_from");

	private final String code;

	ItemSortEnum(String code) {
		this.code = code;
	}

	public String getSortCode() {
		return code;
	}

	public static ItemSortEnum getEnum(String str) {
		ItemSortEnum[] enumArray = ItemSortEnum.values();
		for(ItemSortEnum enumStr : enumArray) {
			// ������enum�^�̕�������r
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}