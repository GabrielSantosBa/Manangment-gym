package com.management.gym.model.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.management.gym.model.Contacts;

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
public class TeacherDTO {
	
	private UUID id;
	
	@NotBlank(message = "name cannot be null.")
	private String name;
	
	private List<Contacts> contacts;
	
}
