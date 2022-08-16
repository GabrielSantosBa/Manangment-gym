package com.management.gym.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.management.gym.enums.FormOfPaymentEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPaymentDTO {

	private Integer financialStatusEnum;	
	
	private Integer formOfPayment;
	
	private BigDecimal monthlyfee;
	
	private LocalDate birthDate;
	
	private String name;
	
	
}
