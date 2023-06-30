package com.management.gym.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyAccountingDTO {
	
	private BigDecimal late;	
	
	private BigDecimal inDay;
	
	private BigDecimal toWin;
	
	@Schema(description = "Date of accounting", example = "dd-MM-yyyy")
	private LocalDate accountingDate;
	
	
}
