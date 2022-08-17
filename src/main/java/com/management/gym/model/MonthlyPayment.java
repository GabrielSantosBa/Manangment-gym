package com.management.gym.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	@Column(name = "id_monthly_payment")
	private UUID id;

	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate paymentDate = LocalDate.now();

	
	private BigDecimal monthlyfee;

	private Integer formOfPayment;

	private Integer financialStatusEnum;

	private Integer daysLatePayment;

	@ManyToOne
	@JoinColumn(name = "FK_STUDENT")
	private Student student;

	public FinancialStatusEnum getSexoEnum() {
		return FinancialStatusEnum.valueOf(financialStatusEnum);
	}

	public void setSexoEnum(FinancialStatusEnum financialStatusEnum) {
		if (financialStatusEnum != null) {
			this.financialStatusEnum = financialStatusEnum.getCode();
		}
	}

	public FormOfPaymentEnum getFormOfPaymentEnum() {
		return FormOfPaymentEnum.valueOfFormPaymentEnum(formOfPayment);
	}

	public void setFinancialStatusEnum(FormOfPaymentEnum formOfPaymentEnum) {
		if (formOfPaymentEnum != null) {
			this.formOfPayment = formOfPaymentEnum.getCode();
		}
	}

}
