package com.management.gym.enums;

public enum FormOfPaymentEnum {

	PIX(1), CREDIT_CARD(2), DEBIT_CARD(3), TRANSFER(4), MONEY(5);

	private int code;

	private FormOfPaymentEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FormOfPaymentEnum valueOfFormPaymentEnum(int cod) {
		for (FormOfPaymentEnum form : FormOfPaymentEnum.values()) {

			if (form.ordinal() == cod) {
				return form;
			}
		}
		throw new IllegalArgumentException("Numero invalido");
	}

}
