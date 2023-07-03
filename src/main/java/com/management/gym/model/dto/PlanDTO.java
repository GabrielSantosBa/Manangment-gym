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
	
	@NotBlank
	private UUID id;
	
	private String namePlan;
	
	private BigDecimal valuePlan;
	
}
