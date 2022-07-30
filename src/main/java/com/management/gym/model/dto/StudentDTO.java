package com.management.gym.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.management.gym.model.Contacts;

import lombok.Data;

@Data
public class StudentDTO {
	
	private Long id;
	
	private String name;
	
	private List<Contacts> contacts;
	
	private LocalDate birthDate;
	
	private boolean status;
	
	
}
