package com.management.gym.builders;

import java.math.BigDecimal;

import com.management.gym.model.dto.MonthlyPaymentDTO;

public class MonthlyPaymentBuilder {
	
	
	public static MonthlyPaymentDTO createMonthlyPaymentDTO() {
		
		return MonthlyPaymentDTO.builder()
		.financialStatusCod(1)
		.formOfPayment(1)
		.monthlyfee(new BigDecimal(49.99))
		.studentId("UUID.randomUUID()")
		.build();
	}
}
