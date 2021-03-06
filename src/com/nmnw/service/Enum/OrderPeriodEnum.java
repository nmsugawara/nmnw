package com.nmnw.service.Enum;

public enum OrderPeriodEnum {
	選択してください(""),
	過去30日以内の注文("-30"),
	過去半年以内の注文("-180"),
	過去1年以内の注文("-365");

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
			// 引数とenum型の文字列を比較
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}