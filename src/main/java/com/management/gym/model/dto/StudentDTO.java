package com.management.gym.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.management.gym.model.Contacts;
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
	
	private List<Contacts> contacts;
	
	private LocalDate birthDate;
	
	private String situation;
	
	private Plan plan;
	
}
