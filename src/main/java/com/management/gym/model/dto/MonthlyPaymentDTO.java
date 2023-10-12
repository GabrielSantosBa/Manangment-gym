package com.management.gym.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class MonthlyPaymentDTO {
	
	@Schema(description = "Forma de Pagamento", example = "PIX(1), CREDIT_CARD(2), DEBIT_CARD(3), TRANSFER(4), MONEY(5)")
	private Integer formOfPayment;
	
	//@NotBlank(message = "Payment cannot be null")
	private BigDecimal monthlyfee;
	
	//@NotBlank(message = "Payment Date cannot be null")
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate paymentDate;
	
	//@NotBlank
	@Schema(description = "Status financeiro ", example = "IN_DAY(1), LATE(2), TO_WIN(3)")
	private Integer financialStatusCod;
	
	//@NotBlank
	private Long studentId;
	
}
