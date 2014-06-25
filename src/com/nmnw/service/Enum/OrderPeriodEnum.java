package com.nmnw.service.Enum;

public enum OrderPeriodEnum {
	�I�����Ă�������(""),
	�ߋ�30���ȓ��̒���("-30"),
	�ߋ����N�ȓ��̒���("-180"),
	�ߋ�1�N�ȓ��̒���("-365");

	private final String code;

	OrderPeriodEnum(String code) {
		this.code = code;
	}

	public String getPeriodCode() {
		return code;
	}

	public static OrderPeriodEnum getEnum(String str) {
		OrderPeriodEnum[] enumArray = OrderPeriodEnum.values();
		for(OrderPeriodEnum enumStr : enumArray) {
			// ������enum�^�̕�������r
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}