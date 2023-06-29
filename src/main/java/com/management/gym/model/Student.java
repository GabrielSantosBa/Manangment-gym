package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.enums.SexoEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "tb_students") 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student implements Serializable{

	
	private static final long serialVersionUID = 4489958219071898520L;
	
	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue
	@Column(name = "id_student")
	private UUID id;	
	
	@NotBlank(message = "Name Cannot Be Null")
	@Size(min = 3, max = 100, message = "Verify the size name!")
	private String name;
	
	private int sexoEnum;	
	
	@OneToMany
	@JoinColumn(name = "FK_STUDENT")
	private List<Contacts> contacts;
	
	@OneToMany
	@JoinColumn(name = "FK_STUDENT")
	private List<Measurement> measurements;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@Column(name = "situation_training", columnDefinition = "boolean default true")
	private boolean status;//TODO validar forma de inicialização
	
	@Schema(description = "Value will be used via routine", required = false)
	private Integer paymentStatus;
	
	public void setValuePaymentStatus(FinancialStatusEnum statusEnum) {
		if(Objects.nonNull(statusEnum)) {
			this.paymentStatus = statusEnum.getCode();
		}
	}
	
	public void setSexoEnum(SexoEnum sexoEnum) {
		if(Objects.nonNull(sexoEnum)) {
			this.sexoEnum = sexoEnum.getCode();
		}
	}
	
}
