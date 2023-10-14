package com.management.gym.model.dto;

import java.time.LocalDate;

import com.management.gym.model.Contact;
import com.management.gym.model.Plan;

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
public class StudentDTO {
	
	private Long id;
	
	private String name;
	
	private String sexo;
	
	private Contact contact;
	
	private LocalDate birthDate;
	
	private String situation;
	
	private String paymentStatus;
	
	private Plan plan;
	
}
