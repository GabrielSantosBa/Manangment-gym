package com.management.gym.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.enums.FormOfPaymentEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_monthly_payment")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthlyPayment implements Serializable {

	
	
	private static final long serialVersionUID = 6821647203784981686L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@EqualsAndHashCode.Include
	private Integer id;
	
	@DateTimeFormat(iso =ISO.DATE )
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate paymentDate = LocalDate.now();
	
	private BigDecimal monthlyfee;
	
	
	@Enumerated(EnumType.STRING)
	private FormOfPaymentEnum formOfPayment;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private FinancialStatusEnum financialStatusEnum;	
	
	private Integer daysLatePayment;

	
	@ManyToOne
	@JoinColumn(name = "ID_STUDENT")
	private Student  Student;
	
	
}
