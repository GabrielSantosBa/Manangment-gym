package com.management.gym.enums;

public enum SexoEnum {

	MEN(1), WOMEN(2), OTHERS(3);

	private int code;

	private SexoEnum(int code) {

		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SexoEnum valueOfEnumSex(int cod) {

		for (SexoEnum sexo : SexoEnum.values()) {
			if (sexo.ordinal() == cod) {

				return sexo;
			}
		}

		throw new IllegalArgumentException("Numero invalido");

	}

}
