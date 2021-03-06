package com.nmnw.service.Enum;

public enum ItemSortEnum {
	選択してください("0"),
	価格が安い順("price"),
	価格が高い順("price desc"),
	新着順("sales_period_from desc"),
	古い順("sales_period_from");

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
			// 引数とenum型の文字列を比較
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}