package com.nmnw.admin.utility;

public enum ItemCategoryEnum {
	�I�����Ă�������(0),
	�M�yPOP(1),
	�M�yHIPHOP(2),
	�M�y���Q�G(3),
	�M�y���~�b�N�X(4),
	���̑�(99);

	private final int code;
	ItemCategoryEnum(int code) {
		this.code = code;
	}
	int getCategoryCode() {
		return code;
	}
}