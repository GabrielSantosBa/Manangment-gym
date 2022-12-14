package com.management.gym.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.management.gym.model.Contacts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {
	
	private UUID id;
	
	private String name;
	
	private List<Contacts> contacts;
	
	private LocalDate birthDate;
	
	private boolean status;
	
	
}
