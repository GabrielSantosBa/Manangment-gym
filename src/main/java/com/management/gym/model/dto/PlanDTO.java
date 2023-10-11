package com.management.gym.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

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
public class PlanDTO {
	
	private UUID id;
	
	@NotBlank(message = "Name plan cannot be null!")
	private String namePlan;
	
	@NotBlank(message = "Value Plan cannot be null!")
	private BigDecimal valuePlan;
	
}
