package com.management.gym.model.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.management.gym.model.Contacts;

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
	
	private UUID id;
	
	@NotBlank(message = "name cannot be null.")
	private String name;
	
	@Size(min = 3, max = 100)
	@Schema(description = "Link or identifier of social network", example = "@Example or https://www.linkedin.com/in/gabriel-santosba/")
	private String socialNetwork;
	
	@Size(max = 12)	
	private String numberPhone;	
	
}
