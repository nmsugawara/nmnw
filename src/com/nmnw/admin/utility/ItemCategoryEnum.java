package com.nmnw.admin.utility;

public enum ItemCategoryEnum {
	選択してください(0),
	邦楽POP(1),
	邦楽HIPHOP(2),
	邦楽レゲエ(3),
	邦楽リミックス(4),
	その他(99);

	private final int code;
	ItemCategoryEnum(int code) {
		this.code = code;
	}
	int getCategoryCode() {
		return code;
	}
}