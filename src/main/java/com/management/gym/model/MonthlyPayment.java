package com.management.gym.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.gym.enums.FinancialStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_monthly_payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthlyPayment implements Serializable {

	private static final long serialVersionUID = 6821647203784981686L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_monthly_payment")
	private Long id;

	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate paymentDate;
	
	private BigDecimal monthlyfee;

	private Integer formOfPayment;

	private Integer financialStatusCod;

	private Integer daysLatePayment;
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate payDay;

	@ManyToOne
	@JoinColumn(name = "FK_STUDENT")
	private Student student;

	
	public void setFinancialStatusEnum(FinancialStatusEnum financialStatusEnum) {
		if (financialStatusEnum != null) {
			this.financialStatusCod = financialStatusEnum.getCode();
		}
	}
	
}
