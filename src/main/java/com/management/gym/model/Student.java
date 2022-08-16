package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.management.gym.enums.SexoEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor 
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "tb_students") 
public class Student implements Serializable{

	//TODO usar validations nos principais campos aqui...
	private static final long serialVersionUID = 4489958219071898520L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id_student")
	private Long id;	//TODO Usar UUID como tipo ao invés de Integer.
	
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
	private boolean status = true;
	
	
	public SexoEnum getFinancialStatusEnum() {
		return SexoEnum.valueOfEnumSex(sexoEnum);
	}
	
	public void setFinancialStatusEnum(SexoEnum sexoEnum) {
		if(sexoEnum != null) {
			this.sexoEnum = sexoEnum.getCode();
		}
	}
	
}
