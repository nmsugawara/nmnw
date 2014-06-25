package com.nmnw.service.Enum;

public enum OrderPeriodEnum {
	‘I‘ğ‚µ‚Ä‚­‚¾‚³‚¢(""),
	‰ß‹30“úˆÈ“à‚Ì’•¶("-30"),
	‰ß‹”¼”NˆÈ“à‚Ì’•¶("-180"),
	‰ß‹1”NˆÈ“à‚Ì’•¶("-365");

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
			// ˆø”‚ÆenumŒ^‚Ì•¶š—ñ‚ğ”äŠr
			if (str.equals(enumStr.code.toString())){
				return enumStr;
			}
		}
		return null;
	}
}