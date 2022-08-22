package com.management.gym.builders;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.management.gym.model.dto.MonthlyPaymentDTO;

public class MonthlyPaymentBuilder {
	
	
	public static MonthlyPaymentDTO createMonthlyPaymentDTO() {
		
		return MonthlyPaymentDTO.builder()
		.financialStatusEnum(1)
		.formOfPayment(1)
		.monthlyfee(new BigDecimal(49.99))
		.birthDate(LocalDate.now().minusYears(15))
		.name("Boa Hanckock").build();
	}
}
