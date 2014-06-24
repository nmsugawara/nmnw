package com.nmnw.service.Enum;

public enum ItemSortEnum {
	‘I‘ğ‚µ‚Ä‚­‚¾‚³‚¢("0"),
	‰¿Ši‚ªˆÀ‚¢‡("price"),
	‰¿Ši‚ª‚‚¢‡("price desc"),
	V’…‡("sales_period_from desc"),
	ŒÃ‚¢‡("sales_period_from");

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
			// ˆø”‚ÆenumŒ^‚Ì•¶š—ñ‚ğ”äŠr
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}