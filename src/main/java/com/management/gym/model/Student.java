package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	
	private static final long serialVersionUID = 4489958219071898520L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;	//TODO Usar UUID como tipo ao invés de Integer.
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private SexoEnum sex;	
	
	@OneToMany
	@JoinColumn(name = "ID_ALUNO")
	private List<Contacts> contacts;
	
	@OneToMany
	@JoinColumn(name = "ID_ALUNO")
	private List<Measurements> measurements;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@Column(name = "status", columnDefinition = "boolean default true")
	private boolean status = true;

	
	
}
