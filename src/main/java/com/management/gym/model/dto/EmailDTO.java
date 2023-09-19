package com.management.gym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
	
	private String emailTo;
	private String subject;
	private String message;
	
}
