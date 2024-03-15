package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_student")
	private Long id;
	
	@NotBlank(message = "Name Cannot Be Null")
	@Size(min = 3, max = 100, message = "Verify the size name!")
	private String name;
	
	private String sexo;	
	
	@OneToOne
	@JoinColumn(name = "FK_STUDENT")
	private Contact contact;
	
	@OneToMany
	@JoinColumn(name = "FK_STUDENT")
	private List<Measurement> measurements;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@Column(name = "situation_training")
	private String situation;
	
	@Schema(description = "Value will be used via routine", required = false)
	private String paymentStatus;
	
	@OneToOne(cascade=CascadeType.REMOVE , fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "FK_PLAN")
	private Plan plan;
	
	public void setValuePaymentStatus(FinancialStatusEnum statusEnum) {
		if(Objects.nonNull(statusEnum)) {
			this.paymentStatus = statusEnum.name();
		}
	}
	
	public void setSexoEnum(SexoEnum sexoEnum) {
		if(Objects.nonNull(sexoEnum)) {
			this.sexo = sexoEnum.name();
		}
	}
}
