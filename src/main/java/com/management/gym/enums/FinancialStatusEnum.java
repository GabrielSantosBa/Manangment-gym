package com.management.gym.enums;

public enum FinancialStatusEnum {

	
	IN_DAY(1), LATE(2), IN_NEGOCIATION(3);

	private int code;

	private FinancialStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FinancialStatusEnum valueOf(int code) {

		for (FinancialStatusEnum value : FinancialStatusEnum.values()) {

			if (code == value.ordinal()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo do Enum invalido");

	}

}
