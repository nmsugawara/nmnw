package com.nmnw.admin.Enum;

public enum ItemCategoryEnum {
	選択してください("0"),
	邦楽POP("1"),
	邦楽HIPHOP("2"),
	邦楽レゲエ("3"),
	邦楽リミックス("4"),
	洋楽HIPHOP("5"),
	洋楽レゲエ("6"),
	洋楽リミックス("7"),
	その他("99");

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
			// 引数とenum型の文字列を比較
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}