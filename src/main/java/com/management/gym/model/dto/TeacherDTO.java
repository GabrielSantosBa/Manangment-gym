package com.management.gym.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
	
	private Long id;	
	private String name;	
	@Schema(description = "Link or identifier of social network", example = "@Example or https://www.linkedin.com/in/gabriel-santosba/")
	private String socialNetwork;
	private String numberPhone;	
	
}
